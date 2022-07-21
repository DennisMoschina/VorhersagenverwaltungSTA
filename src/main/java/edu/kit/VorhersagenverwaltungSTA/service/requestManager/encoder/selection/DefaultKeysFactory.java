package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

import java.util.Set;

/**
 * This interface is used, to get the keys that should be selected by default.
 * @author Dennis Moschina
 */
public interface DefaultKeysFactory {
    Set<String> getDefaultKeys(ObjectType objectType);
}