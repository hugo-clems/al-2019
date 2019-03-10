package plateau;

public class Position {

    /**
     * x.
     */
    private int x;

    /**
     * y.
     */
    private int y;

    /**
     * Constructeur par défaut.
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Set x.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get y.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Set y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Méthode equals d'une Position qui compare les X et Y de 2 positions.
     * @param o l'objet à tester
     * @return true si les 2 positions sont égales et false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Position that = (Position) o;

        return (x == that.getX() && y == that.getY());
    }

    @Override
    public int hashCode() {
        return x * 10 + y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
