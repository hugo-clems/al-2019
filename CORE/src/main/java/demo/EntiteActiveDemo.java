package demo;

import entites.AbstractEntiteActive;

public class EntiteActiveDemo extends AbstractEntiteActive {

    private int value;

    /**
     * Constructeur par défaut.
     *
     * @param nom nom de l'agent
     */
    public EntiteActiveDemo(String nom, int value) {
        super(nom);
        this.value = value % 3;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value % 3;
    }

    @Override
    public void ActionTour() {
        this.value = (this.value + 1) % 3;
    }
}
