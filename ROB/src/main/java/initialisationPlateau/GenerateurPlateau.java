package initialisationPlateau;

import common.Direction;
import entites.Obstacle;
import entites.Robot;
import entites.ZoneCollecte;
import entites.ZoneDepot;
import plateau.Application;
import plateau.IAgentite;
import plateau.Position;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class GenerateurPlateau {

    private static Scanner reader;
    private static Application application;
    private static Position centreCollecte;
    private static Position centreDepot;
    private static int nombreColis;
    private static int taillePlateauX;
    private static int taillePlateauY;

    public static void initPlateau() {

        System.out.print("Taille Plateau X : ");
        taillePlateauX = reader.nextInt();
        System.out.print("Taille Plateau Y : ");
        taillePlateauY = reader.nextInt();

        //Instance de Application
        application = new Application("Plateau Robot", taillePlateauY, taillePlateauX);
    }

    private static void initZones(String nom) {

        System.out.print("Zone " + nom + " Point 1 X : ");
        int point1X = reader.nextInt();
        System.out.print("Zone " + nom + " Point 1 Y : ");
        int point1Y = reader.nextInt();
        Position point1 = new Position(point1X, point1Y);

        System.out.print("Zone " + nom + " Point 2 X : ");
        int point2X = reader.nextInt();
        System.out.print("Zone " + nom + " Point 2 Y : ");
        int point2Y = reader.nextInt();
        Position point2 = new Position(point2X, point2Y);

        Position centreZone = initZone(point1, point2, nom);

        if(nom.equals("Collecte")) centreCollecte = centreZone;
        else centreDepot = centreZone;
    }

    public static Position initZone(Position case1, Position case2, String nom) {

        for(int i = case1.getX(); i <= case2.getX(); i++) {
            for(int j = case1.getY(); j <= case2.getY(); j++) {
                if(nom.equals("Collecte")) application.placerAgentite(new Position(i, j), new ZoneCollecte(nom));
                else application.placerAgentite(new Position(i, j), new ZoneDepot(nom));
            }
        }

        int centreX = (case1.getX() + case2.getX()) / 2;
        int centreY = (case1.getY() + case2.getY()) / 2;

        System.out.println("Centre de la zone de " + nom + " : " + centreX + ", " + centreY);
        return new Position(centreX, centreY);
    }

    private static void affichage() {
        application.setCasePaint((caseToPaint, graphics, positionX, positionY, sizeMax) -> {
            Stream<IAgentite> iAgentiteStream = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof ZoneCollecte);
            if (iAgentiteStream.count() > 0) {
                graphics.setColor(Color.ORANGE);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }
            iAgentiteStream = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof ZoneDepot);
            if (iAgentiteStream.count() > 0) {
                graphics.setColor(Color.BLUE);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }
            iAgentiteStream = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof Robot);
            if (iAgentiteStream.count() > 0) {
                graphics.setColor(Color.GRAY);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }
        });
    }

    private static void initObstacles() {
        Position posObs = new Position(-1, -1);

        while(posObs.getX() != 0 && posObs.getX() != 0) {
            System.out.println("Entrez un 0 pour terminer.");
            System.out.print("Obstacle X : ");
            posObs.setX(reader.nextInt());
            System.out.print("Obstacle Y : ");
            posObs.setY(reader.nextInt());
            if(posObs.getX() == 0 || posObs.getY() == 0) break;
            application.placerAgentite(posObs, new Obstacle("Obstacle"));
        }
    }

    private static void initRobots() {
        int nombreRobots;
        int randomX;
        int randomY;
        Position randomPos = new Position(0, 0);

        System.out.print("Nombre de Robots : ");
        nombreRobots = reader.nextInt();

        for(int i = 0; i < nombreRobots; i++) {
            randomPos.setX(ThreadLocalRandom.current().nextInt(1, taillePlateauX));
            randomPos.setY(ThreadLocalRandom.current().nextInt(1, taillePlateauY));
            if(!application.placerAgentite(randomPos, new Robot("Robot", application.getIAgentPlateau(), Direction.N, centreCollecte, centreDepot))) i--;
        }
    }

    private static void initColis() {
        System.out.print("Nombre de Colis : ");
        nombreColis = reader.nextInt();
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void main(final String[] args) throws IOException {

        System.out.print("Utiliser le fichier de demo ? Y/N : ");
        char choice = (char) System.in.read();

        if(choice == 'Y') {
            String input = readFile("./ROB/src/main/resources/demo.cfg", StandardCharsets.UTF_8);
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
        }

        reader = new Scanner(System.in);

        initPlateau();
        initZones("Collecte");
        initZones("Depot");
        initObstacles();
        initRobots();
        initColis();
        affichage();

        // Lancement de l'interface 2D
        application.run();

        /*
        if(x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }

        if(y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        */


    }
}
