package kr.co.seoulit.logistics.prodcsvc.production.mpastruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsResTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class MpsResMapstructorImpl implements MpsResMapstructor {

    @Override
    public MpsResTO toDto(MpsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MpsResTO mpsResTO = new MpsResTO();

        mpsResTO.setStatus( entity.getStatus() );
        mpsResTO.setMpsNo( entity.getMpsNo() );
        mpsResTO.setMpsPlanClassification( entity.getMpsPlanClassification() );
        mpsResTO.setContractDetailNo( entity.getContractDetailNo() );
        mpsResTO.setSalesPlanNo( entity.getSalesPlanNo() );
        mpsResTO.setItemCode( entity.getItemCode() );
        mpsResTO.setItemName( entity.getItemName() );
        mpsResTO.setUnitOfMps( entity.getUnitOfMps() );
        mpsResTO.setMpsPlanDate( entity.getMpsPlanDate() );
        mpsResTO.setMpsPlanAmount( entity.getMpsPlanAmount() );
        mpsResTO.setDueDateOfMps( entity.getDueDateOfMps() );
        mpsResTO.setScheduledEndDate( entity.getScheduledEndDate() );
        mpsResTO.setMrpApplyStatus( entity.getMrpApplyStatus() );
        mpsResTO.setDescription( entity.getDescription() );
        mpsResTO.setChecked( entity.getChecked() );

        return mpsResTO;
    }

    @Override
    public List<MpsResTO> toDto(List<MpsEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MpsResTO> list = new ArrayList<MpsResTO>( entities.size() );
        for ( MpsEntity mpsEntity : entities ) {
            list.add( toDto( mpsEntity ) );
        }

        return list;
    }

    @Override
    public ArrayList<MpsResTO> toDto(ArrayList<MpsEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<MpsResTO> arrayList = new ArrayList<MpsResTO>();
        for ( MpsEntity mpsEntity : entities ) {
            arrayList.add( toDto( mpsEntity ) );
        }

        return arrayList;
    }
}
