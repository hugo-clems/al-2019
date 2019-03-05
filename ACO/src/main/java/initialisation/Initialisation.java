package initialisation;


import agent.Fourmi;
import common.Direction;
import demo.AgentDemo;
import demo.EntitePasiveDemo;
import entites.Nid;
import entites.Nourriture;
import entites.Obstacle;
import entites.Pheromone;
import org.w3c.dom.*;
import plateau.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Initialisation {

    private Application application;

    //Pour les nuances de couleur de phéromones
    private int nbFourmis;

    public Application getApplication() {
        return application;
    }

    public int getNbFourmis() {
        return this.nbFourmis;
    }

    class PositionNid{
        Position position_nid;
        Nid nid;

        public PositionNid(Position position_nid, Nid nid){
            this.position_nid=position_nid;
            this.nid=nid;
        }

        public Position getPosition_nid() {
            return position_nid;
        }

        public Nid getNid() {
            return nid;
        }
    }

    /**
     * Lis le fichier xml et initialise les différents éléments en conséquences
     * Nid - Fourmi - Nourriture - Obstacle
     * @param xml_location
     * @param xml_filename
     */
    public Initialisation(String xml_location,String xml_filename){

        Document document = null;
        DocumentBuilderFactory factory = null;
        try {
            factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xml_location+xml_filename);

            Element root = document.getDocumentElement();
            initPlateau(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initPlateau(Node n){
        if(n instanceof Element){
            if(n.getNodeName().equals("plateau")){
                NodeList caracteristiques_plateau = n.getChildNodes();
                int nbCarac = n.getChildNodes().getLength();
                String name_plateau="";
                int width_plateau=-1;
                int height_plateau=-1;
                PositionNid nid_position = null;
                for(int i = 0; i < nbCarac; i++) {
                    Node n2 = caracteristiques_plateau.item(i);
                    if(n2 instanceof Element){
                        switch (n2.getNodeName()){
                            case "nom":
                                name_plateau=n2.getTextContent();
                                break;
                            case "width":
                                width_plateau=Integer.parseInt(n2.getTextContent());
                                break;
                            case "heigth":
                                height_plateau=Integer.parseInt(n2.getTextContent());
                                this.application = new Application(name_plateau,height_plateau,width_plateau);
                                break;
                            case "liste_agentite":
                                NodeList type_entites = n2.getChildNodes();
                                int nbTypeEntite = n.getChildNodes().getLength();
                                for(int k = 0; k < nbTypeEntite; k++) {
                                    Node type_entite = type_entites.item(k);
                                    if(type_entite instanceof Element){
                                        switch (type_entite.getNodeName()){
                                            case "nid":
                                                nid_position=initNid(type_entite);
                                                break;
                                            case "nourritures":
                                                initNourritures(type_entite);
                                                break;
                                            case "fourmis":
                                                    Nid nid = nid_position.getNid();
                                                    Position posNid = nid_position.getPosition_nid();
                                                    int nb_fourmis = initFourmis(type_entite,nid,posNid);
                                                    nid.setNbFourmis(nb_fourmis);
                                                    this.nbFourmis=nb_fourmis;
                                                break;
                                            case "obstacles":
                                                initObstacles(type_entite);
                                                break;
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }




        }
    }

    private PositionNid initNid(Node type_entite) {
        PositionNid posnid = null;
        if(type_entite instanceof Element){
            Nid nid = null;
            Position position_nid = null;
            NodeList caracteristiques_nid = type_entite.getChildNodes();
            int nbCarac = caracteristiques_nid.getLength();
            String name_nid="";
            ArrayList<Integer> position = new ArrayList<Integer>();
            for(int i = 0; i < nbCarac; i++) {
                Node caracteristique_nid = caracteristiques_nid.item(i);
                if(caracteristique_nid instanceof Element){
                    switch (caracteristique_nid.getNodeName()){
                        case "nom":
                            name_nid=caracteristique_nid.getTextContent();
                            break;
                        case "position":
                            position=getPosition(caracteristique_nid);
                            break;
                    }
                }
            }
            nid = new Nid(name_nid,0);
            position_nid = new Position(position.get(0),position.get(1));
            posnid = new PositionNid(position_nid,nid);
            this.application.placerAgentite(position_nid,nid);

        }
        return posnid;
    }

    private ArrayList<Integer> getPosition(Node caracteristique) {
        int x=-1;
        int y=-1;
        if(caracteristique instanceof Element){
            NodeList coordonnees = caracteristique.getChildNodes();
            int nbCoordonnee = coordonnees.getLength();
            for(int i = 0; i < nbCoordonnee; i++) {
                Node coordonnee = coordonnees.item(i);
                if(coordonnee instanceof Element){
                    switch (coordonnee.getNodeName()){
                        case "x":
                            x = Integer.parseInt(coordonnee.getTextContent());
                            break;
                        case "y":
                            y = Integer.parseInt(coordonnee.getTextContent());
                            break;
                    }
                }
            }
        }
        ArrayList<Integer> positions = new ArrayList<Integer>();
        positions.add(x);
        positions.add(y);
        return positions;
    }

    private void initNourritures(Node type_entite) {
        if(type_entite instanceof Element) {
            NodeList nourritures = type_entite.getChildNodes();
            int nbNourriture = nourritures.getLength();
            int nb=0;
            for(int i = 0; i < nbNourriture; i++){
                Node nourriture = nourritures.item(i);
                if(nourriture instanceof Element){
                    NodeList caracteristiques_nourriture = nourriture.getChildNodes();
                    int nbCaracteristique = caracteristiques_nourriture.getLength();
                    String name_nourriture="Nourriture_"+nb;
                    nb+=1;
                    int quantite_nourriture=-1;
                    ArrayList<Integer> position= new ArrayList<Integer>();
                    for(int k = 0; k < nbCaracteristique; k++) {
                        Node caracteristique_nourriture = caracteristiques_nourriture.item(k);
                        if(caracteristique_nourriture instanceof Element){
                            switch (caracteristique_nourriture.getNodeName()){
                                case "quantite":
                                    quantite_nourriture=Integer.parseInt(caracteristique_nourriture.getTextContent());
                                case "position":
                                    position=getPosition(caracteristique_nourriture);
                                    break;
                            }
                        }
                    }
                    Position position_nourriture = new Position(position.get(0),position.get(1));
                    Nourriture food = new Nourriture(quantite_nourriture,name_nourriture);
                    this.application.placerAgentite(position_nourriture,food);
                }
            }

        }
    }

    private int initFourmis(Node type_entite, Nid nid, Position position_nid) {
        int nb_fourmis = 0;
        if(type_entite instanceof Element) {
            nb_fourmis = Integer.parseInt(type_entite.getTextContent());
            for(int k=0;k<nb_fourmis;k++) {
                String name_fourmi = "Fourmi_"+k;
                Fourmi new_fourmi = new Fourmi(name_fourmi,this.application.getIAgentPlateau(),position_nid);
                this.application.placerAgentite(position_nid,new_fourmi);
            }
        }
        return nb_fourmis;
    }

    private void initObstacles(Node type_entite) {
        if(type_entite instanceof Element) {
            NodeList obstacles = type_entite.getChildNodes();
            int nbObstacles = obstacles.getLength();
            int nb=0;
            for(int i = 0; i < nbObstacles; i++){
                Node obstacle = obstacles.item(i);
                if(obstacle instanceof Element){
                    NodeList caracteristiques_obstacle = obstacle.getChildNodes();
                    int nbCaracteristique = caracteristiques_obstacle.getLength();
                    String name_obstacle="Obstacle_"+nb;
                    nb+=1;
                    ArrayList<Integer> position= new ArrayList<Integer>();
                    for(int k = 0; k < nbCaracteristique; k++) {
                        Node caracteristique_obstacle = caracteristiques_obstacle.item(k);
                        if(caracteristique_obstacle instanceof Element){
                            switch (caracteristique_obstacle.getNodeName()){
                                case "position":
                                    position=getPosition(caracteristique_obstacle);
                                    break;
                            }
                        }
                    }
                    Position position_obstacle = new Position(position.get(0),position.get(1));
                    Obstacle obs = new Obstacle(name_obstacle);
                    this.application.placerAgentite(position_obstacle,obs);
                }
            }

        }
    }


    public static void main(String [] args){
        String xml_location="ACO/src/main/java/initialisation/";
        String xml_filename="initialisation.xml";
        Initialisation init = new Initialisation(xml_location,xml_filename);

        Application application = init.application;

        int nbFourmi=init.getNbFourmis();

        application.setCasePaint((caseToPaint, graphics, positionX, positionY, sizeMax) -> {

            Stream<IAgentite> streamFourmi = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof Fourmi);
            if (streamFourmi.count() > 0) {
                graphics.setColor(Color.CYAN);
                graphics.fillRect(positionX, positionY, sizeMax, sizeMax);
            }

            Stream<IAgentite> streamNid = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof Nid);
            if (streamNid.count() > 0) {
                graphics.setColor(Color.BLACK);
                graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
            }

            Stream<IAgentite> streamNourriture = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof Nourriture);
            if (streamNourriture.count() > 0) {
                graphics.setColor(Color.GREEN);
                graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
            }

            int pallierPheromone = nbFourmi/3;
            if(nbFourmi<3){
                pallierPheromone=1;
            }

            Stream<IAgentite> streamPheromone = caseToPaint.getAgentites().stream().filter(aCase -> aCase instanceof Pheromone);
            if (streamPheromone.count() > 0) {
                for(IAgentite agentite:caseToPaint.getAgentites()) {
                    if(agentite instanceof Pheromone){
                        Pheromone p = (Pheromone)agentite;
                        int tauxPheromone = p.getTauxPheromone();
                        if(tauxPheromone>0 && tauxPheromone<pallierPheromone){
                            graphics.setColor(Color.decode("#F0D581"));
                            graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
                        }
                        if(tauxPheromone>pallierPheromone && tauxPheromone<(pallierPheromone*2)){
                            graphics.setColor(Color.decode("#FF8903"));
                            graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
                        }
                        if(tauxPheromone>(pallierPheromone*2)){
                            graphics.setColor(Color.decode("#FF0000"));
                            graphics.fillOval(positionX + 3, positionY + 3, sizeMax - 6, sizeMax - 6);
                        }
                    }
                }

            }
        });


        application.run();
    }

}
