package kr.co.seoulit.logistics.busisvc.sales.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class SalesPlanMapstructImpl implements SalesPlanMapstruct {

    @Override
    public List<SalesPlanTO> toDto(List<SalesPlanEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SalesPlanTO> list = new ArrayList<SalesPlanTO>( entities.size() );
        for ( SalesPlanEntity salesPlanEntity : entities ) {
            list.add( toDto( salesPlanEntity ) );
        }

        return list;
    }

    @Override
    public ArrayList<SalesPlanTO> toDto(ArrayList<SalesPlanEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<SalesPlanTO> arrayList = new ArrayList<SalesPlanTO>();
        for ( SalesPlanEntity salesPlanEntity : entities ) {
            arrayList.add( toDto( salesPlanEntity ) );
        }

        return arrayList;
    }

    @Override
    public SalesPlanTO toDto(SalesPlanEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SalesPlanTO salesPlanTO = new SalesPlanTO();

        salesPlanTO.setStatus( entity.getStatus() );
        salesPlanTO.setSalesPlanNo( entity.getSalesPlanNo() );
        salesPlanTO.setItemCode( entity.getItemCode() );
        salesPlanTO.setItemName( entity.getItemName() );
        salesPlanTO.setUnitOfSales( entity.getUnitOfSales() );
        salesPlanTO.setSalesPlanDate( entity.getSalesPlanDate() );
        salesPlanTO.setDueDateOfSales( entity.getDueDateOfSales() );
        salesPlanTO.setSalesAmount( entity.getSalesAmount() );
        salesPlanTO.setUnitPriceOfSales( entity.getUnitPriceOfSales() );
        salesPlanTO.setSumPriceOfSales( entity.getSumPriceOfSales() );
        salesPlanTO.setMpsApplyStatus( entity.getMpsApplyStatus() );
        salesPlanTO.setDescription( entity.getDescription() );

        return salesPlanTO;
    }
}
