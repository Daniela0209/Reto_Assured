package co.com.daniela.util;

public enum LoginKey {
    EMAIL("[email]"),
    PASSWORD("[password]");

    private final String value;

    LoginKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
