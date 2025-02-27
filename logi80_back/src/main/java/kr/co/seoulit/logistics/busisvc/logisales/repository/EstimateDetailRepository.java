package kr.co.seoulit.logistics.busisvc.logisales.repository;

import java.util.List;

import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstimateDetailRepository extends CrudRepository<EstimateDetailEntity, String> {
	
	List<EstimateDetailEntity> findByEstimateNo(String estimateNo);
}
