package kr.co.seoulit.logistics.logiinfosvc.logiinfo.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co.seoulit.logistics.logiinfosvc.logiinfo.dto.ItemGroupResDto;
import kr.co.seoulit.logistics.logiinfosvc.logiinfo.entity.ItemGroupEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T16:06:45+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ItemGroupResMapStructImpl implements ItemGroupResMapStruct {

    @Override
    public ArrayList<ItemGroupResDto> toDto(ArrayList<ItemGroupEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        ArrayList<ItemGroupResDto> arrayList = new ArrayList<ItemGroupResDto>();
        for ( ItemGroupEntity itemGroupEntity : entities ) {
            arrayList.add( toDto( itemGroupEntity ) );
        }

        return arrayList;
    }

    @Override
    public ItemGroupResDto toDto(ItemGroupEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ItemGroupResDto itemGroupResDto = new ItemGroupResDto();

        itemGroupResDto.setItemGroupCode( entity.getItemGroupCode() );
        itemGroupResDto.setDescription( entity.getDescription() );
        itemGroupResDto.setItemGroupName( entity.getItemGroupName() );

        return itemGroupResDto;
    }

    @Override
    public List<ItemGroupResDto> toDto(List<ItemGroupEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ItemGroupResDto> list = new ArrayList<ItemGroupResDto>( entities.size() );
        for ( ItemGroupEntity itemGroupEntity : entities ) {
            list.add( toDto( itemGroupEntity ) );
        }

        return list;
    }
}
