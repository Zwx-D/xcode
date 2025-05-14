package com.xcode.app.service.mapper;

import java.util.List;

public interface BaseMapper<E, V> {
    V toVM(E entity);

    E toEntity(V vm);

    List<V> toVMList(List<E> entityList);

    List<E> toEntityList(List<V> vmList);
}
