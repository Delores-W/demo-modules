package com.delores.medusa.model.enums;

import lombok.Data;

/**
 * @author William
 * @date 4/30/21 12:02 AM
 * @description
 */
public enum UserState {
    ACTIVE(0, "user state is active"),
    INACTIVE(1, "user state is inactive");

    private int index;
    private String comment;

    UserState(int index, String comment) {
        this.index = index;
        this.comment = comment;
    }

    public int getIndex() {
        return index;
    }

    public String getComment() {
        return comment;
    }
}
