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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fourni == null) ? 0 : fourni.hashCode());
		result = prime * result + ((requis == null) ? 0 : requis.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connexion other = (Connexion) obj;
		if (fourni == null) {
			if (other.fourni != null)
				return false;
		} else if (!fourni.equals(other.fourni))
			return false;
		if (requis == null) {
			if (other.requis != null)
				return false;
		} else if (!requis.equals(other.requis))
			return false;
		return true;
	}
	public boolean equalsIdPort(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connexion other = (Connexion) obj;
		return (other.getFourni() != null 
				&& other.getFourni().getId() !=null 
				&&other.getRequis() != null 
				&& other.getRequis().getId() !=null 
				&&  other.getFourni().getId().equals(this.getFourni().getId()) 
				&& other.getRequis().getId().equals(this.getRequis().getId()));
		
		}
	}
    

}