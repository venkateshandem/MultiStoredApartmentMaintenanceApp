package com.apartment.to;


public class Message {
	private int messageId;
	private int userId;
	private String userName;
	private String messageDescription;
	private String messageDate;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessageDescription() {
		return messageDescription;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", userId=" + userId
				+ ", userName=" + userName + ", messageDescription="
				+ messageDescription + ", messageDate=" + messageDate + "]";
	}

}
