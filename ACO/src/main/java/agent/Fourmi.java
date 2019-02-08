package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;

public class Fourmi extends AbstractAgentSitue {

    public boolean transporteNourriture;

    public Fourmi(String nom, Direction directionInitiale, LifeCycle lifeCycle, ICommunication myMailBoxManager, boolean transporteNourriture) {
        super(nom, directionInitiale, lifeCycle, myMailBoxManager);
        this.transporteNourriture = transporteNourriture;
    }

    public void chercherNourriture(){

    }

    public void revenirAuNid(){

    }

}
