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

    private ArrayList<Configuration> lesConfigurations;
    private List<MessageAgentAuNoyau> messageRecus = new ArrayList<>();
    private List<Connexion> connexionsPossbile = new ArrayList<>();





    public Noyau (IVue iVue,IConnexion iConnexion,IPersistence iPersistence,IRecommandation iRecommandation) {
        this.iVue = iVue;
        this.iConnexion = iConnexion;
        this.iPersistence = iPersistence;
        this.iRecommandation = iRecommandation;
     }

    @Override
    public void noterConfiguration(int configuration, Appreciation appreciation) {

            Configuration configurationASauvegarder = lesConfigurations.get(configuration);
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

            if (configuration == lesConfigurations.size()){
                connexionsASauvegarder.addAll(connexions);
                iPersistence.sauvegarderConnexion(connexionsASauvegarder);
            }
    }


    @Override
    public List<Configuration> getConfigurations() {

        ArrayList<Connexion> connexionsPossiblesAPersistees = new ArrayList<>();
        List<MessageAgentAuNoyau> messagesAgentAuNoyeau = recevoirMessage();
        ArrayList<Configuration> configurationsPossibles = new ArrayList<>();
        Set<Connexion> setConnexionPossibles = new HashSet<>();
        ArrayList<Connexion> inter;

        messagesAgentAuNoyeau.forEach(messageAgentAuNoyeau -> {
            setConnexionPossibles.addAll(messageAgentAuNoyeau.getCouplePossible());
            configurationsPossibles.add(new Configuration(setConnexionPossibles));
        });

        for (Configuration configuration : configurationsPossibles) {
            inter = new ArrayList<>();
            inter.addAll(configuration.getConnexions());
            connexionsPossiblesAPersistees.addAll(iPersistence.trouverConnexion(inter));
        }

        List<Configuration> configurations = new ArrayList<>();
        configurations.addAll(iRecommandation.creerConfigurations(connexionsPossiblesAPersistees));

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

    public ArrayList<Configuration> getLesConfigurations() {
        return lesConfigurations;
    }

    public void setLesConfigurations(ArrayList<Configuration> lesConfigurations) {
        this.lesConfigurations = lesConfigurations;
    }

    @Override
    public void actionTour() {

    }
}
