package ihm;

import domaine.Configuration;
import domaine.Connexion;

public class Affichage {

    public static void afficherConfiguration(Configuration c) {
        c.getConnexions().forEach(connexion -> afficherConnexion(connexion));
    }

    public static void afficherConnexion(Connexion c) {
        System.out.println(c.toString());
    }

}
