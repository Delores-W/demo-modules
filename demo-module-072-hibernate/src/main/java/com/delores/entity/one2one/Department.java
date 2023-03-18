package com.delores.entity.one2one;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author William
 * @date 4/15/21 11:26 PM
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long id;
    private String depName;
    private Manager manager;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                ", manager=" + manager.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(depName, that.depName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, depName);
    }
}
