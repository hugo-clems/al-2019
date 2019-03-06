package domaine;


import java.io.Serializable;

import java.util.UUID;

public class Connexion /*extends Recommandable*/ implements Serializable {

    private UUID uuid;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7856893215103074382L;
	private Port fourni;
	private Port requis;
    private int nbApprobation;
    private int nbOccurence;

    public Connexion(UUID uuid, Port fourni, Port requis, int nbOccurence, int nbApprobation) {
        this.uuid = uuid;
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = nbOccurence;
        this.nbApprobation = nbApprobation;
    }

    public Connexion(Port fourni, Port requis, int nbOccurence, int nbApprobation) {
        this.uuid = UUID.randomUUID();
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = nbOccurence;
        this.nbApprobation = nbApprobation;
    }

    public Connexion(Port fourni, Port requis) {
        this.uuid = UUID.randomUUID();
        this.fourni = fourni;
        this.requis = requis;
        this.nbOccurence = 0;
        this.nbApprobation = 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
				&& other.getFourni().getUuid() !=null
				&&other.getRequis() != null 
				&& other.getRequis().getUuid() !=null
				&&  other.getFourni().getUuid().equals(this.getFourni().getUuid())
				&& other.getRequis().getUuid().equals(this.getRequis().getUuid()));

	}
	
	public void incrementerNote() {
		this.nbApprobation++;
	}
	
	public void decrementerNote() {
		this.nbApprobation--;
	}


    public int getNbApprobation() {
        return nbApprobation;
    }

    public void setNbApprobation(int nbApprobation) {
        this.nbApprobation = nbApprobation;
    }

    public int getNbOccurence() {
        return nbOccurence;
    }

    public void setNbOccurence(int nbOccurence) {
        this.nbOccurence = nbOccurence;
    }

}