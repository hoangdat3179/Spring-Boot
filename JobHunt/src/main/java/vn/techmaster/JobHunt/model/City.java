package vn.techmaster.JobHunt.model;

public enum City {
    HaNoi("Hà nội"),
    HoChiMinh("Hồ Chí Minh"),
    HaiPhong("Hải phòng"),
    DaNang("Đà Nẵng");

    public final String value;

    private City(String value) {
        this.value = value;
    }
}