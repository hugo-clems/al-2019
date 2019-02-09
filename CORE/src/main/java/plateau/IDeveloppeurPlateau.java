package plateau;

public interface IDeveloppeurPlateau {

    Boolean placerAgentite(Position position, IAgentite agentite);

    IAgentite enleverAgentite(Position position, IAgentite agentite);
}
