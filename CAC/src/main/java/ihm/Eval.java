package ihm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import domaine.Composant;
import domaine.Configuration;
import domaine.Connexion;
import domaine.Port;
import interfaces.IEval;

public class Eval {

    private IEval iEval;

    public Eval(IEval iE){
        this.iEval = iE;
    }

    public void demanderNotation(){
        /*
        Données de test, a effacer !
         */
        Configuration configuration1 = new Configuration();
        //Création de composants
        Composant pc = new Composant("PC1");
        Composant videoProj = new Composant("VIDEO-PROJ1");
        //Ajout des ports
        Port pr = new Port(videoProj, "IMAGE1");
        Port pf = new Port(pc, "IMAGE1");
        pc.ajouterPortFourni(pf);
        videoProj.ajouterPortRequis(pr);

        //Création de composants
        Composant tel = new Composant("TELEPHONE1");
        Composant enceinte = new Composant("ENCEINTE1");
        //Ajout des ports
        Port pf2 = new Port(tel, "SON1");
        Port pr2 = new Port(enceinte, "SON1");
        tel.ajouterPortFourni(pf2);
        enceinte.ajouterPortRequis(pr2);

        //Création des connexions
        Connexion connexion1 = new Connexion(pf, pr);
        Connexion connexion2 = new Connexion(pf2, pr2);
        configuration1.ajouterConnexion(connexion1);
        configuration1.ajouterConnexion(connexion2);

/* ---------------------------------------------------------------------------------------------------*/
        Configuration configuration2 = new Configuration();
        //Création de composants
        Composant pc2 = new Composant("PC2");
        Composant videoProj2 = new Composant("VIDEO-PROJ2");
        //Ajout des ports
        Port pr3 = new Port(videoProj2, "IMAGE1");
        Port pf3 = new Port(pc2, "IMAGE1");
        pc.ajouterPortFourni(pf3);
        videoProj.ajouterPortRequis(pr3);

        //Création de composants
        Composant tel2 = new Composant("TELEPHONE2");
        Composant enceinte2 = new Composant("ENCEINTE2");
        //Ajout des ports
        Port pf4 = new Port(tel, "SON2");
        Port pr4 = new Port(enceinte, "SON2");
        tel.ajouterPortFourni(pf4);
        enceinte.ajouterPortRequis(pr4);

        //Création des connexions
        Connexion connexion3 = new Connexion(pf3, pr3);
        Connexion connexion4 = new Connexion(pf4, pr4);
        configuration2.ajouterConnexion(connexion3);
        configuration2.ajouterConnexion(connexion4);
        List<Configuration> configs = new ArrayList<Configuration>(){{
            add(configuration1);
            add(configuration2);
        }};

        /** Fin des données de test **/

        String entree = null;
        Scanner scan = new Scanner(System.in);
        Boolean entreeValide = false;
        //List<Configuration> configs = iEval.getConfigurations();
        for (int i=0; i<configs.size(); i++) {
            System.out.println("Configuration n°"+(i+1));
            Affichage.afficherConfiguration(configs.get(i));
        }
        for (int i=0; i<configs.size(); i++) {
            while (!entreeValide) {
                System.out.println("Aimez-vous la configuration n°"+(i+1)+"? (O/N)");
                entree = scan.nextLine();

                if (entree.equals("O") || entree.equals("o")) {
                    System.out.println("this.iEval.noterConfiguration(i, Appreciation.JAIME);"); //TODO : enlever affichage quand iEval sera implem
                    entreeValide = true;
                } else if (entree.equals("N") || entree.equals("n")) {
                    System.out.println("this.iEval.noterConfiguration(i, Appreciation.JAIMEPAS);"); //TODO : enlever affichage quand iEval sera implem
                    entreeValide = true;
                } else {
                    System.out.println("Entrée invalide, tapez O ou N");
                }
            }
            entreeValide = false;
            Affichage.afficherConfiguration(configs.get(i));
        }

    }


}
