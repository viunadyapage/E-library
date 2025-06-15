package model;

public class Library {
    private String id;
    private String name;
    private String location;
    private String phoneNumber;
    private String operationalHour;


    // Konstruktor lengkap
    public Library(String id, String name, String location, String phoneNumber, String operationalHour) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.operationalHour = operationalHour;
    }

    // Getter dan Setter untuk setiap atribut
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOperationalHour() {
        return operationalHour;
    }

    public void setOperationalHour(String operationalHour) {
        this.operationalHour = operationalHour;
    }
}
