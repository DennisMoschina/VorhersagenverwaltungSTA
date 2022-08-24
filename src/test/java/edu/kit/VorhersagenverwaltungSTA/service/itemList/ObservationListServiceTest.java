package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ObservationListServiceTest extends AbstractListServiceTest {
    public ObservationListServiceTest() {
        super(ObjectType.OBSERVATION);
    }

    @Override
    protected void assign() {
        this.service = new ObservationListService();
    }
}