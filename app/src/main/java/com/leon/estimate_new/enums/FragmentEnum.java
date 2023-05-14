package com.leon.estimate_new.enums;

public enum FragmentEnum {
    ShowDocument("SHOW_DOCUMENT_FRAGMENT"),
    Bill("BILL_FRAGMENT")  ;

    private final String value;

    FragmentEnum(final String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
