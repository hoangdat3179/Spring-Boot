package vn.techmaster.job;

public enum Location {
    HaNoi ("Hà Nội"),
    HaiPhong ("Hải Phòng"),
    NamDinh ("Nam Định") ,
    NgheAn ("Nghệ An");

    private String value;

    Location(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
