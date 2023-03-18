package com.delores.medusa.dao.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author William
 * @date 4/17/21 3:08 AM
 * @description 自定义Repository 增强 JpaRepository 如果不加 @NoRepositoryBean 会报错
 */
@NoRepositoryBean
public interface WiselyRepository<T, ID> extends JpaRepository<T, ID> {
//TODO:
//    <E extends Comparable<E> & Serializable> Page<T> findByExampleWithRange(Example<T> example, List<Range<E>> ranges, Pageable pageable);
//    <E extends Comparable<E> & Serializable> List<T> findByExampleWithRange(Example<T> example, List<Range<E>> ranges);

}
