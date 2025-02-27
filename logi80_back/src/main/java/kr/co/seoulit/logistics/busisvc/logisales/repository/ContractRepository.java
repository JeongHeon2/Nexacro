package kr.co.seoulit.logistics.busisvc.logisales.repository;

import org.springframework.data.repository.CrudRepository;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<ContractEntity, String> {

}
