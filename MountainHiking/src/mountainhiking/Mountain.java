/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainhiking;

/**
 *
 * @author asus
 */
public class Mountain {
    private String mountainCode;
    private String mountainName;
    private String location;
    private String description;

    public Mountain() {
        this.mountainCode = null;
        this.mountainName = null;
        this.location = null;
        this.description = null;
    }

    public Mountain(String mountainCode, String mountainName, String location, String description) {
        this.mountainCode = mountainCode;
        this.mountainName = mountainName;
        this.location = location;
        this.description = description;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-15s %-20s", "MT" + mountainCode, mountainName, location, description);
    }
}