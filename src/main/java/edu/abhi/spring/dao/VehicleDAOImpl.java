package edu.abhi.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import edu.abhi.spring.model.Vehicle;

public class VehicleDAOImpl implements VehicleDAO {

	
private JdbcTemplate jdbcTemplate;
	
	public VehicleDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void updateVehicle(String vehicle) {
			// update
			String sql = "update vehicle set availableVehicles = availableVehicles-1 "
					+ " where vehicleName = ? ";
						
			jdbcTemplate.update(sql, vehicle);
		
	}
	
	@Override
	public void update(String vehicle) {
			// update
			String sql = "update vehicle set availableVehicles = availableVehicles+1 "
					+ " where vehicleName = ? ";
						
			jdbcTemplate.update(sql, vehicle);
		
	}
	
	@Override
	public List<Vehicle> list() {
		String sql = "select * from vehicle where availableVehicles<> 0";
		List<Vehicle> listvehicle = jdbcTemplate.query(sql, new RowMapper<Vehicle>() {

			@Override
			public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Vehicle aVehicle = new Vehicle();
	
				aVehicle.setVehicleName(rs.getString("vehicleName"));
				aVehicle.setVehicleCount(rs.getInt("availableVehicles"));
				
				return aVehicle;
			}
			
		});
		
		return listvehicle;
	}


}
