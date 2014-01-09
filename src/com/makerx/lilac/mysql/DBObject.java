package com.makerx.lilac.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.makerx.lilac.entity.InitInfo;
import com.makerx.lilac.entity.UserInfo;

public class DBObject {

	private static DBObject db;

	private DBObject() {

	}

	public static DBObject instance() {
		if (db == null) {
			db = new DBObject();
		}
		return db;
	}

	public UserInfo retrieveUserInfo(String qrcode) {
		UserInfo userInfo = null;
		Connection connection = DataAdapter.createBaeSqlConnection();
		PreparedStatement statement = null;

		try {
			statement = connection
					.prepareStatement(MysqlString.SQL_SELECT_CARDINFO_BY_QRCODE);
			statement.setString(1, qrcode);
			ResultSet userInfoRow = statement.executeQuery();
			if (userInfoRow.next()) {
				userInfo = new UserInfo();
				userInfo.setName(userInfoRow.getString(MysqlString.COLUMN_NAME));
				userInfo.setTelephone(userInfoRow
						.getString(MysqlString.COLUMN_TELEPHONE));
				userInfo.setEntranceTime(userInfoRow
						.getString(MysqlString.COLUMN_ENTRANCE_TIME));
				userInfo.setColleage(userInfoRow
						.getString(MysqlString.COLUMN_COLLEAGE));
				userInfo.setCompany(userInfoRow
						.getString(MysqlString.COLUMN_COMPANY));
			}

		} catch (SQLException e) {
			userInfo = null;
			throw new Error(e.getMessage());
		}

		return userInfo;
	}

	// TODO: return 0 = success
	// 1 = qrcode exist
	// 2 = insert fail
	public synchronized int initCard(InitInfo info) {
		int result = 0;

		Connection connection = DataAdapter.createBaeSqlConnection();
		PreparedStatement statement = null;

		try {
			statement = connection
					.prepareStatement(MysqlString.SQL_SELECT_USER_ID_BY_QRCODE);
			statement.setString(1, info.getCodeid());
			ResultSet qrcodeRow = statement.executeQuery();
			if (qrcodeRow.next()) {
				result = 1;
			} else {
				statement = connection.prepareStatement(MysqlString.SQL_INIT);
				statement.setString(1, info.getCodeid());
				statement.setString(2, info.getNfcid());
				statement.setString(3, info.getSn());
				int row = statement.executeUpdate();
				if (row == 1) {
					result = 0;
				} else {
					result = 2;
				}
			}
		} catch (SQLException e) {
			throw new Error(e.getMessage() + "\n" + info.toString());
		}

		return result;

	}

	// TODO: return 0 = success
	// 1 = qrcode not found
	// 2 = userInfo exists
	// 3 = save userInfo fail
	// 4 = sql exception
	public synchronized int saveUserInfo(UserInfo userInfo, String qrcode) {

		int code = 0;
		Connection connection = DataAdapter.createBaeSqlConnection();
		PreparedStatement statement = null;

		int userId = 0;

		try {
			statement = connection
					.prepareStatement(MysqlString.SQL_SELECT_USER_ID_BY_QRCODE);
			statement.setString(1, qrcode);
			ResultSet qrcodeRow = statement.executeQuery();
			if (qrcodeRow.next()) {
				statement = connection
						.prepareStatement(MysqlString.SQL_SELECT_CARDINFO_BY_QRCODE);

				statement.setString(1, qrcode);
				ResultSet userInfoRow = statement.executeQuery();
				if (userInfoRow.next()) {
					code = 2;// need to implement an update method
				} else {
					// (userId, name, telephone, entranceTime, colleage,
					// company)
					statement = connection
							.prepareStatement(MysqlString.SQL_INSERT_USERINFO);

					userId = qrcodeRow.getInt(MysqlString.COLUMN_USER_ID);

					statement.setInt(1,
							qrcodeRow.getInt(MysqlString.COLUMN_USER_ID));
					statement.setString(2, userInfo.getName());
					statement.setString(3, userInfo.getTelephone());
					statement.setString(4, userInfo.getEntranceTime());
					statement.setString(5, userInfo.getColleage());
					statement.setString(6, userInfo.getCompany());
					int row = statement.executeUpdate();
					if (row == 1) {
						code = 0;
					} else {
						code = 3;
					}
				}
			} else {
				code = 1;
			}
		} catch (SQLException e) {

			code = 4;
			throw new Error(e.getMessage() + "\n " + statement + "\n userid: "
					+ userId);
		}
		return code;
	}
}
