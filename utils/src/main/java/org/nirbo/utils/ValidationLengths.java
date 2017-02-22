package org.nirbo.utils;

public enum ValidationLengths {

    VALIDATION_NAME_LENGTH(29),
    VALIDATION_IP_LENGTH(15);

    private final int length;

    ValidationLengths(int length) {
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }

}
