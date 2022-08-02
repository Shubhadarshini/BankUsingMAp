package org.example;

import java.util.HashMap;
import java.util.Map;

public class CustomerDetails{

    private Map<String , Customer> customerDetail;
    private static CustomerDetails obj;

    private CustomerDetails() {
        this.customerDetail = new HashMap<>();
    }

    public static CustomerDetails getInstance(){

        if (obj == null){
            obj = new CustomerDetails();
        }
        return obj;
    }

    public void addCustomerToList(Customer customer) {
        customerDetail.put(customer.getCustomerId(), customer);
    }

    public Customer getCustomerFromList(String cusID){
        return customerDetail.get(cusID);

    }


}
