package vn.techmaster.comic.entity;

public enum Status {
    COMPLETE("Hoàn thành"),
    INPROCESS("Đang tiến hành"),
    DROP("Bỏ dở");

    private final String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
