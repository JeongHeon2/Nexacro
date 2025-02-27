package kr.co.seoulit.logistics.busisvc.sales.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import kr.co.seoulit.logistics.busisvc.sales.to.DeliveryInfoEntity;


@Repository
public interface DeliveryInfoRepository extends CrudRepository<DeliveryInfoEntity, String> {
   
	ArrayList<DeliveryInfoEntity> findAll(Sort sort);
	ArrayList<DeliveryInfoEntity> findAllByCustomerCodeOrderByDeliveryDateDesc(String CustomerCode);
}
