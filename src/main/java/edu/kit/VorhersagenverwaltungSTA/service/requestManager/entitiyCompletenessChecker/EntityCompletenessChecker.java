package edu.kit.VorhersagenverwaltungSTA.service.requestManager.entitiyCompletenessChecker;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;

/**
 * This interface is used to check, if an {@link Entity} matches the requirements to be complete.
 * @param <T> the type of the {@link Entity} to check
 */
public interface EntityCompletenessChecker<T extends Entity> {

    /**
     * Check, if everything requested in the {@link SingleSelection} is already present in the entity.
     * @param selection the {@link SingleSelection} with the requested properties
     * @param entity the entity to check if it has the requested properties
     * @return the entity has all requested properties
     */
    boolean isComplete(SingleSelection selection, T entity);
}