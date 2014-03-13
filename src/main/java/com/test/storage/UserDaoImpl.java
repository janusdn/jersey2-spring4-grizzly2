/* Copyright 2013-2014 (c) Sepior Aps, all rights reserved. */

package com.test.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.domain.User;
import com.test.storage.interfaces.UserDao;

/**
 * UserDaoImpl
 * @author Janus Dam Nielsen
 */
@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public void insertUser(User user) {
		this.getJdbcTemplate().update(
				"INSERT INTO USERS (ID, USERNAME, NAME) VALUES (?, ?, ?)",
				new Object[] {
						user.getId(),
						user.getUsername(),
						user.getName()
				}
				);
	}

	@Override
	public User getUser(String username) {
		User user = this.getJdbcTemplate().
				queryForObject(
						"SELECT * FROM USERS WHERE USERNAME = ?",
						new Object[] { username },
						new UserMapper()
						);
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = this.getJdbcTemplate().
				query("SELECT * FROM USERS",
						new UserMapper()
						);
		return users;
	}

	private class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			User user = new User();
			user.setId(rs.getInt("ID"));
			user.setUsername(rs.getString("USERNAME"));
			user.setName(rs.getString("NAME"));
			return user;
		}

	}

}
