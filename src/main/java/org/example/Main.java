package org.example;


import java.beans.Customizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        printMenu(sc);

    }
    public static void printMenu(Scanner sc){
        int choice = 0;


            System.out.println("What would you like to do?");
            System.out.println("1) Create Customer");
            System.out.println("2) Add Account");
            System.out.println("3) Show details of customer");
            System.out.println("4) Linking");
            System.out.println("5) Show account details");
            System.out.println("6) Deposit");
            System.out.println("7) Withdraw");
            System.out.println("8) Calculate Interest");
            System.out.println("9) Update customer details");
            System.out.println("10) Exit");

        do {
            try{
                System.out.print("Enter Choice : ");
                choice = sc.nextInt();
                sc.nextLine();
                if(choice<1 || choice>10){
                    System.out.println("Enter a valid digit between 1 to 10");
                }

            } catch (Exception e) {
                sc.nextLine();// if this line is in try , the buffer has  enter in choice and takes it as next input
                System.out.println("Strings Not allowed");

            }

        } while (choice < 1 || choice > 10);

        switch (choice) {
            case 1:
                createNewCustomer(sc);
                break;

            case 2:
                addAccount(sc);
                break;

            case 3:
                showCustomerDetails(sc);
                break;


            case 4:
                linkCustomerToExistingAccountNumber(sc);

                break;

            case 5:
                showAccountDetails(sc);
                break;

            case 6:
                deposit(sc);
                break;


            case 7:
                withdraw(sc);
                break;

            case 8:
                AccountDetails.getInstance().calculateInterest();
                break;

            case 9:
                updateCustomer(sc);
                break;
        }

        if (choice != 10) {
            printMenu(sc);
        } else {
            System.out.println("***  Thank you for your visit   ***");
            System.exit(0);}
    }

    private static void updateCustomer(Scanner sc) {
        String str;
        do {
            System.out.println("Enter Customer ID");
            str = sc.nextLine();
            if(!ExRef.getInstance().validCustomerId(str)){
                System.out.println("Enter valid customer Id");
            }
        }while(!ExRef.getInstance().validCustomerId(str));

        int i = 0;
        do{
           try {
               System.out.println("1) Update name\n2) Phone Number\n3) Email");
               i = sc.nextInt();
               sc.nextLine();
           }catch (Exception e){
               sc.nextLine();
               System.out.println("Enter a valid digit");
           }

        }while (i<1 ||i>3);

        if(i == 1){
            System.out.print("Enter a new name : \n");
            String name = sc.nextLine();
            CustomerDetails.getInstance().getCustomerFromList(str).setCustomerName(name);
        } else if (i==2) {
            System.out.print("Enter new phone number");
            String phonenum = sc.nextLine();
            CustomerDetails.getInstance().getCustomerFromList(str).setPhoneNum(phonenum);
        } else if (i==3) {
            System.out.println("Enter new email");
            String email = sc.nextLine();
            CustomerDetails.getInstance().getCustomerFromList(email).setEmail(email);
        }

        System.out.println(CustomerDetails.getInstance().getCustomerFromList(str));

    }


    public static void withdraw(Scanner sc){
        String accNum;
        do {
            System.out.print("Enter Account Number to withdraw from : ");
            accNum = sc.nextLine();
            if(!ExRef.getInstance().validateAccountNumber(accNum)){
                System.out.println("Enter a valid Account Number : ");
            }
        }while (!ExRef.getInstance().validateAccountNumber(accNum));
        Account a1 = AccountDetails.getInstance().getAccountsFromList(accNum);
        if(a1.getClass().getName().compareTo("org.example.Savings")==0)
        {
            double amount = 0;
            do{
                try {
                    System.out.print("Enter Amount : ");
                    amount = sc.nextDouble();
                    if(amount <= 0 || amount>a1.getBalance()){
                        System.out.println("Enter amount greater than zero and less than balance.");
                    }
                }catch (Exception e){
                    System.out.println("Enter integers");
                }
            } while (amount <= 0 || amount>a1.getBalance());

            a1.subtractBalance(amount);
        }
        else System.out.println("This is Term deposit account, cannot withdraw ");



    }

    public static void deposit(Scanner sc){

        String accNum;
        do {
            System.out.print("Enter Account Number to deposit in : ");
            accNum = sc.nextLine();
            if(!ExRef.getInstance().validateAccountNumber(accNum)){
                System.out.println("Enter a valid Account Number : ");
            }
        }while (!ExRef.getInstance().validateAccountNumber(accNum));
        Account a1 = AccountDetails.getInstance().getAccountsFromList(accNum);
        if(a1.getClass().getName().compareTo("org.example.Savings")==0)
        {
            double amount = 0;
            do{
               try {
                   System.out.print("Enter Amount : ");
                   amount = sc.nextDouble();
               }catch (Exception e){
                   sc.nextLine();
                   System.out.println("Enter integers");
               }
            } while (amount <= 0);

            a1.addBalance(amount);
        }
        else System.out.println("This is Term deposit account, cannot deposit \n");


    }
    public static void showCustomerDetails(Scanner sc) {

        String cusID;
        do {
            System.out.println("Enter CustomerId : ");
            cusID = sc.nextLine();
            if (!ExRef.getInstance().validCustomerId(cusID)) {
                System.out.println("Invalid Customer Id, Customer Id must be created before linking");
            }
        }while (!ExRef.getInstance().validCustomerId(cusID));

        System.out.println(CustomerDetails.getInstance().getCustomerFromList(cusID));
        ArrayList<String> accNum = ExRef.getInstance().getAccountsLinkedToACustomer(cusID);
        for (String str: accNum) {
            System.out.println(AccountDetails.getInstance().getAccountsFromList(str));
        }

    }

    public static void showAccountDetails(Scanner sc){

        String accNum;
        do {
            System.out.println("Enter Account number : ");
            accNum = sc.nextLine();
            if (!ExRef.getInstance().validateAccountNumber(accNum)) {
                System.out.println("Invalid Account Number, Account must be created before linking");
            }
        }while (!ExRef.getInstance().validateAccountNumber(accNum));

        System.out.println(AccountDetails.getInstance().getAccountsFromList(accNum));
        ArrayList<String> cusId = ExRef.getInstance().getCustomerLinkedToAccount(accNum);
        for (String str: cusId) {
            System.out.println(CustomerDetails.getInstance().getCustomerFromList(str));

        }


    }

    public static void linkCustomerToExistingAccountNumber(Scanner sc) {
        String cusID;
        do {
            System.out.println("Enter CustomerId : ");
            cusID = sc.nextLine();
            if (!ExRef.getInstance().validCustomerId(cusID)) {
                System.out.println("Invalid Customer Id, Customer Id must be created before linking");
            }
        }while (!ExRef.getInstance().validCustomerId(cusID));

        String accNum;
       do {
           System.out.println("Enter Account number : ");
           accNum = sc.nextLine();
           if (!ExRef.getInstance().validateAccountNumber(accNum)) {
               System.out.println("Invalid Account Number, Account must be created before linking");
           }
       }while (!ExRef.getInstance().validateAccountNumber(accNum));


       ExRef.getInstance().addAccountToCustomer(cusID,accNum);
       ExRef.getInstance().addCustomerToAccount(accNum,cusID);



    }

    private static void addAccount(Scanner sc) {
        String cusId;
        do {
            System.out.println("Enter Customer ID : ");

            cusId = sc.nextLine();
            if (ExRef.getInstance().validCustomerId(cusId)) {
                makeAccount(sc, cusId);
            } else if(!ExRef.getInstance().validCustomerId(cusId)) System.out.println("Not a valid Customer Id, Enter Again.");

        }while (!ExRef.getInstance().validCustomerId(cusId));


    }

    private static void makeAccount(Scanner sc, String cusId){
        String type;
        System.out.println("Select type of account(1/2) :\n1) Savings\n2)Term Deposit");
        type = sc.nextLine();
        double initialBalance= 0;
        if(type.equals( "1")) {
            do {
                try {
                    System.out.println("Enter Initial Balance");
                    initialBalance = sc.nextDouble();
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Strings not allowed");
                    sc.nextLine();
                }
            }while (initialBalance == 0);


            Account s = new Savings(initialBalance, 0.03f, cusId, type);

        }
        else if(type.equals("2")){
            System.out.println("Enter Initial Balance : ");
            initialBalance = sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter Year : ");
            float year = sc.nextFloat();
            float interestRate =0f;
            if(year<=1){interestRate = 0.05f;}
            if(year>1 && year<=5){interestRate = 0.06f;}
            if(year>5){interestRate = 0.065f;}
            Account td = new TermDeposit(initialBalance, interestRate, cusId, year,type);
        }

    }

    private static void createNewCustomer(Scanner sc) {
        System.out.print("Enter name : ");
        String name = sc.nextLine();
        String phoneNum;
        do {
            System.out.print("Enter Phone Number of length 10 : ");
            phoneNum= sc.nextLine();
        }while (phoneNum.length()!=10);
        System.out.print("Enter Email : ");
        String email = sc.nextLine();

        Customer c1 = new Customer(name, phoneNum, email);
    }


}
