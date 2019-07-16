package org.launchcode.repair_shop.models.forms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String agentFirstName;

    @NotNull
    private String agentLastName;

    @NotNull
    private String agentMiddleInitial;

    @NotNull
    private String streetAddress;

    @NotNull
    private String cityStateAddress;

    @NotNull
    private String zipAddress;

    private String phone;

    private String ssn;

    @NotNull
    @DecimalMin(value = "7.25")
    @DecimalMax(value = "40.00")
    private Double rate;

    @NotNull
    private int pin;

    private boolean active;

    public Employee(int id, String agentFirstName, String agentLastName, String agentMiddleInitial, String streetAddress,String cityStateAddress,String zipAddress, String phone, String ssn, Double rate, int pin, boolean active) {
        this.id = id;
        this.agentFirstName = agentFirstName;
        this.agentLastName = agentLastName;
        this.agentMiddleInitial = agentMiddleInitial;
        this.streetAddress = streetAddress;
        this.cityStateAddress = cityStateAddress;
        this.zipAddress = zipAddress;
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

    public String getAgentFirstName() {
        return agentFirstName;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public String getAgentLastName() {
        return agentLastName;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public String getAgentMiddleInitial() {
        return agentMiddleInitial;
    }

    public void setAgentMiddleInitial(String agentMiddleInitial) {
        this.agentMiddleInitial = agentMiddleInitial;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityStateAddress() {
        return cityStateAddress;
    }

    public void setCityStateAddress(String cityStateAddress) {
        this.cityStateAddress = cityStateAddress;
    }

    public String getZipAddress() {
        return zipAddress;
    }

    public void setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
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
