package org.example;

import java.util.*;

public class Customer {

    private String customerName;
    private String customerId;
    private String phoneNum;
    private String email;


    public Customer(String customerName, String phoneNum, String email) {
        this.customerName = customerName;
        this.customerId = ExRef.getInstance().getNewcustomerId();
        this.phoneNum = phoneNum;
        this.email = email;
        ExRef.getInstance().addCustomerIdToList(this.customerId);
        CustomerDetails.getInstance().addCustomerToList(this);
        System.out.printf("created customer %s with ID : %s\n",this.customerName,this.customerId);

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
