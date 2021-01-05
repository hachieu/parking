package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cloud.entity.Employee;
import cloud.query.EmployeeQuery;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
	@Query(value = EmployeeQuery.IDENTITY, nativeQuery = true)
	String checkIdentity(String identity);
	
	@Query(value = EmployeeQuery.PHONENUMBER, nativeQuery = true)
	String checkPhone(String phone);
	
	@Query(value = EmployeeQuery.FINDBYID, nativeQuery = true)
	Employee findByUser(String id);
}
