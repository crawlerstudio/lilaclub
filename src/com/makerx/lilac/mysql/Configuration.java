package com.makerx.lilac.mysql;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static Configuration config = null;
	private String driverName;
	private String userName;
	private String password;
	private String mainConnName;

	private Configuration() {

//		InputStream in = Configuration.class
//				.getResourceAsStream("sql.properties");
//		Properties properties = new Properties();
		// try {
		//
		// properties.load(in);
		//
		// } catch (Exception e) {
		//
		// throw new Error(e.getMessage());
		// }

		userName = "x5013xy550";
		password = "5wk2m2ih2l0k2ykziwjlz5ix3zykjmm34li04w1m";
		driverName = "com.mysql.jdbc.Driver";
//		driverName ="org.gjt.mm.mysql.Driver";
		mainConnName = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_lilachome";

		// driverName = properties.getProperty("driverName");
		// userName = properties.getProperty("userName");
		// password = properties.getProperty("password");
		// mainConnName = properties.getProperty("mainConnName");

	}

	public static Configuration Instance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	public String getDriverName() {

		return this.driverName;
	}

	public String getPassword() {

		return this.password;
	}

	public String getUserName() {

		return this.userName;
	}

	public String getMainConnName() {

		return this.mainConnName;
	}

}
