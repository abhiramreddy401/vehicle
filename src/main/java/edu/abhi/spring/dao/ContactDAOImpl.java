package edu.abhi.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import edu.abhi.spring.model.Contact;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Contact contact) {
		if (contact.getId() > 0) {
			// update
			String sql = "UPDATE contact SET name=?, email=?, address=?, "
						+ "telephone=?, vehicle =?, noofdays=? WHERE contact_id=?";
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
					contact.getAddress(), contact.getTelephone(), contact.getVehicle(),contact.getDays(),contact.getId());
		} else {
			// insert
			String sql = "INSERT INTO contact (name, email, address, telephone, vehicle, noofdays)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
					contact.getAddress(), contact.getTelephone(), contact.getVehicle(), contact.getDays());
		}
		
	}

	@Override
	public void delete(int contactId) {
		String sql = "DELETE FROM contact WHERE contact_id=?";
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public List<Contact> list() {
		String sql = "SELECT * FROM contact";
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();
	
				aContact.setId(rs.getInt("contact_id"));
				aContact.setName(rs.getString("name"));
				aContact.setEmail(rs.getString("email"));
				aContact.setAddress(rs.getString("address"));
				aContact.setTelephone(rs.getString("telephone"));
				aContact.setVehicle(rs.getString("vehicle"));
				aContact.setDays(rs.getInt("noofdays"));
				return aContact;
			}
			
		});
		
		return listContact;
	}

	@Override
	public Contact get(int contactId) {
		String sql = "SELECT * FROM contact WHERE contact_id=" + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("contact_id"));
					contact.setName(rs.getString("name"));
					contact.setEmail(rs.getString("email"));
					contact.setAddress(rs.getString("address"));
					contact.setTelephone(rs.getString("telephone"));
					contact.setVehicle(rs.getString("vehicle"));
					contact.setDays(rs.getInt("noofdays"));
					return contact;
				}
				
				return null;
			}
			
		});
	}

}
