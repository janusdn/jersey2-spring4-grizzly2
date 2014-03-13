/* Copyright 2013-2014 (c) Sepior Aps, all rights reserved. */

package com.test.storage.interfaces;

import java.util.List;

import com.test.domain.User;

/**
 * UserDao
 * @author Janus Dam Nielsen
 */
public interface UserDao {

	void insertUser(User user);

	User getUser(String username);

	List<User> getUsers();
}
