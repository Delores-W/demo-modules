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
public class Item {
    private Integer id;
    private String itemName;
    private Set<Category> categories = new HashSet<>();


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName);
    }
}
