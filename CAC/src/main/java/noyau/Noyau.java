package noyau;


import agent.AbstractAgentSocial;
import communication.AbstractMessage;
import domaine.*;
import enumeration.Appreciation;
import ihm.IVue;
import interfaces.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Noyau extends AbstractAgentSocial implements IEval{


    private IVue iVue;
    private IConnexion iConnexion;
    private IPersistence iPersistence;
    private IRecommandation iRecommandation;

    private List<Configuration> configurations;
    private List<MessageAgentAuNoyau> messageRecus = new ArrayList<>();
    private List<Connexion> connexionsPossbile = new ArrayList<>();

    public Noyau (IVue iVue,IPersistence iPersistence,IRecommandation iRecommandation) {
        this.iVue = iVue;
        this.iPersistence = iPersistence;
        this.iRecommandation = iRecommandation;
    }

    public Noyau (IVue iVue,IConnexion iConnexion,IPersistence iPersistence,IRecommandation iRecommandation) {
        this.iVue = iVue;
        this.iConnexion = iConnexion;
        this.iPersistence = iPersistence;
        this.iRecommandation = iRecommandation;
     }

    @Override
    public void noterConfiguration(int configuration, Appreciation appreciation) {

            Configuration configurationASauvegarder = configurations.get(configuration);
            Set <Connexion> connexions = configurationASauvegarder.getConnexions();
            ArrayList<Connexion> connexionsASauvegarder = new ArrayList<>();

            if(appreciation.equals(Appreciation.JAIME)){
                connexions.forEach(connexion -> {
                    connexion.incrementerNote();
                    connexions.add(connexion);
                });
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

    public void calculerConfigurations() {
        ArrayList<Connexion> connexionsPossibles = new ArrayList<>();
        List<MessageAgentAuNoyau> messagesAgentAuNoyeau = recevoirMessage();
        ArrayList<Configuration> configurationsPossibles = new ArrayList<>();
        Set<Connexion> setConnexionPossibles = new HashSet<>();
        ArrayList<Connexion> inter;

        messagesAgentAuNoyeau.forEach(messageAgentAuNoyeau -> {
            setConnexionPossibles.addAll(messageAgentAuNoyeau.getCouplePossible());
        });

        connexionsPossibles = iPersistence.trouverConnexion(new ArrayList<>(setConnexionPossibles));

        configurations = new ArrayList<>(iRecommandation.creerConfigurations(connexionsPossibles));
    }

    @Override
    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public List<MessageAgentAuNoyau> recevoirMessage() {

        for (AbstractMessage msg : super.recevoirMessages()) {
            if ( msg instanceof  MessageAgentAuNoyau){
                MessageAgentAuNoyau message = (MessageAgentAuNoyau) msg;
                messageRecus.add(message);
                connexionsPossbile.addAll(message.getCouplePossible());
            }
        }
        return messageRecus;

    }

    public List<AgentComposant> creerAgents(){

        List<Composant> composants = iVue.entrerConfiguration();
        List<AgentComposant> listAgents = new ArrayList<>();
        AgentComposant abstractAgentSocial;
        for(Composant composant : composants){
            abstractAgentSocial = new AgentComposant(composant, this);
            listAgents.add(abstractAgentSocial);
        }
        return  listAgents;
    }


    public List<MessageAgentAuNoyau> getMessageRecus() {
        return messageRecus;
    }

    public void setMessageRecus(List<MessageAgentAuNoyau> messageRecus) {
        this.messageRecus = messageRecus;
    }

    public List<Connexion> getConnexionsPossbile() {
        return connexionsPossbile;
    }

    public void setConnexionsPossbile(List<Connexion> connexionsPossbile) {
        this.connexionsPossbile = connexionsPossbile;
    }

    public void setConfigurations(ArrayList<Configuration> configurations) {
        this.configurations = configurations;
    }

    @Override
    public void actionTour() {

    }
}
