package entites;

import plateau.IAgentite;

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
     * Constructeur par défaut.
     * @param nom le nom de l'entité
     */
    public AbstractEntite(String nom) {
        this.nom = nom;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "AbstractEntite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
