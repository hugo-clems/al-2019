package plateau;

public interface IDeveloppeurPlateau {

    /**
     * Place un nouvel agent sur le plateau à la Position donnée.
     * @param position la position sur laquelle on place l'agentite
     * @param agentite l'agentite à placer sur le plateau
     * @return true si l'agentite a été placé et false sinon
     */
    Boolean placerAgentite(Position position, IAgentite agentite);

    /**
     * Retire l'agent du plateau à la Position donnée.
     * @param position la position sur laquelle on retire l'agentite
     * @param agentite l'agentite à retirer du plateau
     * @return true si l'agentite a été retiré et false sinon
     */
    IAgentite enleverAgentite(Position position, IAgentite agentite);
}
