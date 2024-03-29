package com.delores.medusa.dao.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class RangeSpecification<T, E extends Comparable<E> & Serializable> implements Specification<T> {
    private final List<Range<E>> ranges;

    public RangeSpecification(List<Range<E>> ranges) {
        this.ranges = ranges;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = newArrayList();

        for (Range range : ranges) {
            if (!range.isSet()) {
                continue;
            }

            Predicate rangePredicate = buildRangePredicate(range, root, builder);

            if (rangePredicate != null) {
                if (!range.isIncludeNullSet() || range.getIncludeNull() == FALSE) {
                    predicates.add(rangePredicate);
                } else {
                    predicates.add(builder.or(rangePredicate, builder.isNull(root.get(range.getField()))));
                }
            }

            if (TRUE == range.getIncludeNull()) {
                predicates.add(builder.isNull(root.get(range.getField())));
            } else if (FALSE == range.getIncludeNull()) {
                predicates.add(builder.isNotNull(root.get(range.getField())));
            }
        }
        return predicates.isEmpty() ? builder.conjunction() : builder.and(toArray(predicates, Predicate.class));
    }

    private Predicate buildRangePredicate(Range<E> range, Root<T> root, CriteriaBuilder builder) {
        if (range.isBetween()) {
            return builder.between(root.get(range.getField()), range.getFrom(), range.getTo());
        } else if (range.isFromSet()) {
            return builder.greaterThanOrEqualTo(root.get(range.getField()), range.getFrom());
        } else if (range.isToSet()) {
            return builder.lessThanOrEqualTo(root.get(range.getField()), range.getTo());
        }
        return null;
    }

}