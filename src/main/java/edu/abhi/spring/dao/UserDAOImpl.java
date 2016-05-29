package edu.abhi.spring.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.abhi.spring.model.LoginBean;


public class UserDAOImpl implements UserDAO
{
	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public LoginBean getValidUser(String username, String password)
	{
		String query = "Select * from user where username = ? and password = ?";

		return jdbcTemplate.query(query,new Object[] { username, password },  new ResultSetExtractor<LoginBean>() {

			@Override
			public LoginBean extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				if (rs.next()) {
					LoginBean user = new LoginBean();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					return user;
				}

				return null;
			}

		});
	}
}