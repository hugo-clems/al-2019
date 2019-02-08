package plateau;

import java.awt.*;

public interface IDeveloppeurPlateau {

    Boolean placerAgentite(Point point, IAgentite agentite);

    IAgentite enleverAgentite(Point point, IAgentite agentite);
}
