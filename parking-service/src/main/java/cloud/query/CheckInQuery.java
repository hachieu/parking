package cloud.query;

public interface CheckInQuery {
	String GETLICENCE = "SELECT * FROM CHECK_IN WHERE LICENCE_PLATE = ?1 AND STATUS = ?2";
}
