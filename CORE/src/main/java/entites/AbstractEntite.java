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
     * @param nom le nom de l'entité
     * @param plateau le plateau auquel appartient l'entité
     */
    public AbstractEntite (String nom, IEntitePlateau plateau) {
        this.nom = nom;
        this.plateau = plateau;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "AbstractEntite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", plateau=" + plateau +
                '}';
    }
}
