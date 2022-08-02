package org.example;

import java.util.HashMap;
import java.util.Map;

public class AccountDetails {
    private Map<String, Account> accountDetail;

    private static AccountDetails obj;

    private AccountDetails() {
        this.accountDetail = new HashMap<>();
    }

    public static AccountDetails getInstance(){
        if(obj==null){
            obj = new AccountDetails();
        }
        return obj;
    }

    public void addAccountTolist(Account account) {
        accountDetail.put(account.getAccountNum(), account);
    }

    public Account getAccountsFromList(String accNum) {
        return accountDetail.get(accNum);
    }


    public void calculateInterest() {
        for (Account a1: accountDetail.values()) {
            a1.addInterest();
        }
    }
}
