package com.task.databaseinspector.mapping;


public interface DefaultMapper<E,D> {
    D toDto(E entity);

    E toEntity(D dto);
}
