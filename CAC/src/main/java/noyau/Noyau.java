package noyau;


import agent.AbstractAgentSocial;
import domaine.AgentComposant;
import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import enumeration.Appreciation;
import ihm.IVue;
import interfaces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Noyau extends AbstractAgentSocial implements IEval{


    private IVue iVue;
    private IConnexion iConnexion;
    private IPersistence iPersistence;
    private IRecommandation iRecommandation;

    private ArrayList<Configuration> lesConfigurations;
    private ArrayList<Connexion> connexionsPossibles;
    private ArrayList<Connexion> connexionsPossiblesPersistees;
    private List<MessageAgentAuNoyeau> messageRecus = new ArrayList<>();
    private List<Connexion> connexionsPossbile = new ArrayList<>();





    public Noyau (IVue iVue,IConnexion iConnexion,IPersistence iPersistence,IRecommandation iRecommandation) {
        this.iVue = iVue;
        this.iConnexion = iConnexion;
        this.iPersistence = iPersistence;
        this.iRecommandation = iRecommandation;
        connexionsPossibles = new ArrayList<>();
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

        List<MessageAgentAuNoyeau> messagesAgentAuNoyeau = recevoirMessage();

        messagesAgentAuNoyeau.forEach(messageAgentAuNoyeau ->
            { connexionsPossibles.add(new Connexion(messageAgentAuNoyeau.getCouplePossible()))});

        connexionsPossiblesPersistees = iPersistence.trouverConnexion(connexionsPossibles);
        return iRecommandation.creerConfigurations(connexionsPossiblesPersistees);
    }

    public List<MessageAgentAuNoyeau> recevoirMessage() {

        for (AbstractMessage msg : super.recevoirMessages()) {
            if ( msg instanceof  MessageAgentAuNoyeau){
                MessageAgentAuNoyeau message = (MessageAgentAuNoyeau) msg;
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


    public List<MessageAgentAuNoyeau> getMessageRecus() {
        return messageRecus;
    }

    public void setMessageRecus(List<MessageAgentAuNoyeau> messageRecus) {
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

}
