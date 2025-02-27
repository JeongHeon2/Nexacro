package kr.co.seoulit.logistics.prodcsvc.quality.mapstruct;

import kr.co.seoulit.logistics.prodcsvc.quality.Entity.WorkOrderInfoEntity;
import kr.co.seoulit.logistics.prodcsvc.quality.to.WorkOrderInfoResTO;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkOrderInfoResMapstruct extends EntityResMapper<WorkOrderInfoEntity, WorkOrderInfoResTO> {

    @Override
    WorkOrderInfoResTO toDto(WorkOrderInfoEntity entity);

    @Override
    List<WorkOrderInfoResTO> toDto(List<WorkOrderInfoEntity> entities);

    @Override
    ArrayList<WorkOrderInfoResTO> toDto(ArrayList<WorkOrderInfoEntity> entities);
}
