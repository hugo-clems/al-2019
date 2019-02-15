package entites;

import plateau.IAgentite;
import plateau.IEntitePlateau;

import java.util.UUID;

public abstract class AbstractEntite implements IAgentite {

    /**
     * L'id de l'agent.
     */
    private UUID id ;

    /**
     * Le nom de l'agent.
     */
    private String nom ;

    /**
     * L'interface du plateau.
     */
    private IEntitePlateau plateau ;

    /**
     * Constructeur par défaut.
     * @param nom
     * @param plateau
     */
    public AbstractEntite (String nom, IEntitePlateau plateau) {
        this.nom = nom;
        this.plateau = plateau;
        this.id = UUID.randomUUID();
    }
}
