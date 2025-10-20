/* --------------------------------------------------------------------------------
 * WoE
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package fr.centrale.nantes.worldofecn.world;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ECN
 */
public class Monstre extends Creature {
    
    private static final String RACELOUP = "Loup";
    private static final String RACEOURS = "Ours";
    private static final String RACEBUFFLE = "Buffle";
    private static final String RACEJAGUAR = "Jaguar";
    private static final String RACEVACHE = "Vache";
    private static final String RACELAPIN = "Lapin";

    private String race;

    private static List<String> racesList;

    /**
     *
     */
    public static void init() {
        racesList = new ArrayList<String>();
        racesList.add(RACELOUP);
        racesList.add(RACEOURS);
        racesList.add(RACEBUFFLE);
        racesList.add(RACEJAGUAR);
        racesList.add(RACEVACHE);
        racesList.add(RACELAPIN);
    }
    
    /**
     * Get nb races
     * @return
     */
    public static int getNbRaces() {
        if (racesList != null) {
            return racesList.size();
        }
        return 0;
    }

    /**
     *
     * @param race
     * @return
     */
    public static String intToRace(int race) {
        if (race < racesList.size()) {
            return racesList.get(race);
        }
        return "";
    }

    /**
     *
     * @param world
     */
    public Monstre(World world) {
        super(world);
    }

    /**
     * 
     * @return 
     */
    public String getRace() {
        return race;
    }

    /**
     * 
     * @param race 
     */
    public void setRace(String race) {
        this.race = race;
    }
    
    /**
     *
     */
    public void setRaceCaracteristiques() {
        switch (this.getRace()) {
            case RACELOUP :
                this.setPourcentAttaque(30.0f);
                this.setDegatsAttaque(3.0f);
                this.setPourcentEsquive(20.0f);
                this.setAbsorbe(2.0f);
                this.setPVieMax(20.0f);
                break;
            case RACEOURS :
                this.setPourcentAttaque(30.0f);
                this.setDegatsAttaque(5.0f);
                this.setPourcentEsquive(5.0f);
                this.setAbsorbe(3.0f);
                this.setPVieMax(40.0f);
                break;
            case RACEBUFFLE :
                this.setPourcentAttaque(20.0f);
                this.setDegatsAttaque(5.0f);
                this.setPourcentEsquive(0.0f);
                this.setAbsorbe(2.0f);
                this.setPVieMax(30.0f);
                break;
            case RACEJAGUAR :
                this.setPourcentAttaque(20.0f);
                this.setDegatsAttaque(2.0f);
                this.setPourcentEsquive(20.0f);
                this.setAbsorbe(1.0f);
                this.setPVieMax(20.0f);
                break;
            case RACEVACHE :
                this.setPourcentAttaque(20.0f);
                this.setDegatsAttaque(4.0f);
                this.setPourcentEsquive(0.0f);
                this.setAbsorbe(2.0f);
                this.setPVieMax(20.0f);
                break;
            case RACELAPIN :
                this.setPourcentAttaque(1.0f);
                this.setDegatsAttaque(0.1f);
                this.setPourcentEsquive(40.0f);
                this.setAbsorbe(0.5f);
                this.setPVieMax(5.0f);
                break;
            default : // UNDEFINED
                this.setPourcentAttaque(0.0f);
                this.setDegatsAttaque(0.0f);
                this.setPourcentEsquive(0.0f);
                this.setAbsorbe(0.0f);
                this.setPVieMax(0.0f);
                break;
        }
        this.setPVie(this.getPVieMax());
    }


    @Override
    public Integer saveToDatabase(Connection connection, int idSauvegarde) {
        Integer id = -1;
        String query;

        try {
            query = "INSERT INTO element_jeu(pos_x,pos_y,type_element,id_sauvegarde) VALUES (?,?,?,?)";
            PreparedStatement stmt1 = connection.prepareStatement(query);
            stmt1.setInt(1, this.getPosition().getX());
            stmt1.setInt(2, this.getPosition().getY());
            stmt1.setString(3, this.getRace());
            stmt1.setInt(4, idSauvegarde);
            stmt1.executeUpdate();
            stmt1.close();
            
            
            query = "SELECT max(id_element) FROM element_jeu WHERE id_sauvegarde=?";
            PreparedStatement stmt2 = connection.prepareStatement(query);
            stmt2.setInt(1, idSauvegarde);
            ResultSet res1 = stmt2.executeQuery();
            res1.next();
            int idElement = res1.getInt("id_element");
            res1.close();
            stmt2.close();
            
            query = "INSERT INTO posseder(id_element,nom_caracteristique,valeur_float) VALUES (?,?,?)";
            PreparedStatement stmt3 = connection.prepareStatement(query);
            stmt3.setInt(1, idElement);
            stmt3.setString(2, "PVie");
            stmt3.setFloat(3, this.getPVie());
            stmt3.executeUpdate();
            stmt3.close();
            
        } catch (SQLException ex) {
        }
        return id;
    }

    @Override
    public void getFromDatabase(Connection connection, Integer id) {
        try{
            
            String query = "Select type from element where id_element =?";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setInt(1, id);
            
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                this.setRace(res.getString("type"));
                this.setRaceCaracteristiques();
            }
            
                        String query2 = "Select * from posseder where id_element =?";
            PreparedStatement stmt2 = connection.prepareStatement(query2);
            
            stmt2.setInt(1, id);
            
            ResultSet res2 = stmt2.executeQuery();
            
            while (res2.next()) {
                switch(res2.getString("nom_caracteritique")){
                    case "PVie" :
                        this.setPVie(res2.getFloat("valeur_float"));
                        break;
                }
            }
            
            
        }catch (SQLException e) {
            
        }
       
    }

    @Override
    public void removeFromDatabase(Connection connection, Integer id) {
    }
    
}
