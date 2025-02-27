package kr.co.seoulit.logistics.busisvc.sales.repository;

import java.util.ArrayList;


import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReleaseInfoRepository extends CrudRepository<ReleaseInfoEntity, String> {
	 ArrayList<ReleaseInfoEntity> findAll(Sort sort);
}
