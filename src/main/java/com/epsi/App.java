package com.epsi;

import com.epsi.dao.Dao;
import com.epsi.view.Window;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App  {
	/**
	 * Le DAO.
	 */
	private static Dao dao;

	static {
		try {
			dao = new Dao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        Window.getWindow();
    }
}
