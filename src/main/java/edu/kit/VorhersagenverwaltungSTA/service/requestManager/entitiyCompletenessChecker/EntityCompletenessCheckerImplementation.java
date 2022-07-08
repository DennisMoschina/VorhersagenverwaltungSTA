package edu.kit.VorhersagenverwaltungSTA.service.requestManager.entitiyCompletenessChecker;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PluralObjectTypeEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SingularObjectTypeEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the {@link EntityCompletenessChecker} interface.
 * @author Dennis Moschina
 */
@Service
public class EntityCompletenessCheckerImplementation implements EntityCompletenessChecker<Entity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityCompletenessCheckerImplementation.class);

    private static final List<String> POSSIBLE_EXPAND_PROPERTIES;

    static {
        List<String> singularNames = Arrays.stream(ObjectType.values())
                .map(type -> new SingularObjectTypeEncoder().encode(type).toLowerCase()).toList();
        List<String> pluralNames = Arrays.stream(ObjectType.values())
                .map(type -> new PluralObjectTypeEncoder().encode(type).toLowerCase()).toList();
        POSSIBLE_EXPAND_PROPERTIES = new LinkedList<>(singularNames);
        POSSIBLE_EXPAND_PROPERTIES.addAll(pluralNames);
    }

    @Override
    public boolean isComplete(SingleSelection selection, Entity entity) {
        if (entity.getId() != selection.getSelectedId()) {
            LOGGER.warn(String.format("expected entity with id %d, but got %d", entity.getId(), selection.getSelectedId()));
            return false;
        }

        //check, if all properties are not null using reflection
        if (selection.hasSelectKeys()) {
            for (String key : selection.getKeys()) {
                if (propertyIsNull(entity, key)) return false;
            }
        } else {
            List<PropertyDescriptor> descriptors
                    = new java.util.ArrayList<>(
                            Arrays.stream(BeanUtils.getPropertyDescriptors(entity.getClass())).toList()
            );
            descriptors.removeIf(pd -> POSSIBLE_EXPAND_PROPERTIES.contains(pd.getName().toLowerCase()));
            List<String> propertyNames = descriptors.stream().map(FeatureDescriptor::getName).toList();

            for (String property : propertyNames) {
                if (this.propertyIsNull(entity, property)) return false;
            }
        }
        for (Selection expand : selection.getObjectsToExpand()) {
            String propertyName;
            Encoder<ObjectType> objectTypeEncoder;
            if (expand.getClass() == SingleSelection.class) objectTypeEncoder = new SingularObjectTypeEncoder();
            else if (expand.getClass() == MultiSelection.class) objectTypeEncoder = new PluralObjectTypeEncoder();
            else {
                LOGGER.error(String.format("unknown class of selection called %s", expand.getClass()));
                return false;
            }
            propertyName = objectTypeEncoder.encode(expand.getObjectType());

            if (propertyIsNull(entity, propertyName)) return false;
        }
        return true;
    }

    private boolean propertyIsNull(Entity entity, String propertyName) {
        PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(entity.getClass(), propertyName);
        if (pd == null) return true;
        Method getter = pd.getReadMethod();
        try {
            if (getter.invoke(entity) == null) return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
            return true;
        }
        return false;
    }
}