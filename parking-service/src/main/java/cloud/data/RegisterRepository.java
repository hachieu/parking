package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, String>{

}
