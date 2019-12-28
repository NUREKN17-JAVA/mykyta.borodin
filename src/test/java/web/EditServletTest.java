package web;

import java.text.DateFormat;
import java.util.Date;

import nure.cs.borodin.usermanagment.User;

public class EditServletTest extends MockServletTestCase {

	protected void setUp() throws Exception{
		super.setUp();
		createServlet(EditServlet.class);
	}
	
	public void testEdit(){
		Date date = new Date();
		User user = new User(new Long(1), "Nikita", "Borodin", date);
		getMockUserDao().expect("UpdateUser", user);
		
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Yaroslav");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
	}
	
	public void testEditEmptyFirstName(){
		Date date = new Date();
		
		addRequestParameter("id", "1");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
	
	public void testEditEmptyLastName(){
		Date date = new Date();
		
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Nikita");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
	
	public void testEditEmptyDate(){
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Nikita");
		addRequestParameter("lastName", "Borodin");
		addRequestParameter("okButton");
		doPost();
		String errorMsg = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Cant find a message in session scope", errorMsg);
	}
	
	public void testEditDateValidate(){
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
