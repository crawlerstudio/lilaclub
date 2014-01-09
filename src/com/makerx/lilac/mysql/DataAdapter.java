package com.makerx.lilac.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAdapter {

	public static Connection createBaeSqlConnection() {

		Connection connection = null;
		Configuration pro = Configuration.Instance();

		

			try {
				Class.forName(pro.getDriverName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new Error(e.getMessage());
			}
			try {
				connection = DriverManager.getConnection(pro.getMainConnName(),
						pro.getUserName(), pro.getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Error(e.getMessage());
			}

		
		return connection;
	}
}
