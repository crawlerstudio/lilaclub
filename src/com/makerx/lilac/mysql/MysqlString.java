package com.makerx.lilac.mysql;

public class MysqlString {

	public static final String TABLE_QRCODE = "QRCode";
	public static final String TABLE_USERINFO = "UserInfo";

	public static final String COLUMN_NFCID = "nfcId";
	public static final String COLUMN_QRCODE_ID = "qrcode";
	public static final String COLUMN_SN = "sn";
	public static final String COLUMN_USER_ID = "userId";

	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_TELEPHONE = "telephone";
	public static final String COLUMN_ENTRANCE_TIME = "entranceTime";
	public static final String COLUMN_COLLEAGE = "colleage";
	public static final String COLUMN_COMPANY = "company";

	// SELECT * FROM UserInfo WHERE userId = ?
	public static final String SQL_SELECT_USERINFO_BY_USER_ID = String.format(
			"SELECT * FROM %s WHERE userId = ?", TABLE_USERINFO);

	// SELECT userId FROM QRCode WHERE qrcode= ?
	public static final String SQL_SELECT_USER_ID_BY_QRCODE = String.format(
			"SELECT %s FROM QRCode WHERE qrcode= ?", COLUMN_USER_ID);

	// "SELECT * FROM UserInfo AS T1 WHERE T1.userId = (SELECT userId FROM QRCode WHERE qrcode = ?)";
	public static final String SQL_SELECT_CARDINFO_BY_QRCODE = String.format(
			"SELECT * FROM %s  WHERE %s.%s = (SELECT %s FROM %s WHERE %s = ?)",
			TABLE_USERINFO, TABLE_USERINFO, COLUMN_USER_ID, COLUMN_USER_ID,
			TABLE_QRCODE, COLUMN_QRCODE_ID);

	// INSERT INTO UserInfo (userId, name, telephone, entranceTime, colleage,
	// company) VALUES (0,'walter','12213131', '2001','cs','antiy')
	public static final String SQL_INSERT_USERINFO = String.format(
			"INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?,?,?,?,?,?)",
			TABLE_USERINFO, COLUMN_USER_ID, COLUMN_NAME, COLUMN_TELEPHONE,
			COLUMN_ENTRANCE_TIME, COLUMN_COLLEAGE, COLUMN_COMPANY);

	public static final String SQL_INIT = "INSERT INTO " + TABLE_QRCODE + "("
			+ COLUMN_QRCODE_ID + "," + COLUMN_NFCID + "," + COLUMN_SN
			+ ") VALUES (?,?,?)";
}
