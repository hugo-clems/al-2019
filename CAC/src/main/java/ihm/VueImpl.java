package ihm;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import impl.Noyau;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VueImpl implements IVue{

    @Override
    public List<Composant> entrerConfiguration() {
        Scanner scan = new Scanner(System.in);
        int nbComposants=0;
        List<Composant> composants = new ArrayList<>();
        String entree = null;
        int nb = 0;
        Composant c;
        Port p;
        System.out.println("Combien de composants ?");
        nbComposants = scan.nextInt();
        scan.nextLine();
        for (int i=0; i<nbComposants; i++){
            System.out.println("Entrez nom composant n°"+(i+1)+":");
            entree = scan.nextLine();
            c = new Composant(entree);
            System.out.println("Combien de ports requis?");
            nb = scan.nextInt();
            scan.nextLine();
            for (int j=0; j<nb; j++){
                System.out.println("Entrez nom service pour port requis n°"+(j+1)+" : ");
                entree = scan.nextLine();
                p = new Port(c,entree);
                c.ajouterPortRequis(p);
            }
            System.out.println("Combien de ports fournis?");
            nb = scan.nextInt();
            scan.nextLine();
            for (int j=0; j<nb; j++){
                System.out.println("Entrez nom service pour port fourni n°"+(j+1)+" : ");
                entree = scan.nextLine();
                p = new Port(c,entree);
                c.ajouterPortRequis(p);
            }
            composants.add(c);

        }
        return composants;
    }



    public static void main (String[] args){
        VueImpl vue = new VueImpl();
        List<Composant> conf = vue.entrerConfiguration();
        conf.toString();
        Eval eval = new Eval(null);
        eval.demanderNotation();

    }

}
