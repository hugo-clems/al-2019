package plateau;

public interface IDeveloppeurPlateau {

    /**
     * Place un nouvel agent sur le plateau à la Position donnée.
     * @param position
     * @param agentite
     * @return
     */
    Boolean placerAgentite(Position position, IAgentite agentite);

    /**
     * Retire l'agent du plateau à la Position donnée.
     * @param position
     * @param agentite
     * @return
     */
    IAgentite enleverAgentite(Position position, IAgentite agentite);
}
