package org.launchcode.repair_shop.models.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    private String agentName;

    private String address;

    private String phone;

    private String ssn;

    private Double rate;

    private int pin;

    private boolean active;

    public Employee(int id, String agentName, String address, String phone, String ssn, Double rate, int pin, boolean active) {
        this.id = id;
        this.agentName = agentName;
        this.address = address;
        this.phone = phone;
        this.ssn = ssn;
        this.rate = rate;
        this.pin = pin;
        this.active = active;
    }

    public Employee() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
