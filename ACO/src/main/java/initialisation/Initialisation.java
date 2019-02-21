package initialisation;


import agent.Fourmi;
import common.Direction;
import entites.Nid;
import entites.Nourriture;
import org.w3c.dom.*;
import plateau.Plateau;
import plateau.Position;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Initialisation {

    private Plateau plateauACO;

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

    public Initialisation(){

        String xml_location="ACO/src/main/java/initialisation/";
        String xml_filename="initialisation.xml";

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
                                this.plateauACO = new Plateau(name_plateau,width_plateau,height_plateau);
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
                                                    initFourmis(type_entite,nid,posNid);
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
            nid = new Nid(this.plateauACO,name_nid);
            position_nid = new Position(position.get(0),position.get(1));
            posnid = new PositionNid(position_nid,nid);
            this.plateauACO.placerAgentite(position_nid,nid);

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
            for(int i = 0; i < nbNourriture; i++) {
                Node nourriture = nourritures.item(i);
                if(nourriture instanceof Element){
                    NodeList caracteristiques_nourriture = nourriture.getChildNodes();
                    int nbCaracteristique = caracteristiques_nourriture.getLength();
                    String name_nourriture="";
                    int quantite_nourriture=-1;
                    ArrayList<Integer> position= new ArrayList<Integer>();
                    for(int k = 0; k < nbCaracteristique; k++) {
                        Node caracteristique_nourriture = caracteristiques_nourriture.item(k);
                        if(caracteristique_nourriture instanceof Element){
                            switch (caracteristique_nourriture.getNodeName()){
                                case "nom":
                                    name_nourriture=caracteristique_nourriture.getTextContent();
                                    break;
                                case "quantite":
                                    quantite_nourriture=Integer.parseInt(caracteristique_nourriture.getTextContent());
                                case "position":
                                    position=getPosition(caracteristique_nourriture);
                                    break;
                            }
                        }
                    }
                    Position position_nourriture = new Position(position.get(0),position.get(1));
                    Nourriture food = new Nourriture(quantite_nourriture,this.plateauACO,name_nourriture);
                    this.plateauACO.placerAgentite(position_nourriture,food);
                }
            }

        }
    }

    private void initFourmis(Node type_entite, Nid nid, Position position_nid) {
        if(type_entite instanceof Element) {
            NodeList fourmis = type_entite.getChildNodes();
            int nbFourmis = fourmis.getLength();
            for(int i = 0; i < nbFourmis; i++) {
                Node fourmi = fourmis.item(i);
                if(fourmi instanceof Element){
                    NodeList caracteristiques_fourmi = fourmi.getChildNodes();
                    int nbCaracteristique = caracteristiques_fourmi.getLength();
                    String name_fourmi="";
                    String direction_intiale_fourmi="";
                    for(int k = 0; k < nbCaracteristique; k++) {
                        Node caracteristique_fourmi = fourmis.item(i);
                        if(caracteristique_fourmi instanceof Element){
                            switch (caracteristique_fourmi.getNodeName()){
                                case "nom":
                                    name_fourmi=caracteristique_fourmi.getTextContent();
                                    break;
                                case "direction_initiale":
                                    direction_intiale_fourmi = caracteristique_fourmi.getTextContent();
                            }
                        }
                    }
                    Direction d=null;
                    switch (direction_intiale_fourmi){
                        case "N":
                            d=Direction.N;
                            break;
                        case "E":
                            d=Direction.E;
                            break;
                        case "S":
                            d=Direction.S;
                            break;
                        case "O":
                            d=Direction.O;
                            break;
                        case "NO":
                            d=Direction.NO;
                            break;
                        case "NE":
                            d=Direction.NE;
                            break;
                        case "SO":
                            d=Direction.SO;
                            break;
                        case "SE":
                            d=Direction.SE;
                            break;
                    }
                    Fourmi new_fourmi = new Fourmi(name_fourmi,d,false,false,position_nid);
                    this.plateauACO.placerAgentite(position_nid,new_fourmi);
                }
            }

        }
    }


    public static void main(String [] args){
        Initialisation init = new Initialisation();
    }

}
