package com.apartment.webservice;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.apartment.dao.DAOImpl;
import com.apartment.to.Message;
import com.apartment.to.UserTO;
import com.google.gson.Gson;

@Path("/ws")
public class AuthenticationWebService {

	@Path("/login")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticatUser(UserTO userto) {
		DAOImpl daoImpl = new DAOImpl();
		System.out.println(userto);
		userto = daoImpl.authenticateUser(userto);
		return Response.status(200).entity(new Gson().toJson(userto)).build();
	}

	@Path("/registerUser")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response registerUser(UserTO userTO) {
		DAOImpl daoImpl = new DAOImpl();
		boolean status = daoImpl.registerUser(userTO);
		return Response.status(200).entity(new Gson().toJson(status)).build();
	}

	@Path("/getUserList")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response getUsersList() {
		DAOImpl daoImpl = new DAOImpl();
		ArrayList<UserTO> userTOs = daoImpl.getAllUsers();
		return Response.status(200).entity(new Gson().toJson(userTOs)).build();
	}

	@Path("/sendMessage")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response sendMessage(Message message) {
		System.out.println(message);
		DAOImpl daoImpl = new DAOImpl();
		boolean status = daoImpl.setMessage(message);
		return Response.status(200).entity(new Gson().toJson(status)).build();
	}

	@Path("/getMessages")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response getMessages() {
		DAOImpl daoImpl = new DAOImpl();
		ArrayList<Message> messages = daoImpl.getMessageList();
		return Response.status(200).entity(new Gson().toJson(messages)).build();
	}

	@Path("/checkMessages")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response checkMessages(Message message) {
		DAOImpl daoImpl = new DAOImpl();
		//System.out.println(message);
		//System.out.println("in checkMessage" + message.getMessageId());
		boolean status = daoImpl.checkMessages(message.getMessageId());
		return Response.status(200).entity(new Gson().toJson(status)).build();
	}

	/*@Path("/getUserList")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response getUserList() {
		DAOImpl daoImpl = new DAOImpl();
		ArrayList<UserTO> userTOs = daoImpl.getUserList();
		return Response.status(200).entity(new Gson().toJson(userTOs))
				.build();
	}*/
}
