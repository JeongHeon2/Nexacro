package kr.co.seoulit.logistics.prodcsvc.production.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MpsRepository extends JpaRepository<MpsEntity, String> {
	
	List<MpsEntity> findByMpsPlanDate(String mpsPlanDate);
	
	ArrayList<MpsEntity> findByMpsPlanDateBetween(String startDate, String endDate);
	
	ArrayList<MpsEntity> findByMpsPlanDateBetweenAndMrpApplyStatusIsNull(String startDate, String endDate);
	
	Optional<MpsEntity> findByMpsNo(String mpsNo);
}
