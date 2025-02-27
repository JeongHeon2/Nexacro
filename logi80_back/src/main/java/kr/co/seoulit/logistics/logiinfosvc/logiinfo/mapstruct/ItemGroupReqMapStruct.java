package kr.co.seoulit.logistics.logiinfosvc.logiinfo.mapstruct;

import kr.co.seoulit.logistics.logiinfosvc.logiinfo.dto.ItemGroupReqDto;
import kr.co.seoulit.logistics.logiinfosvc.logiinfo.entity.ItemGroupEntity;
import kr.co.seoulit.logistics.sys.mapper.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemGroupReqMapStruct extends EntityReqMapper<ItemGroupEntity, ItemGroupReqDto> {

    @Override
    ItemGroupEntity toEntity(ItemGroupReqDto dto);

    @Override
    List<ItemGroupEntity> toEntity(List<ItemGroupReqDto> dtos);
}
