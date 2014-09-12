package com.apartment.to;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

@XmlRootElement
public class UserTO {
	private int user_id;
	private String userName;
	private String emailId;
	private String contactNumber;
	private String apartmentName;
	private String aparrmentBlock;
	private String flatnumber;
	private String address;
	private String rent;
	private String password;
	private String userType;
	private String userStatus;

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAppartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getAparrmentBlock() {
		return aparrmentBlock;
	}

	public void setApparrmentBlock(String apparrmentBlock) {
		this.aparrmentBlock = apparrmentBlock;
	}

	public String getFlatnumber() {
		return flatnumber;
	}

	public void setFlatnumber(String flatnumber) {
		this.flatnumber = flatnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	@Override
	public String toString() {
		return "UserTO [user_id=" + user_id + ", userName=" + userName
				+ ", emailId=" + emailId + ", contactNumber=" + contactNumber
				+ ", apartmentName=" + apartmentName + ", aparrmentBlock="
				+ aparrmentBlock + ", flatnumber=" + flatnumber + ", address="
				+ address + ", rent=" + rent + ", password=" + password
				+ ", userType=" + userType + ", userStatus=" + userStatus + "]";
	}

	public static void main(String[] args) {
		UserTO to = new UserTO();
		to.setEmailId("kaleshav786@gmail.com");
		to.setPassword("kalesha");
		to.setUser_id(2);
		to.setUserName("Pranay");
		to.setUserType("user");
		to.setContactNumber("8121902962");
		to.setApartmentName("Heven");
		to.setApparrmentBlock("A");
		to.setFlatnumber("A103");
		to.setRent("15000");
		System.out.println(new Gson().toJson(to));
	}
}
