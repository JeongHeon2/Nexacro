package kr.co.seoulit.logistics.sys.mapper;

import java.util.ArrayList;
import java.util.List;

public interface EntityReqMapper<E,D> {

    E toEntity(D dto);

    List<E> toEntity(List<D> dtos);
    ArrayList<E> toEntity(ArrayList<D> dtos);

}
