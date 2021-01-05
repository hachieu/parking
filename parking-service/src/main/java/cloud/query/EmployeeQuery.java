package cloud.query;

public interface EmployeeQuery {
	//get identity
	String IDENTITY  = "SELECT IDENTITY FROM EMPLOYEE WHERE IDENTITY = ?";
	// get phone number
	String PHONENUMBER = "SELECT PHONE FROM EMPLOYEE WHERE PHONE = ?";
	
	String FINDBYID = "SELECT * FROM EMPLOYEE WHERE USER_ID = ?";
}
