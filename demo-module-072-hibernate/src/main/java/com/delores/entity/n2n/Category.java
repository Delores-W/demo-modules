package com.delores.entity.n2n;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author William
 * @date 4/16/21 1:50 AM
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;
    private String cateName;
    private Set<Item> items = new HashSet<>();

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", cateName='" + cateName + '\'' +
                ", items=" + items.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(cateName, category.cateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cateName);
    }
}
