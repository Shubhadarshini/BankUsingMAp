package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ExRef {

    private static ExRef obj;
    private Map<String, ArrayList<String>> customerMapToAccount;// customerId, accounts linked
    private Map<String, ArrayList<String>> accountMapToCustomer;//accountNum, customers linked

    private ExRef() {
        this.customerMapToAccount = new HashMap<>();
        this.accountMapToCustomer = new HashMap<>();

    }

    public static ExRef getInstance(){
        if(obj==null){
            obj = new ExRef();
        }
        return obj;
    }
   

    public void addCustomerIdToList(String cusID) {
        customerMapToAccount.put(cusID, new ArrayList<>());
    }

    public String getNewcustomerId() {

        String customerId = "";
        int len = 6;
        Random rng = new Random();
        boolean nonUnique;

        do {
            for (int i = 0; i < len; i++) {
                customerId += ((Integer) rng.nextInt(10)).toString();
            }
//            Set<String> names = customer.keySet();
            nonUnique = false;

            if (customerMapToAccount.containsKey(customerId)) {
                nonUnique = true;
            }
        } while (nonUnique);

        return customerId;

    }

    public boolean validCustomerId(String cusID) {
        return customerMapToAccount.containsKey(cusID);
    }
    
    public boolean validateAccountNumber(String accNum){
        return  accountMapToCustomer.containsKey(accNum);
    }

    public String getNewAccountNUm() {

        String accountNum = "";
        int len = 10;
        Random rng = new Random();
        boolean nonUnique;

        do {
            for (int i = 0; i < len; i++) {
                accountNum += ((Integer) rng.nextInt(10)).toString();
            }

            nonUnique = false;

            if (accountMapToCustomer.containsKey(accountNum)) {
                nonUnique = true;
            }
        } while (nonUnique);

        return accountNum;



    }

    public void addAccountToCustomer(String cusId, String accountNum) {
        customerMapToAccount.get(cusId).add(accountNum);
    }

    public void addCustomerToAccount(String accountNum, String cusID) {

        if(accountMapToCustomer.containsKey(accountNum)==false)
        {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(cusID);
            accountMapToCustomer.putIfAbsent(accountNum,arrayList);
        }
        else{accountMapToCustomer.get(accountNum).add(cusID);}
    }

    public ArrayList<String> getAccountsLinkedToACustomer(String cusId){
        return customerMapToAccount.get(cusId);
    }

    public ArrayList<String> getCustomerLinkedToAccount(String accNum){
        return accountMapToCustomer.get(accNum);
    }


}

