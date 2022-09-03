package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import edu.kit.VorhersagenverwaltungSTA.model.core.CacheProxyObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.model.core.ObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.GenericSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.entitiyCompletenessChecker.EntityCompletenessChecker;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.entitiyCompletenessChecker.EntityCompletenessCheckerImplementation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.RelationSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to load elements from a specific FROST-server.
 *
 * @author Dennis Moschina
 */
public class RequestManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestManager.class);

    private final RestTemplate restTemplate;
    private final Source source;

    private final Map<Class<Entity>, ObjectContainer<Long, Entity>> containerMap;
    private ObjectContainer<Long, Entity> container;

    public RequestManager(Source source) {
        this.source = source;
        this.restTemplate = RestTemplateFactory.getRestTemplate(source);
        this.containerMap = new HashMap<>();
    }

    /**
     * Load the object selected by the {@link Selection} from the server.
     * @param selection the {@link Selection}, specifying, which Object should be loaded from the server
     * @return the result of the request
     */
    public Object request(Selection selection) {
        Class<?> resultType = this.getResultType(selection);

        if (resultType == null) {
            LOGGER.error("Failed to get result type");
            return null;
        }

        Object result;
        if (this.shouldLoad(selection)) {
            String encodedSelection = new GenericSelectionEncoder().encode(selection);
            String url = this.source.url();
            if (!url.endsWith("/")) url += "/";
            String request = url + encodedSelection;
            result = this.restTemplate.getForObject(request, resultType);
            if (result == null) {
                LOGGER.error("failed to load object for selection {}", selection);
                return null;
            }
            this.manageResult(result, selection);
        } else {
            result = this.container.get(((SingleSelection) selection).getSelectedId());
            LOGGER.debug("found {} with id {} in container",
                    selection.getObjectType(), ((SingleSelection) selection).getSelectedId());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private Class<?> getResultType(Selection selection) {
        Class<?> resultType = selection.getObjectTypeClass();
        Class<Entity> entityType;
        if (selection.getClass() == SingleSelection.class) entityType = (Class<Entity>) selection.getObjectTypeClass();
        else if (selection.getClass() == MultiSelection.class)
            entityType = (Class<Entity>) selection.getObjectType().getObjectClass();
        else if (selection.getClass() == ObjectAssociatedSelection.class) {
            entityType = (Class<Entity>) selection.getObjectTypeClass();
        } else {
            LOGGER.warn(String.format("unknown selection type %s", selection.getClass()));
            return null;
        }
        if (!containerMap.containsKey(entityType)) {
            this.containerMap.put(entityType, new CacheProxyObjectContainer<>());
        }
        this.container = this.containerMap.get(entityType);

        return resultType;
    }

    /**
     * Check, if the Object in the selection is already in the container and if all selected attributes are loaded.
     * @param selection The selection to check.
     * @return True, if the Object in the selection is already in the container and if all selected attributes are
     * loaded.
     */
    private boolean shouldLoad(Selection selection) {
        if (selection.getClass() != SingleSelection.class) return true;

        SingleSelection singleSelection = (SingleSelection) selection;
        if (!this.container.contains(singleSelection.getSelectedId())) return true;

        Entity loadedObject = this.container.get(singleSelection.getSelectedId());
        EntityCompletenessChecker<Entity> checker = new EntityCompletenessCheckerImplementation();

        return !checker.isComplete(singleSelection, loadedObject);
    }

    @SuppressWarnings("unchecked")
    private void manageResult(Object result, Selection selection) {
        if (selection instanceof SingleSelection) {
            this.updateContainerEntryFor((Entity) result);
        } else if (selection instanceof MultiSelection) {
            STAObjectList<Entity> list = (STAObjectList<Entity>) result;
            this.updateContainerEntriesFor(list.getList());
        } else if (selection instanceof ObjectAssociatedSelection associatedSelection) {
            this.manageResult(result, associatedSelection.getSelection());
        } else if (selection instanceof RelationSelection relationSelection) {
            this.manageResult(result, relationSelection.getSelection());
        }
    }

    private void updateContainerEntriesFor(Collection<Entity> entities) {
        for (Entity entity : entities) {
            this.updateContainerEntryFor(entity);
        }
    }

    private void updateContainerEntryFor(Entity entity) {
        Entity entityInContainer = this.container.get(entity.getId());
        if (entityInContainer != null) entityInContainer.update(entity);
        else this.container.add(entity.getId(), entity);
    }
}