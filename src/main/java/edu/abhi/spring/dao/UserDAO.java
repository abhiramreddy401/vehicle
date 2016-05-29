package edu.abhi.spring.dao;

import java.sql.SQLException;

import edu.abhi.spring.model.LoginBean;

public interface UserDAO
{
		public LoginBean getValidUser(String username, String password) throws SQLException;
}
