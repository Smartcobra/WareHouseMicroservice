package in.jit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.jit.model.RoleDTO;
import in.jit.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	@Query(value = "SELECT ROLE FROM authapp.ROLESTAB ROLE INNER JOIN  authapp.USERTAB  USER \r\n" + 
			"WHERE ROLE.ID=USER.ID AND USER.USERNAME = :username", nativeQuery = true)
	  List<String> findUserByRole(@Param("username") String username);
	  //List<RoleDTO> findUserByRole(@Param("username") String username);
}
