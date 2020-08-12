package in.jit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import in.jit.model.Part;


public interface PartRepository extends JpaRepository<Part, Integer> {
	
	/*
	 * this method AJAX call.
	 */
	@Query("SELECT COUNT(P.partCode) FROM Part P WHERE P.partCode=:code")
	public Integer getPartCodeCount(String code);

}
