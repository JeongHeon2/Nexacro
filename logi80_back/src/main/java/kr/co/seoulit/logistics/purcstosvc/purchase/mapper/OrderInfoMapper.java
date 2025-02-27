package kr.co.seoulit.logistics.purcstosvc.purchase.mapper;

import kr.co.seoulit.logistics.purcstosvc.purchase.entity.OrderInfoEntity;
import kr.co.seoulit.logistics.purcstosvc.purchase.to.OrderInfoTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderInfoMapper {
    OrderInfoMapper INSTANCE = Mappers.getMapper(OrderInfoMapper.class);

    @Mappings({
            @Mapping(source = "orderDate", target = "orderDate"),
            @Mapping(source = "orderInfoStatus", target = "orderInfoStatus"),
            @Mapping(source = "orderSort", target = "orderSort"),
            @Mapping(source = "itemCode", target = "itemCode"),
            @Mapping(source = "itemName", target = "itemName"),
            @Mapping(source = "orderAmount", target = "orderAmount"),
            @Mapping(source = "inspectionStatus", target ="inspectionStatus"),
            @Mapping(source = "checked", target = "checked")
    })
    OrderInfoTO entityToTO(OrderInfoEntity entity);
}

