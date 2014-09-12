package com.apartment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apartment.to.Message;
import com.apartment.to.UserTO;

public class DAOImpl {
	public UserTO authenticateUser(UserTO to) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("select * from user_login where emailId=? and password=?");
			statement.setString(1, to.getEmailId().trim());
			statement.setString(2, to.getPassword().trim());

			ResultSet resultSet = statement.executeQuery();
			UserTO to2 = new UserTO();
			while (resultSet.next()) {
				to2.setUser_id(resultSet.getInt("userid"));
				to2.setUserName(resultSet.getString("userName"));
				to2.setEmailId(resultSet.getString("emailId"));
				to2.setUserType(resultSet.getString("usertype"));
				to2.setContactNumber(resultSet.getString("contactnumber"));
				to2.setApartmentName(resultSet.getString("apartmentname"));
				to2.setApparrmentBlock(resultSet.getString("blocknumber"));
				to2.setFlatnumber(resultSet.getString("flatnumber"));
				to2.setRent(resultSet.getString("rent"));
				to2.setUserStatus(resultSet.getString("userStatus"));
				return to2;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		return null;
	}

	public boolean registerUser(UserTO userTO) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("insert into user_login (userid,userName,emailId,usertype,contactnumber,apartmentname,blocknumber,flatnumber,rent,userStatus,password) values(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, DBUtil.getMaxId("user_login", "userid") + 1);
			statement.setString(2, userTO.getUserName());
			statement.setString(3, userTO.getEmailId());
			statement.setString(4, userTO.getUserType());
			statement.setString(5, userTO.getContactNumber());
			statement.setString(6, userTO.getAppartmentName());
			statement.setString(7, userTO.getAparrmentBlock());
			statement.setString(8, userTO.getFlatnumber());
			statement.setString(9, userTO.getRent());
			statement.setString(10, "active");
			statement.setString(11, userTO.getPassword());
			int status = statement.executeUpdate();
			if (status > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		return false;
	}

	public ArrayList<UserTO> getAllUsers() {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("select * from user_login");
			ResultSet resultSet = statement.executeQuery();
			ArrayList<UserTO> userTOs = new ArrayList<UserTO>();
			while (resultSet.next()) {
				UserTO to2 = new UserTO();
				to2.setUser_id(resultSet.getInt("userid"));
				to2.setUserName(resultSet.getString("userName"));
				to2.setEmailId(resultSet.getString("emailId"));
				to2.setUserType(resultSet.getString("usertype"));
				to2.setContactNumber(resultSet.getString("contactnumber"));
				to2.setApartmentName(resultSet.getString("apartmentname"));
				to2.setApparrmentBlock(resultSet.getString("blocknumber"));
				to2.setFlatnumber(resultSet.getString("flatnumber"));
				to2.setRent(resultSet.getString("rent"));
				to2.setUserStatus(resultSet.getString("userStatus"));
				userTOs.add(to2);
			}
			return userTOs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		return null;

	}

	public boolean setMessage(Message message) {
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Messages (messageid,userid,messagedesc,messagedate)values(?,?,?,?)");
			preparedStatement.setInt(1,
					DBUtil.getMaxId("Messages", "messageid") + 1);
			preparedStatement.setInt(2, message.getUserId());
			preparedStatement.setString(3, message.getMessageDescription());
			preparedStatement.setString(4, System.currentTimeMillis() + "");
			int status = preparedStatement.executeUpdate();
			System.out.println(status);
			return status > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		return false;
	}

	public ArrayList<Message> getMessageList() {
		Connection connection = null;
		ArrayList<Message> arrayList = new ArrayList<Message>();
		try {
			connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from messages m, user_login u where m.userid=u.userid order by m.messageid");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Message message = new Message();
				message.setMessageId(resultSet.getInt("messageid"));
				message.setUserId(resultSet.getInt("userId"));
				message.setUserName(resultSet.getString("username"));
				message.setMessageDescription(resultSet
						.getString("messagedesc"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(Long.parseLong(resultSet
						.getString("messagedate")));
				String time = calendar.get(Calendar.HOUR)
						+ ":"
						+ calendar.get(Calendar.MINUTE)
						+ " "
						+ ((calendar.get(Calendar.AM_PM) == Calendar.AM) ? "AM"
								: "PM") + "\n "
						+ calendar.get(Calendar.DAY_OF_MONTH) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.YEAR);
				message.setMessageDate(time);
				arrayList.add(message);
				System.out.println(message);
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * private String getDate(String string) {
	 * 
	 * return string; }
	 */
	public boolean checkMessages(int messageId) {
		Connection connection = null;
		ArrayList<Message> arrayList = new ArrayList<Message>();
		try {
			connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from messages where messageId > "
							+ messageId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return false;
	}

}
