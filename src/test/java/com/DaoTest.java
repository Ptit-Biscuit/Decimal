package com;

import com.epsi.App;
import com.epsi.dao.Dao;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class DaoTest extends TestCase {
    private static Dao dao;

    public DaoTest() {
        if (dao == null || dao.isClosed()) {
            try {
                dao = new Dao();
                if (dao.validatePlayer("test", App.hashPassword("test"))) {
                    dao.deletePlayer("test", App.hashPassword("test"));
                }
            } catch (SQLException e) {
                LogManager.getLogger(App.class).fatal("Impossible de se connecter à la BDD", e);
            }
        }
    }

    public void testRegisterPlayer() {
        assertEquals("Validation du joueur = ", true, dao.newPlayer("test", App.hashPassword("test")));
        assertEquals("Validation du joueur (déjà existant) = ", false, dao.newPlayer("test", App.hashPassword("test")));
    }

    public void testRemovePlayer() {
        dao.newPlayer("test", App.hashPassword("test"));
        assertEquals("Suppression du joueur = ", true, dao.deletePlayer("test", App.hashPassword("test")));
        assertEquals("Suppression du joueur (non existant) = ", false, dao.deletePlayer("test", App.hashPassword("test")));
    }

    public void testLoginPlayer() {
        assertEquals("Validation du joueur (non existant) = ", false, dao.validatePlayer("test", App.hashPassword("test")));
        dao.newPlayer("test", App.hashPassword("test"));
        assertEquals("Validation du joueur = ", true, dao.validatePlayer("test", App.hashPassword("test")));
        dao.deletePlayer("test", App.hashPassword("test"));
    }

    public void testAddScore(){
        dao.newPlayer("test", App.hashPassword("test"));
        assertEquals("Validation du score = ", true, dao.addScore("test", 1234));
        assertEquals("Validation du score = ", false, dao.addScore("test", -1234));
        dao.deletePlayer("test", App.hashPassword("test"));
        assertEquals("Validation du score = ", false, dao.addScore("test", 1234));
    }
}
