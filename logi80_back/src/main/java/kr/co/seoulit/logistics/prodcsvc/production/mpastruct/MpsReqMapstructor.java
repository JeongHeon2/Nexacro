package kr.co.seoulit.logistics.prodcsvc.production.mpastruct;

import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsReqTO;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MpsReqMapstructor extends EntityReqMapper<MpsEntity,MpsReqTO> {

    @Override
    MpsEntity toEntity(MpsReqTO dto);

    @Override
    List<MpsEntity> toEntity(List<MpsReqTO> dtos);

    @Override
    ArrayList<MpsEntity> toEntity(ArrayList<MpsReqTO> dtos);
}
