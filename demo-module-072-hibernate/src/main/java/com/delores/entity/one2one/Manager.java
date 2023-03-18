package com.delores.entity.one2one;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author William
 * @date 4/15/21 11:25 PM
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private Long id;
    private String name;
    private Department department;

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department.getDepName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) &&
                Objects.equals(name, manager.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
