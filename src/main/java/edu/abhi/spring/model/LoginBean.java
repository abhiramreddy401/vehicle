package edu.abhi.spring.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class LoginBean
{
	
		@NotEmpty
		@NotNull
		private String username;

		@NotEmpty
		private String password;

		public String getPassword()
		{
				return this.password;
		}

		public String getUsername()
		{
				return this.username;
		}

		public void setUsername(String username)
		{
				this.username = username;
		}

		public void setPassword(String password)
		{
				this.password = password;
		}


}
