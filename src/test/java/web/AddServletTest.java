package web;

import java.text.DateFormat;
import java.util.Date;

import nure.cs.borodin.usermanagment.User;

public class AddServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	public void testAdd(){
		Date date = new Date();
		User user = new User(new Long(1), "Nikita", "Borodin", date);
		user.setId(null);
		User createdUser = new User(new Long(1), "Nikita", "Borodin", date);
		getMockUserDao().expectAndReturn("CreateUser", user, createdUser);
		
		addRequestParameter("firstName", "Nikita");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
	}
	
	public void testAddEmptyFirstName(){
		Date date = new Date();
		
		addRequestParameter("id", "1");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
	
	public void testAddEmptyLastName(){
		Date date = new Date();
		
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Nikita");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
	
	public void testAddEmptyDate(){
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Nikita");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
	
	public void testAddDateValidate(){
        Date date = new Date();
		
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Nikita");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("date", "wrongFormatDate");
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
}
