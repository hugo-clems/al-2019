package impl;


import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import interfaces.IEval;

import java.util.ArrayList;
import java.util.List;

public class Noyau implements IEval{

    List<Composant> composants;

    private List<AgentComposant> creerAgents(){
        List<AgentComposant> listAgents = new ArrayList<>();
        AgentComposant abstractAgentSocial;
        for(Composant composant : composants){
            abstractAgentSocial = new AgentComposant();
            listAgents.add(abstractAgentSocial);
        }
        return  listAgents;
    }

    @Override
    public void noterConfiguration(List<Configuration> configurations) {

    }




    //getPossib
    //DemanderConnexionPossible

}
