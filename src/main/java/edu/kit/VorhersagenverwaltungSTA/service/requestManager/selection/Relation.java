package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PluralObjectTypeEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SingularObjectTypeEncoder;

/**
 * This class represents a relation between {@link ObjectType ObjectTypes} in the
 * <a href="https://github.com/opengeospatial/sensorthings">SensorThings API</a>.
 *
 * @author Dennis Moschina
 */
public class Relation {
    private final boolean asList;
    private final ObjectType objectType;
    private final String name;

    /**
     * Creates a Relation.
     *
     * @param objectType the type of the related object
     */
    public Relation(ObjectType objectType) {
        this(false, objectType);
    }

    /**
     * Creates a Relation.
     *
     * @param asList there are multiple instances of the related object possible in this relation
     * @param objectType the type of the related object
     */
    public Relation(boolean asList, ObjectType objectType) {
        this(asList,
                objectType,
                (asList ? new PluralObjectTypeEncoder() : new SingularObjectTypeEncoder()).encode(objectType));
    }

    /**
     * Creates a named Relation.
     * <p> There are not multiple Objects related.
     *
     * @param objectType the type of the related object
     * @param name the name of the relation
     */
    public Relation(ObjectType objectType, String name) {
        this(false, objectType, name);
    }

    /**
     * Creates a named Relation.
     *
     * @param asList there are multiple instances of the related object possible in this relation
     * @param objectType the type of the related object
     * @param name the name of the relation
     */
    public Relation(boolean asList, ObjectType objectType, String name) {
        this.asList = asList;
        this.objectType = objectType;
        this.name = name;
    }

    public boolean isAsList() {
        return asList;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public String getName() {
        return name;
    }
}