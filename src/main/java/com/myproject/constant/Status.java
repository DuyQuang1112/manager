package com.myproject.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    CREATED("Created"),
    UPDATED("Updated"),
    PENDING("Pending"),
    REJECTED("Rejected"),
    ACCEPTED("Accepted"),
    ADDITIONAL("Additional");

    private final String value;
}
