package APPLICATION;

import DB.TransactionDAO;
import GUI.Main;

public class Transaction {

    public enum status {Requested, Accepted, NotAccepted, Payed};

    // instance variables
    private int transactionNumber;  // unique
    private int eventNumber;
    private int userNumber;
    private int providerNumber;
    private status status;
    private String message;
    private double totalAmount;
    private double amountToProvider;  // 91%
    private double amountToNPO; //3%
    private double amountPlatform;    // 3% vanaf 3x: 1%
    private double amountDiscount;     // vanaf 3x: 2%
    private double amountToPay;

    // constructor for TransactionDAO
    public Transaction(int transactionNumber, int eventNumber, int userNumber, int providerNumber, Transaction.status status, String message, double totalAmount, double amountToProvider, double amountToNPO, double amountPlatform, double amountDiscount, double amountToPay) {
        this.transactionNumber = transactionNumber;
        this.eventNumber = eventNumber;
        this.userNumber = userNumber;
        this.providerNumber = providerNumber;
        this.status = status;
        this.message = message;
        this.totalAmount = totalAmount;
        this.amountToProvider = amountToProvider;
        this.amountToNPO = amountToNPO;
        this.amountPlatform = amountPlatform;
        this.amountDiscount = amountDiscount;
        this.amountToPay = amountToPay;
    }

    // consturctor for GUI
    public Transaction(int eventNumber, int userNumber, int providerNumber, Transaction.status status, String message, double totalAmount) {
        this.transactionNumber = TransactionDAO.getTransactions().size() + 1;
        this.eventNumber = eventNumber;
        this.userNumber = userNumber;
        this.providerNumber = providerNumber;
        this.status = status;
        this.message = message;
        if (isDiscounted(providerNumber)) {
            this.amountToProvider = 0.94 * totalAmount;
            this.amountToNPO = 0.03 * totalAmount;
            this.amountPlatform = 0.01 * totalAmount;
            this.amountDiscount = 0.02 * totalAmount;
            this.totalAmount = totalAmount - this.amountDiscount;
        }
        else {
            this.totalAmount = totalAmount;
            this.amountToProvider = 0.94 * totalAmount;
            this.amountToNPO = 0.03 * totalAmount;
            this.amountPlatform = 0.03 * totalAmount;
            this.amountDiscount = 0;
        }
        this.amountToPay = this.totalAmount;
    }

    public boolean isDiscounted(int providerNumber){
        int count = 0;
        for (Transaction transaction : TransactionDAO.getTransactions())
            if (transaction.getUserNumber()== Main.userMain.getUserNumber() && transaction.getProviderNumber() == providerNumber)
                count++;
        return count > 2.5;

    }
    // getters and setters
    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(int providerNumber) {
        this.providerNumber = providerNumber;
    }

    public Transaction.status getStatus() {
        return status;
    }

    public void setStatus(Transaction.status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountToProvider() {
        return amountToProvider;
    }

    public void setAmountToProvider(double amountToProvider) {
        this.amountToProvider = amountToProvider;
    }

    public double getAmountToNPO() {
        return amountToNPO;
    }

    public void setAmountToNPO(double amountToNPO) {
        this.amountToNPO = amountToNPO;
    }

    public double getAmountPlatform() {
        return amountPlatform;
    }

    public void setAmountPlatform(double amountPlatform) {
        this.amountPlatform = amountPlatform;
    }

    public double getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(double amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

}