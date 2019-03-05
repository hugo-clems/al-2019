package impl;


import agent.AbstractAgentSocial;
import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import ihm.IVue;
import ihm.VueImpl;
import interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Noyau extends AbstractAgentSocial implements IEval{

    private ArrayList<Configuration> configurations;
    private ArrayList<Configuration> configurationsASauvegarder;

     private IVue iVue;
     private IConnexion iConnexion;
     private IPersistence iPersistence;
     private SystemeRecommandation systemeRecommandation;

    public Noyau (IVue iVue,IConnexion iConnexion) {
        this.iVue = new VueImpl();
        this.iConnexion = iConnexion;
        List<Configuration> configurationsASauvegarder = new ArrayList<>();
        List<Configuration> configurations = new ArrayList<>();

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


    @Override
    public void noterConfiguration(int configuration, Appreciation appreciation) {
          /*  Configuration configurationASauvegarder = configurations.get(configuration);
            Set <Connexion> connexions = configurationASauvegarder.getConnexions();
            ArrayList<Connexion> connexionsASauvegarder = new ArrayList<>();
            connexionsASauvegarder.addAll(connexions);
            if(appreciation.equals(Appreciation.JAIME)){
                for(Connexion connexion : connexions)
              //      connexion.incrementerNote();
            }else{
                for(Connexion connexion : connexions)
                //    connexion.decrementerNote();
            }
            //if (configuration == configurations.size());
           // iPersistence.sauvegarder(connexionsASauvegarder);*/
    }

    @Override
    public List<Configuration> Configurations() {
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
