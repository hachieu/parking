package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cloud.entity.CheckIn;
import cloud.query.CheckInQuery;

public interface CheckInRepository extends JpaRepository<CheckIn, String>{
	@Query(value = CheckInQuery.GETLICENCE, nativeQuery=true)
	CheckIn checkLicencePlate(String licencePlate, String status);
}
