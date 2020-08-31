package in.jit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import in.jit.model.Uom;
@CrossOrigin
public interface UomRepository extends JpaRepository<Uom, Integer> {

}
