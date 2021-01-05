package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;

import cloud.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
