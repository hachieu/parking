package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	 User findByEmail(String email);
	 User findByActive(String email);
}
