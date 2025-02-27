package kr.co.seoulit.logistics.prodcsvc.production.mpastruct;

import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsResTO;
import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MpsResMapstructor extends EntityResMapper<MpsEntity, MpsResTO>{
    @Override
    MpsResTO toDto(MpsEntity entity);

    @Override
    List<MpsResTO> toDto(List<MpsEntity> entities);

    @Override
    ArrayList<MpsResTO> toDto(ArrayList<MpsEntity> entities);


}
