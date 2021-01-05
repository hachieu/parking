package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.entity.CheckOut;

public interface CheckOutRepository extends JpaRepository<CheckOut, String>{

}
