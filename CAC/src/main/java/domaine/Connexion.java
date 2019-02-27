package domaine;

public class Connexion extends Recommandable {

    private Port fourni;
    private Port requis;

    public Connexion(Port fourni, Port requis) {
        this.fourni = fourni;
        this.requis = requis;
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
    public String toString(){
        return ("[" + this.getFourni().getComposant().getNom() + "][PROV]" + this.getFourni().getService()
                + " - [" + this.getRequis().getComposant().getNom() + "][REQ]" + this.getRequis().getService());
    }

}