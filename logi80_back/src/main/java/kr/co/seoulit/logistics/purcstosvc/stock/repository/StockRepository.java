package kr.co.seoulit.logistics.purcstosvc.stock.repository;


import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;

public interface StockRepository extends CrudRepository<StockEntity, String> {

	List<StockEntity> findAll(Sort sort);
	ArrayList<StockEntity> findByWarehouseCode(String houseCode);

}
