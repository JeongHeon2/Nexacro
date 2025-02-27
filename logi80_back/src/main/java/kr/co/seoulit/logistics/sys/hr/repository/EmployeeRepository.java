package kr.co.seoulit.logistics.sys.hr.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import kr.co.seoulit.logistics.sys.hr.to.EmployeeTO;



public interface EmployeeRepository extends CrudRepository<EmployeeTO, String> {

	List<EmployeeTO> findAll();
	
	void deleteByEmpCode(String empCode);
		
}
