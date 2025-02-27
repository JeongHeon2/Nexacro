package kr.co.seoulit.logistics.logiinfosvc.logiinfo.mapstruct;

import kr.co.seoulit.logistics.logiinfosvc.logiinfo.dto.ItemGroupReqDto;
import kr.co.seoulit.logistics.logiinfosvc.logiinfo.dto.ItemGroupResDto;
import kr.co.seoulit.logistics.logiinfosvc.logiinfo.entity.ItemGroupEntity;

import kr.co.seoulit.logistics.sys.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemGroupResMapStruct extends EntityResMapper<ItemGroupEntity, ItemGroupResDto> {

    @Override
    ItemGroupResDto toDto(ItemGroupEntity entity);

    @Override
    List<ItemGroupResDto> toDto(List<ItemGroupEntity> entities);
}
