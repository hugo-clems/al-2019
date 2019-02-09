package entites;

import plateau.IAgentite;
import plateau.IEntitePlateau;

import java.util.UUID;

public abstract class AbstractEntite implements IAgentite {

    private UUID id ;
    private String nom ;
    private IEntitePlateau plateau ;

    public AbstractEntite (String nom, IEntitePlateau plateau) {
        this.nom = nom;
        this.plateau = plateau;
        this.id = UUID.randomUUID();
    }
}
