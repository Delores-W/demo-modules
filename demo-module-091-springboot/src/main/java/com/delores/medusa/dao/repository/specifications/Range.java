package com.delores.medusa.dao.repository.specifications;

import java.io.Serializable;

public class Range<E extends Comparable<E> & Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String field;
    private E from; //NOSONAR
    private E to; //NOSONAR
    private Boolean includeNull;

    public Range(String field) {
        this.field = field;
    }

    public Range(String field, E from, E to) {
        this.field = field;
        this.from = from;
        this.to = to;
    }

    public Range(String field, E from, E to, Boolean includeNull) {
        this.field = field;
        this.from = from;
        this.to = to;
        this.includeNull = includeNull;
    }

    public String getField() {
        return field;
    }

    public E getFrom() {
        return from;
    }

    public void setFrom(E from) {
        this.from = from;
    }

    public boolean isFromSet() {
        return getFrom() != null;
    }

    public E getTo() {
        return to;
    }

    public void setTo(E to) {
        this.to = to;
    }

    public boolean isToSet() {
        return getTo() != null;
    }

    public void setIncludeNull(boolean includeNull) {
        this.includeNull = includeNull;
    }

    public Boolean getIncludeNull() {
        return includeNull;
    }

    public boolean isIncludeNullSet() {
        return includeNull != null;
    }

    public boolean isBetween() {
        return isFromSet() && isToSet();
    }

    public boolean isSet() {
        return isFromSet() || isToSet() || isIncludeNullSet();
    }

    public boolean isValid() {
        if (isBetween()) {
            return getFrom().compareTo(getTo()) <= 0;
        }
        return true;
    }
}