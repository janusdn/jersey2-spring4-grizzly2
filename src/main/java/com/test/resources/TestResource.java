package com.test.resources;

import java.security.SecureRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.domain.User;
import com.test.domain.interfaces.UserManager;

/**
 * TestResource
 * @author Janus Dam Nielsen
 */
@Component
@Path("/")
public class TestResource {

	@Autowired
	private UserManager userManager;

	@GET
	@Produces("text/html")
	public String getIt() {
		User user = new User();
		user.setId(new SecureRandom().nextInt());
		user.setName("test");
		user.setUsername("username");

		this.userManager.insertUserAndFailTransation(user);
		return "uups";
	}

	@GET
	@Path("/test")
	@Produces("text/html")
	public String test() {
		User user = new User();
		user.setId(new SecureRandom().nextInt());
		user.setName("test");
		user.setUsername("username");

		this.userManager.insertUser(user);
		return "test";
	}

}