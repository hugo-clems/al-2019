package impl;


import MASInfrastructure.Communication.IMessage;
import agent.AbstractAgentSocial;
import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import ihm.IVue;
import interfaces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Noyau extends AbstractAgentSocial implements IEval{


    private IVue iVue;
    private IConnexion iConnexion;
    private IPersistence iPersistence;
    private SystemeRecommandation systemeRecommandation;

    private ArrayList<Configuration> configurations;
    private ArrayList<Configuration> configurationsPossibles;
    private ArrayList<Connexion> connexionsPossibles;
    private ArrayList<Configuration> configurationsASauvegarder;



    public Noyau (IVue iVue,IConnexion iConnexion) {
        this.iVue = iVue;
        this.iConnexion = iConnexion;
        configurationsASauvegarder = new ArrayList<>();
        connexionsPossibles = new ArrayList<>();
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

            Configuration configurationASauvegarder = configurations.get(configuration);
            Set <Connexion> connexions = configurationASauvegarder.getConnexions();
            ArrayList<Connexion> connexionsASauvegarder = new ArrayList<>();

            if(appreciation.equals(Appreciation.JAIME)){
                for(Connexion connexion : connexions){
                    connexion.incrementerNote();
                    connexions.add(connexion);
                }
            }else{

                for(Connexion connexion : connexions){
                    connexion.decrementerNote();
                    connexions.add(connexion);
                }
            }

            if (configuration == configurations.size()){
                connexionsASauvegarder.addAll(connexions);
                iPersistence.sauvegarderConnexion(connexionsASauvegarder);
            }
    }


    @Override
    public List<Configuration> configurations() {
        connexionsPossibles = null;
        return null;//iPersistence.trouverConnexion(connexionsPossibles);
    }


   /* public List<MessageAgentAuNoyeau> recevoirMessage() {

        for (AbstractMessage msg : super.recevoirMessages()) {
            if ( msg instanceof  MessageAgentAuNoyeau){
                MessageAgentAuNoyeau message = (MessageAgentAuNoyeau) msg;
                messageRecus.add(message);
                connexionsPossbile.addAll(message.getCouplePossible());
            }
        }
        return messageRecus;

    }*/

}
