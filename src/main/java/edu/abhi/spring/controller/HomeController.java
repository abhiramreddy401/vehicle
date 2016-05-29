package edu.abhi.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import edu.abhi.spring.dao.ContactDAO;
import edu.abhi.spring.dao.UserDAO;
import edu.abhi.spring.dao.VehicleDAO;
import edu.abhi.spring.model.Contact;
import edu.abhi.spring.model.LoginBean;
import edu.abhi.spring.model.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private VehicleDAO vehicleDAO;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
	{
		ModelAndView model = new ModelAndView("login");
		//LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}


	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("loginBean")LoginBean loginBean,
			BindingResult result)
	{

		ModelAndView model= null;
		if (result.hasErrors()) {
			
			model = new ModelAndView("login");
			model.addObject("loginBean",loginBean);
			return model;
		}

		try
		{
			LoginBean user = userDAO.getValidUser(loginBean.getUsername(), loginBean.getPassword());
			if(null != user)
			{
				List<Contact> listContact = contactDAO.list();

				request.setAttribute("loggedInUser", loginBean.getUsername());

				model = new ModelAndView("home");
				model.addObject("listContact", listContact);
			}
			else
			{
				model = new ModelAndView("login");
				request.setAttribute("message", "Invalid credentials!!");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}

	/*@RequestMapping(value="/contact")
	public ModelAndView listContact(ModelAndView model) throws IOException{
		List<Contact> listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("home");

		return model;
	}*/

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);

		try
		{
			List<Vehicle> listVehicle = vehicleDAO.list();
			model.addObject("listVehicle", listVehicle);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		model.setViewName("ContactForm");
		return model;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
		ModelAndView model= null;
		if (result.hasErrors()) {
			
			List<Vehicle> listVehicle = vehicleDAO.list();
			model = new ModelAndView("ContactForm");
			model.addObject("listVehicle", listVehicle);
			model.addObject("contact", contact);
			return model;
		}
		try
		{
			contactDAO.saveOrUpdate(contact);		

			vehicleDAO.updateVehicle(contact.getVehicle());
			List<Contact> listContact = contactDAO.list();

			model = new ModelAndView("home");
			model.addObject("listContact", listContact);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		ModelAndView model= null;
		try
		{

			int contactId = Integer.parseInt(request.getParameter("id"));

			Contact contact = contactDAO.get(contactId);

			vehicleDAO.update(contact.getVehicle());
			contactDAO.delete(contactId);		

			List<Contact> listContact = contactDAO.list();

			model = new ModelAndView("home");
			model.addObject("listContact", listContact);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		try
		{
			List<Vehicle> listVehicle = vehicleDAO.list();
			model.addObject("listVehicle", listVehicle);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addObject("contact", contact);

		return model;
	}
}
