package domaine;

import agent.AbstractAgent;
import agent.AbstractAgentSocial;
import communication.AbstractMessage;
import interfaces.IConnexion;

import java.util.*;

/**
 * Agent associe a un composant doit diffuser les port de son composant et associé son
 */
public class AgentComposant extends AbstractAgentSocial  implements IConnexion {

    private Composant composant;

    private MessageAgentComposant messagePourAgentComposants;

    private MessageAgentAuNoyau messagePourAgentNoyeau;

    private List<MessageAgentComposant> messageRecus = new ArrayList<>();

    private  List<Connexion> connexionsPossible = new ArrayList<>();

    private AbstractAgent noyeau;

    public AgentComposant(Composant composant, AbstractAgent noyeau) {
        this.composant = composant;
        messagePourAgentComposants = new MessageAgentComposant(composant.getPortRequis());
        messagePourAgentNoyeau = new MessageAgentAuNoyau(connexionsPossible);
        this.noyeau = noyeau;

    }

    /**
     * Envoie un message au noyeau des connexion possible
     */
    @Override
    public void envoyerConnexionPossible() {
        //On regarde nos message
        for (MessageAgentComposant msg : this.recevoirMessage()) {
            //On ajoute les couple Possible
            compareRequisFournis(msg.getPortRequis(), this.composant.getPortFournis());
        }
        //On envoie au noyeau si il y a des connexion
        if ( !connexionsPossible.isEmpty()) {
            super.envoyerMessage(this.messagePourAgentNoyeau, noyeau);
        }

    }

    public List<MessageAgentComposant> recevoirMessage() {

        for (AbstractMessage msg : super.recevoirMessages()) {
            messageRecus.add((MessageAgentComposant) msg);
        }
        return messageRecus;

    }

    private void compareRequisFournis(Set<Port> requis, Set<Port> fournis) {
        for (Port portRequis : requis) {
            for (Port portFournis : fournis) {
                if (portRequis.estCompatible(portFournis)) {
                    connexionsPossible.add(new Connexion(portFournis,portRequis));
                }
            }
        }
    }

    /**
     * Envoie ses port Requis a tous les autres agent meme au noyeau et c'est au noyeau a trié
     */
    @Override
    public void broadcast() {
        //On envoie si on a des ports Requit
        if ( !messagePourAgentComposants.getPortRequis().isEmpty())
            super.diffuserMessage(this.messagePourAgentComposants);

    }


    @Override
    public void actionTour() {

    }




}
