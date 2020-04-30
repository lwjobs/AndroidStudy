package jssvc.edu.cn.recyclerview2;

public class Person {

    private String name;
    private String phone;
    private int headId;

    public Person(String name, String phone, int headId) {
        this.name = name;
        this.phone = phone;
        this.headId = headId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }
}
