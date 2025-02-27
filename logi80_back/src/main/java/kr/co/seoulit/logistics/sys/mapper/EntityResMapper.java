package kr.co.seoulit.logistics.sys.mapper;

import java.util.ArrayList;
import java.util.List;

public interface EntityResMapper<E,D> {

    D toDto(E entity);

    List<D> toDto(List<E> entities);
    ArrayList<D> toDto(ArrayList<E> entities);

}
