package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.jit.model.GrnDtl;

public interface grnDtlRepo extends JpaRepository<GrnDtl, Integer> {

	
	@Query("SELECT dtl FROM GrnDtl dtl INNER JOIN dtl.grn AS grn WHERE grn.id=:grnId")
	public List<GrnDtl> getAllDtlsByGrnId(Integer grnId);
    
	
	@Modifying
	@Query("UPDATE GrnDtl set status=:status WHERE id=:id")
	public void updateStatusByGrnDtlId(String status, Integer id);
}
