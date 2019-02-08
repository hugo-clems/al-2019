package entites;

import plateau.IEntitePlateau;

import java.util.UUID;

public abstract class AbstractEntite {

    private UUID id ;
    private String nom ;
    private IEntitePlateau plateau ;
}
