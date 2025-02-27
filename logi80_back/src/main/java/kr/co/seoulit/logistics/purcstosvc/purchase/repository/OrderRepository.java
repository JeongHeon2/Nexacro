package kr.co.seoulit.logistics.purcstosvc.purchase.repository;

import java.util.List;

import kr.co.seoulit.logistics.purcstosvc.purchase.entity.OrderInfoEntity;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<OrderInfoEntity, String> {

	List<OrderInfoEntity> findByOrderInfoStatusIn(List<String> orderInfoStatusList);
	List<OrderInfoEntity> findByOrderDateBetween(String startDate, String endDate);

}
