package kr.co.seoulit.logistics.busisvc.logisales.repository;

import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


@Repository
public interface EstimateRepository extends JpaRepository<EstimateEntity, String> {
	
	int countByEstimateDate(String estimateDate);	
	Optional<EstimateEntity> findByEstimateNo(String estimateNo);
	void deleteByEstimateNo(String EstimateNo);

	ArrayList<EstimateEntity> findByEstimateDateBetween(String startDate, String endDate);
	ArrayList<EstimateEntity> findByEffectiveDateBetween(String startDate, String endDate);
}
