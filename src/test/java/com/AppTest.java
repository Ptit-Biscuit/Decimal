package com;

import com.epsi.App;
import com.epsi.dao.Dao;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    private static Dao dao;

    public void testValidatePlayer(){
        if (dao == null || dao.isClosed()) {
            try {
                dao = new Dao();
            } catch (SQLException e) {
                LogManager.getLogger(App.class).fatal("Impossible de se connecter à la BDD", e);
            }
        }
        assertEquals("Validation du joueur = ", false, dao.newPlayer("test", App.hashPassword("test")));
    }

    public void testLoginPlayer(){
        if (dao == null || dao.isClosed()) {
            try {
                dao = new Dao();
            } catch (SQLException e) {
                LogManager.getLogger(App.class).fatal("Impossible de se connecter à la BDD", e);
            }
        }
        assertEquals("Validation du joueur = ", true, dao.newPlayer("test", App.hashPassword("test")));
        assertEquals("Validation du joueur = ", true, dao.newPlayer("test", App.hashPassword("nottest")));
    }

    public void testAddScore(){
        if (dao == null || dao.isClosed()) {
            try {
                dao = new Dao();
            } catch (SQLException e) {
                LogManager.getLogger(App.class).fatal("Impossible de se connecter à la BDD", e);
            }
        }
        assertEquals("Validation du score = ", true, dao.addScore("test", 1234));
        assertEquals("Validation du score = ", false, dao.addScore("thisUserDoesNotExists", 1234));
        assertEquals("Validation du score = ", false, dao.addScore("test", -1234));
    }

}
