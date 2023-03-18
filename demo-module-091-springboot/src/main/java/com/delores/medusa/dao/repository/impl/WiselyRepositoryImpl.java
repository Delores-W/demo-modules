package com.delores.medusa.dao.repository.impl;

import com.delores.medusa.dao.repository.WiselyRepository;
import com.delores.medusa.dao.repository.specifications.ExampleSpecification;
import com.delores.medusa.dao.repository.specifications.RangeSpecification;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * @author William
 * @date 4/17/21 4:31 AM
 * @description
 */
public class WiselyRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements WiselyRepository<T, ID> { //NOSONAR

    public WiselyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

//    @Override
//    public <E extends Comparable<E> & Serializable> Page<T> findByExampleWithRange(Example<T> example, List<Range<E>> ranges, Pageable pageable) {
//        return findAll(specifications(example, ranges), pageable);
//    }
//
//    @Override
//    public <E extends Comparable<E> & Serializable> List<T> findByExampleWithRange(Example<T> example, List<Range<E>> ranges) {
//        return findAll(specifications(example, ranges));
//    }
//
    private <E extends Comparable<E> & Serializable> Specification<T> specifications(Example<T> example, List<Range<E>> ranges) {
        Specification<T> byExample = new ExampleSpecification<>(example);
//TODO:
//        Specification<T> byRanges = new RangeSpecification<>(ranges);
//        return where(byExample).and(byRanges);
        return where(byExample);
    }
}
