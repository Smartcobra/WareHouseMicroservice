package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.jit.model.WhUserType;

@Repository
public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {
	
	@Query("SELECT count(WH.userMail) from WhUserType WH where WH.userMail=:mail")
	public Integer getWhUserTypeCount(String mail);
	
	@Query("SELECT WH.id,WH.userCode from WhUserType WH where WH.userType='Vendor'")
	public List<Object[]> getWhUseTypeIdCode ();

}
