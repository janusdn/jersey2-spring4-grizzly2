/* Copyright 2013-2014 (c) Sepior Aps, all rights reserved. */

package com.test.domain.interfaces;

import java.util.List;

import com.test.domain.User;

/**
 * UserManager
 * @author Janus Dam Nielsen
 */
public interface UserManager {

	void insertUser(User user);

	void insertUserAndFailTransation(User user);

	User getUser(String username);

	List<User> getUsers();


}
