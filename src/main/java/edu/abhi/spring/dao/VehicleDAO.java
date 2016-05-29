package edu.abhi.spring.dao;

import java.util.List;

import edu.abhi.spring.model.Vehicle;



public interface VehicleDAO {

	public void updateVehicle(String vehicle);
	public void update(String vehicle);
	public List<Vehicle> list();
	
	
}
