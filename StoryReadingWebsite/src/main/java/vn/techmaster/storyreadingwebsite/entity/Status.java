package vn.techmaster.storyreadingwebsite.entity;

public enum Status {
    HOANTHANH("Hoàn Thành") ,
    DANGTIENHANH("Đang tiến hành") ;
    public final String label;
    private Status(String label) {
        this.label = label;
    }
}
