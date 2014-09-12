package com.apartment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String UNAME = "kalesha";
	private static String PWD = "kalesha";
	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";

	public DBUtil() {
	}

	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, UNAME, PWD);
		} catch (SQLException e) {
			e.printStackTrace();
			// dblogger.error(DBConstants.GET_CONN_ERR);
		}
		if (con == null) {
			throw new Exception("Database connection failed..");
		}
		return con;
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null && (!connection.isClosed()))
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getMaxId(String tableName, String columnName) {
		Connection connection = null;
		try {
			// System.out.println(tableName + "    " + columnName);
			connection = getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select max( " + columnName + " ) from "
							+ tableName);

			// preparedStatement.setString(1, columnName);
			// preparedStatement.setString(2, tableName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return 0;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
