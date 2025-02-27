package kr.co.seoulit.logistics.purcstosvc.stock.mapStruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.purcstosvc.stock.dto.StockRepDto;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-19T13:34:02+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class StockReqMapStructImpl implements StockReqMapStruct {

    @Override
    public StockEntity toEntity(StockRepDto dto) {
        if ( dto == null ) {
            return null;
        }

        StockEntity stockEntity = new StockEntity();

        stockEntity.setStatus( dto.getStatus() );
        stockEntity.setWarehouseCode( dto.getWarehouseCode() );
        stockEntity.setItemCode( dto.getItemCode() );
        stockEntity.setItemName( dto.getItemName() );
        stockEntity.setUnitOfStock( dto.getUnitOfStock() );
        stockEntity.setSafetyAllowanceAmount( dto.getSafetyAllowanceAmount() );
        stockEntity.setStockAmount( dto.getStockAmount() );
        stockEntity.setOrderAmount( dto.getOrderAmount() );
        stockEntity.setInputAmount( dto.getInputAmount() );
        stockEntity.setTotalStockAmount( dto.getTotalStockAmount() );

        return stockEntity;
    }

    @Override
    public List<StockEntity> toEntity(List<StockRepDto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<StockEntity> list = new ArrayList<StockEntity>( entities.size() );
        for ( StockRepDto stockRepDto : entities ) {
            list.add( toEntity( stockRepDto ) );
        }

        return list;
    }
}
