package impl;


import agent.AbstractAgentSocial;
import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import ihm.IVue;
import ihm.VueImpl;
import interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Noyau extends AbstractAgentSocial implements IEval{

    private List<AgentComposant> listAgents;

     private IVue iVue;
     private IConnexion iConnexion;
     private IPersistence iPersistence;
     private SystemeRecommandation systemeRecommandation;

    public Noyau (IVue iVue,IConnexion iConnexion) {
        this.iVue = new VueImpl();
        this.iConnexion = iConnexion;
    }

    private List<AgentComposant> creerAgents(){

        List<Composant> composants = iVue.entrerConfiguration();
        listAgents = new ArrayList<>();
        AgentComposant abstractAgentSocial;
        for(Composant composant : composants){
            abstractAgentSocial = new AgentComposant(composant, this);
            listAgents.add(abstractAgentSocial);
        }
        return  listAgents;
    }


    @Override
    public void noterConfiguration(int indice, Appreciation a) {

        if (indice == listAgents.size()){

        }
    }

    @Override
    public List<Configuration> getConfigurations() {
        return null;
    }

    /*public List<Configuration> recevoirConnexionPossibles(){

    }
*/


    public List<Configuration> configurations(){
        List<Configuration> configurations = new ArrayList<>();
        List<Configuration> configurationsRecommandees = new ArrayList<>();
        configurations = iPersistence.trouverTous();
        //configurationsRecommandees = systemeRecommandation.choisirConfiguration(configurations);
        return null;
    }


}
