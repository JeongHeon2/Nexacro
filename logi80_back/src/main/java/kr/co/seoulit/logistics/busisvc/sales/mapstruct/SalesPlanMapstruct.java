package kr.co.seoulit.logistics.busisvc.sales.mapstruct;

import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanTO;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesPlanMapstruct extends EntityResMapper<SalesPlanEntity, SalesPlanTO> {
    @Override
    SalesPlanTO toDto(SalesPlanEntity entity);
}
