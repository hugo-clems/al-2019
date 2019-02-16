package agent;

import MASInfrastructure.Communication.ICommunication;
import MASInfrastructure.Etat.LifeCycle;
import common.Direction;

public class Fourmi extends AbstractAgentSitue {

    public boolean transporteNourriture;

    public Fourmi(String nom, Direction directionInitiale, boolean transporteNourriture) {
        super(nom, directionInitiale);
        this.transporteNourriture = transporteNourriture;
    }

    public void chercherNourriture(){

    }

    public void revenirAuNid(){

    }

    @Override
    public void actionTour() {

    }
}
