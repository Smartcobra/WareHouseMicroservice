package in.jit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import in.jit.model.Part;
import in.jit.model.PartVO;


public interface PartRepository extends JpaRepository<Part, Integer> {
	
	/*
	 * this method AJAX call.
	 */
	@Query("SELECT COUNT(P.partCode) FROM Part P WHERE P.partCode=:code")
	public Integer getPartCodeCount(String code);
	
	/*
	 * this is for rest call from purchase order
	 */
	@Query("SELECT id,partCode,baseCost FROM Part")
	public List<Object[]> getPartCodeAndBaseCost();

}
