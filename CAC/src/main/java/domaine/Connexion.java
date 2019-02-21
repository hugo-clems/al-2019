package domaine;

import java.io.Serializable;

public class Connexion extends Recommandable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7856893215103074382L;
	private Port fourni;
    private Port requis;

    public Connexion(Port fourni, Port requis) {
        this.fourni = fourni;
        this.requis = requis;
    }
    public Connexion() {
    }

    public Port getFourni() {
        return fourni;
    }

    public void setFourni(Port fourni) {
        this.fourni = fourni;
    }

    public Port getRequis() {
        return requis;
    }

    public void setRequis(Port requis) {
        this.requis = requis;
    }

}