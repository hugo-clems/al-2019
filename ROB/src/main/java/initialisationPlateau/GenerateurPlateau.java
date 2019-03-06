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
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GenerateurPlateau {

    private static Scanner reader;
    private static Application application;
    private static Position centreCollecte;
    private static Position centreDepot;
    private static int nombreColis;
    private static int taillePlateauX;
    private static int taillePlateauY;
    private static Position point2collecte;

    public static void initPlateau() {

        System.out.println("Initialisation de la taille du plateau.");
        do {
            System.out.print("Taille Plateau X (min 10) : ");
            taillePlateauX = reader.nextInt();
            System.out.print("Taille Plateau Y (min 10) : ");
            taillePlateauY = reader.nextInt();
        } while (taillePlateauX < 10 || taillePlateauY < 10);

        //Instance de Application
        application = new Application("Plateau Robot", taillePlateauY, taillePlateauX);

        // Calcul de la taille des cases en pixel
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double pixelWidth = screenSize.getWidth() / (taillePlateauX * 1.2);
        double pixelHeight = screenSize.getHeight() / (taillePlateauY * 1.2);
        int finalSize;
        if(pixelHeight < pixelWidth) finalSize = (int) pixelHeight;
        else finalSize = (int) pixelWidth;
        application.setCaseSize(finalSize);

    }

    private static boolean positionOK(Position pos) {
        return (pos.getX() > 0 && pos.getX() <= taillePlateauX - 2 && pos.getY() > 0 && pos.getY() <= taillePlateauY - 2);
    }

    private static boolean zoneOK(Position pos1, Position pos2) {
        for(int i = pos1.getX(); i <= pos2.getX(); i++) {
            for (int j = pos1.getY(); j <= pos2.getY(); j++) {
                // Faire une liste et comparer...
            }
        }
        return true;
    }

    private static void revertPos(Position pos1, Position pos2) {
        if(pos1.getX() > pos2.getX()) {
            int tmp = pos1.getX();
            pos1.setX(pos2.getX());
            pos2.setX(tmp);
        }

        if(pos1.getY() > pos2.getY()) {
            int tmp = pos1.getY();
            pos1.setY(pos2.getY());
            pos2.setY(tmp);
        }
    }

    // On vérifie que la zone de dépôt ne soit pas dans la zone de collecte
    private static boolean checkDepot(Position pos) {
        return (point2collecte.getX() < pos.getX() || point2collecte.getY() < pos.getY());
    }

    private static void initZones(String nom) {
        Position point1;
        Position point2;
        boolean depotOK;

        System.out.println("Initialisation de la zone de " + nom + ".");
        do {
            depotOK = true;

            System.out.print("Zone " + nom + " Point 1 X : ");
            int point1X = reader.nextInt();
            System.out.print("Zone " + nom + " Point 1 Y : ");
            int point1Y = reader.nextInt();
            point1 = new Position(point1X, point1Y);

            System.out.print("Zone " + nom + " Point 2 X : ");
            int point2X = reader.nextInt();
            System.out.print("Zone " + nom + " Point 2 Y : ");
            int point2Y = reader.nextInt();
            point2 = new Position(point2X, point2Y);

            revertPos(point1, point2);
            if(nom.equals("Depot")) depotOK = checkDepot(point1);
        } while (!positionOK(point1) || !positionOK(point2) || !depotOK);

        Position centreZone = initZone(point1, point2, nom);

        if(nom.equals("Collecte")) {
            centreCollecte = centreZone;
            point2collecte = point2;
        }
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
            Supplier<Stream<IAgentite>> iAgentiteStream = () -> caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof ZoneCollecte);
            if (iAgentiteStream.get().count() > 0) {
                graphics.setColor(Color.ORANGE);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }iAgentiteStream = () -> caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof ZoneDepot);
            if (iAgentiteStream.get().count() > 0) {
                graphics.setColor(Color.BLUE);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }
            iAgentiteStream = () -> caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof Robot);
            if (iAgentiteStream.get().count() > 0) {
                graphics.setColor(Color.GRAY);
                graphics.fillRoundRect(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6, 5, 5);
                iAgentiteStream.get().forEach(iAgentite -> {
                    if (((Robot) iAgentite).getEntitePortee() != null) {
                        graphics.setColor(Color.LIGHT_GRAY);
                        graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
                    }
                });
            }
            iAgentiteStream.get().close();
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
            if(application.placerAgentite(posObs, new Obstacle("Obstacle"))) System.out.println("Placement OK !");
            else System.out.println("Placement KO...");
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
            if(!application.placerAgentite(randomPos, new Robot("Robot", application.getIAgentPlateau(), Direction.N))) i--;
        }
    }

    private static void initColis() {
        System.out.print("Nombre de Colis (valeur négative pour infini) : ");
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
    }
}
