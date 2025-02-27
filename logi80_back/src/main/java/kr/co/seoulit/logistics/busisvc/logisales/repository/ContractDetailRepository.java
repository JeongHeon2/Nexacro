package kr.co.seoulit.logistics.busisvc.logisales.repository;

import java.util.Optional;

import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDetailRepository extends CrudRepository<ContractDetailEntity, String> {

	Optional<ContractDetailEntity> findByContractDetailNo(String contractDetailNo);
	
}
