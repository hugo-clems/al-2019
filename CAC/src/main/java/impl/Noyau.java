package impl;


import agent.AbstractAgentSocial;
import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import ihm.IVue;
import ihm.VueImpl;
import interfaces.IConnexion;
import interfaces.IEval;

import java.util.ArrayList;
import java.util.List;

public class Noyau extends AbstractAgentSocial implements IEval{

     private IVue iVue;
     private IConnexion iConnexion;
     //private IPersistence iPersistence;

    public Noyau (IVue iVue,IConnexion iConnexion) {
        this.iVue = new VueImpl();
        this.iConnexion = iConnexion;
    }

    private List<AgentComposant> creerAgents(){

        List<Composant> composants = iVue.entrerConfiguration();
        List<AgentComposant> listAgents = new ArrayList<>();
        AgentComposant abstractAgentSocial;
        for(Composant composant : composants){
            abstractAgentSocial = new AgentComposant(composant, this);
            listAgents.add(abstractAgentSocial);
        }
        return  listAgents;
    }



    /*public List<Configuration> recevoirConnexionPossibles(){

    }
*/
    @Override
    public List<Configuration> noterConfiguration() {
        return null;
    }

}
