/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package fr.centrale.nantes.worldofecn;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.centrale.nantes.worldofecn.world.*;

/**
 * Manage database connection, saves and retreive informations
 * @author ECN
 */
public class DatabaseTools {

    private String login;
    private String password;
    private String url;
    private Connection connection;

    /**
     * Load infos
     */
    public DatabaseTools() {
        try {
            // Get Properties file
            ResourceBundle properties = ResourceBundle.getBundle(DatabaseTools.class.getPackage().getName() + ".database");

            // USE config parameters
            login = properties.getString("login");
            password = properties.getString("password");
            String server = properties.getString("server");
            String database = properties.getString("database");
            url = "jdbc:postgresql://" + server + "/" + database;

            // Mount driver
            Driver driver = DriverManager.getDriver(url);
            if (driver == null) {
                Class.forName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            // If driver is not found, cancel url
            url = null;
        }
        this.connection = null;
    }

    /**
     * Get connection to the database
     */
    public void connect() {
        if ((this.connection == null) && (url != null) && (! url.isEmpty())) {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * get Player ID
     * @param nomJoueur : le login du joueur
     * @param password : le mot de passe du joueur
     * @return
     */
    public Integer getPlayerID(String nomJoueur, String password) {
        Integer playerId = null;

        try {
            this.connect();
            String query = "SELECT id_joueur FROM joueur WHERE pseudo = ? AND mot_de_passe = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query);

            stmt.setString(1, nomJoueur);
            stmt.setString(2, password);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                playerId = res.getInt("id_joueur");
            }

            res.close();
            stmt.close();
            this.connection.close();
        } 
        catch (SQLException e) {
            return null;  
        }

        return playerId;
    }
    

    /**
     * save world as sauvegarde in database
     * @param idJoueur : l'ID du joueur dans la BD
     * @param nomPartie : le nom de la partie
     * @param nomSauvegarde : le nom de la sauvegarde
     * @param monde: le monde à enregistrer
     */
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) throws SQLException {
        // TO BE DEFINED
        
        // Create a new "partie" in database if it does not exists and link it to the player
        // Save partie's infos in the sauvegarde (height, width, ...) if necessary
        
        // Create a new sauvegarde if it does not exist for the partie
        // Update sauvegarde infos for the partie
        // Remove existing elements de jeu for the sauvegarde
        // Save world's elementdejeu in database
        
        // Save player infos and the player's creature infos for this partie
        try {
            String query;
            query = "SELECT id_partie FROM partie WHERE id_joueur = ? AND nom_partie = ?";
            PreparedStatement stmt1 = this.connection.prepareStatement(query);
            stmt1.setInt(1, idJoueur);
            stmt1.setString(2, nomPartie);
             
            ResultSet res1 = stmt1.executeQuery();
            
            if (res1.next()) {//si la partie existe
                
                int idPartie = res1.getInt("id_partie");
                res1.close();
            
                query = "SELECT id_sauvegarde FROM sauvegarde WHERE id_partie = ? AND nom_sauvegarde = ?";
                PreparedStatement stmt4 = this.connection.prepareStatement(query);
                stmt4.setInt(1, idPartie);
                stmt4.setString(2, nomSauvegarde);
            
                ResultSet res3 = stmt4.executeQuery();
                
                if (res3.next()){   //si la sauvegarde existe -> on la met à jour en supprimant les éléments du jeu associés
                    int idSauvegarde = res3.getInt("id_sauvegarde");
                    res3.close();
                    
                    query = "SELECT id_element FROM element_jeu";
                    PreparedStatement stmt5 = this.connection.prepareStatement(query);
                    ResultSet res4 = stmt5.executeQuery();
                    
                    while(res4.next()){
                        query = "DELETE FROM posseder WHERE id_element = ?";
                        PreparedStatement stmt6 = this.connection.prepareStatement(query);
                        stmt6.setInt(1, res4.getInt("id_element"));
                        query = "DELETE FROM element_jeu WHERE id_element = ?";
                        PreparedStatement stmt7 = this.connection.prepareStatement(query);
                        stmt7.setInt(1, res4.getInt("id_element"));
                    }
                    query = "UPDATE sauvegarde SET round_no = ? WHERE id_sauvegarde = ? ";
                    PreparedStatement stmt8 = this.connection.prepareStatement(query);
                    stmt8.setInt(1, monde.getRoundNo());
                    stmt8.setInt(2, idSauvegarde);
                    
                    res4.close();
                    
                }
                else{   //si la sauvegarde n'existe pas                
                    query = "INSERT INTO sauvegarde(nom_sauvegarde, id_partie, round_no) VALUES (?, ?, ?)";
                    PreparedStatement stmt2 = this.connection.prepareStatement(query);
                
                    stmt2.setString(1, nomSauvegarde);
                    stmt2.setInt(2, res1.getInt("id_partie"));
                    stmt2.setInt(3, monde.getRoundNo());
                }
            }
            else{   //si la partie n'existe pas
                query = "INSERT INTO partie(nom_partie, hauteur, largeur, id_joueur) VALUES (?,?,?,?)";
                PreparedStatement stmt2 = this.connection.prepareStatement(query);
                
                stmt2.setString(1, nomPartie);
                stmt2.setInt(2, monde.getHeight());
                stmt2.setInt(3, monde.getWidth());
                stmt2.setInt(4, idJoueur);
                
                query = "INSERT INTO sauvegarde(nom_sauvegarde, id_partie, round_no) VALUES (?, SELECT id_partie FROM partie WHERE nom_partie = ? AND id_joueur = ?,?)";
                PreparedStatement stmt3 = this.connection.prepareStatement(query);
                
                stmt3.setString(1, nomSauvegarde);
                stmt3.setString(2, nomPartie);
                stmt3.setInt(3, idJoueur);
                stmt3.setInt(4, monde.getRoundNo());
            }
            
            query = "SELECT id_sauvegarde FROM sauvegarde WHERE id_partie = (SELECT id_partie FROM partie WHERE id_joueur = ? AND nom_partie = ?) AND nom_sauvegarde = ?";
            PreparedStatement stmt4 = this.connection.prepareStatement(query);
            stmt4.setString(1, nomSauvegarde);
            ResultSet res5 = stmt4.executeQuery();
            int idSauvegarde = res5.getInt("id_sauvegarde");
            res5.close();
            
            List<ElementDeJeu> listE = monde.getListElements();
            for(int i=0; i< listE.size();i++){
                int id = listE.get(i).saveToDatabase(connection);
            }
            
        }catch (SQLException e) {
            System.out.println("problème refais stp");
        }
            
    }

    /**
     * get world sauvegarde from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @return monde
     */
    public World readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde) {
        World monde = new World();
        // TO BE DEFINED
        
        // Retreive partie infos for the player
        // Retreive sauvegarde infos for the partie

        // Retreive world infos
        // Generate object world according to the infos
        
        // Retreive element de jeu from sauvegarde
        // Generate approprite objects
        // Link objects to the world
        
        // Associate player with the player's creature

        // Return created world
        try {
            this.connect();
            
            // Set la hauteur et la largeur
            String query = "Select hauteur,largeur from partie where nom_partie =? and id_joueur =?";
            PreparedStatement stmt1 = this.connection.prepareStatement(query);

            stmt1.setString(1, nomPartie);
            stmt1.setInt(2, idJoueur);

            ResultSet res1 = stmt1.executeQuery();

            if (res1.next()) {
                monde.setHeight(res1.getInt("hauteur"));
                monde.setWidth(res1.getInt("largeur"));
            }

            res1.close();
            stmt1.close();
            
            //Set le nombre de round
            query = "Select round_no from sauvegarde join partie on sauvegarde.id_partie = partie.id_partie where nom_partie =? and nom_sauvegarde =? and id_joueur =?";
            PreparedStatement stmt2 = this.connection.prepareStatement(query);
            
            stmt2.setString(1, nomPartie);
            stmt2.setString(2, nomSauvegarde);
            stmt2.setInt(3, idJoueur);
            
            ResultSet res2 = stmt2.executeQuery();

            if (res2.next()) {
                for(int i=0; i<res2.getInt("round_no"); i++){
                    monde.nextRound();
                }
            }
            
            res2.close();
            stmt2.close();
            
            //Initialise le joueur (sauf son personnage)
            query = "Select * from joueur where id_joueur =?";
            PreparedStatement stmt4 = this.connection.prepareStatement(query);
            
            stmt4.setInt(1, idJoueur);
            
            ResultSet res4 = stmt4.executeQuery();
            
            Joueur joueur = null;
            if (res4.next()) {
               joueur = new Joueur(res4.getString("pseudo"),res4.getString("pseudo"),res4.getString("mot_de_passe"));      
            }
            
            res4.close();
            stmt4.close();
            
            
            //Récupère l'id du perso du joueur pour l'attribuer au joueur dans la prochaine requete
            query = "Select id_element from posseder join element on element.id_element = posseder.id_element join sauvegarde on element.id_sauvegarde = sauvegarde.id_sauvegarde join partie on sauvegarde.id_partie = partie.id_partie where nom_partie =? and nom_sauvegarde =? and id_joueur =? and nom_caracteristique='player'";
            PreparedStatement stmt5 = this.connection.prepareStatement(query);
            
            stmt5.setInt(1, idJoueur);
            
            ResultSet res5 = stmt5.executeQuery();
            
            int id_element_joueur = -1;
            if (res5.next()) {
               id_element_joueur = res5.getInt("id_element");      
            }
            
            res5.close();
            stmt5.close();
            
            //Set la liste des éléments
            query = "Select * from element join sauvegarde on element.id_sauvegarde = sauvegarde.id_sauvegarde join partie on sauvegarde.id_partie = partie.id_partie where nom_partie =? and nom_sauvegarde =? and id_joueur =?";
            PreparedStatement stmt3 = this.connection.prepareStatement(query);
            
            stmt3.setString(1, nomPartie);
            stmt3.setString(2, nomSauvegarde);
            stmt3.setInt(3, idJoueur);
            
            ResultSet res3 = stmt3.executeQuery();

            while(res3.next()){
                ElementDeJeu element;
                switch(res3.getString("type")){
                    case "Potion de Soin":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Potion du Guerrier":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Potion de Magie":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Potion de Mage":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Epée démoniaque":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Arc enchanté":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Arbre":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Rocher":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Nuage toxique":
                        element = new Objet(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Humain":
                        element = new Personnage(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Nain":
                        element = new Personnage(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Elfe":
                        element = new Personnage(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Gobelin":
                        element = new Personnage(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                    case "Troll":
                        element = new Personnage(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                        
                    default :
                        element = new Monstre(monde);
                        element.getFromDatabase(this.connection, idJoueur);
                        element.setPosition(new Point2D(res3.getInt("pos_x"),res3.getInt("pos_y")));
                        break;
                        
                }
                
                monde.ajouterElement(element, element.getPosition());
                
                //si l'element est celui du joueur alors on le relie au joueur et le joueur au monde
                if(res3.getInt("id_element") == id_element_joueur){
                    joueur.setPersonnage((Personnage)element);
                    monde.setPlayer(joueur);
                }
            }
            
            res3.close();
            stmt3.close();
            
            this.connection.close();
        } 
        catch (SQLException e) {
            return null;  
        }
        
        return monde;
    }


    /**
     * remove world sauvegarde from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     */
    public void removeWorld(Integer idJoueur, String nomPartie, String nomSauvegarde) {
        // TO BE DEFINED
        
        // Retreive partie infos for the player
        // Retreive sauvegarde infos for the partie

        // remove elements de jeu linked to the sauvegarde
        // remove sauvegarde
        // remove if partie has no mode sauvegarde, remove partie
        
        try{
            
            String query = "SELECT id_sauvegarde FROM sauvegarde JOIN partie on partie.id_partie = sauvegarde.id_partie WHERE nom_partie =? and nom_sauvegarde =? and id_joueur =?";
                PreparedStatement stmt1 = this.connection.prepareStatement(query);
                stmt1.setString(1, nomPartie);
                stmt1.setString(2, nomSauvegarde);
                stmt1.setInt(3, idJoueur);
            
                ResultSet res1 = stmt1.executeQuery();
                
                if (res1.next()){   //si la sauvegarde existe -> on supprime les éléments du jeu associés
                    int idSauvegarde = res1.getInt("id_sauvegarde");
                    res1.close();
                    
                    query = "SELECT id_element FROM element_jeu WHERE id_sauvegarde=?";
                    PreparedStatement stmt2 = this.connection.prepareStatement(query);
                    stmt2.setInt(1, idSauvegarde);
                    
                    ResultSet res2 = stmt2.executeQuery();
                    
                    //supprimer tout les éléments et les posseder
                    while(res2.next()){
                        query = "DELETE FROM posseder WHERE id_element = ?";
                        PreparedStatement stmt3 = this.connection.prepareStatement(query);
                        stmt3.setInt(1, res2.getInt("id_element"));
                        query = "DELETE FROM element_jeu WHERE id_element = ?";
                        PreparedStatement stmt4 = this.connection.prepareStatement(query);
                        stmt4.setInt(1, res2.getInt("id_element"));
                    }
                    
                    //supprimer la sauvegarde
                    query = "DELETE FROM sauvegarde WHERE id_sauvegarde = ?";
                    PreparedStatement stmt5 = this.connection.prepareStatement(query);
                    stmt5.setInt(1, idSauvegarde);
                    
                }
                
                
        }
        catch (SQLException e) {
             System.out.println("t'as pas réussi à remove mon pote");
        }
        
        
    }
}
