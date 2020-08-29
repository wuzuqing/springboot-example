package com.neeson.example.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID > extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    <S extends T> Iterable<S> batchInsert(Iterable<S> var1);
    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);
}
