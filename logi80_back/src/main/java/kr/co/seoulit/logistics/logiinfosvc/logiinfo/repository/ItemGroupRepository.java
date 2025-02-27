package kr.co.seoulit.logistics.logiinfosvc.logiinfo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.seoulit.logistics.logiinfosvc.logiinfo.entity.ItemGroupEntity;


public interface ItemGroupRepository extends JpaRepository<ItemGroupEntity, String> {
	 List<ItemGroupEntity> findAll();
}
