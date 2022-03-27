package vn.techmaster.jobListing.location;

public enum location {
    HaNoi ("Hà Nội"),
    HaiPhong ("Hải Phòng"),
    NamDinh ("Nam Định") ,
    NgheAn ("Nghệ An");

    private String value;

    location(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
