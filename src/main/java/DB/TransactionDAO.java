package DB;

import APPLICATION.Provider;
import APPLICATION.Transaction;
import APPLICATION.User;

import java.sql.*;
import java.util.ArrayList;

public class TransactionDAO {

    public static void createTables() throws DBException {
        try {
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE transactions ("
                    + "TransactionNumber int NOT NULL, "
                    + "eventNumber INT NOT NULL, "
                    + "userNumber INT NOT NULL, "
                    + "providerNumber int NOT NULL, "
                    + "status ENUM('Requested', 'Accepted', 'NotAccepted', 'Payed') NOT NULL, "
                    + "message varchar(100) NOT NULL, "
                    + "totalAmount DOUBLE NOT NULL, "
                    + "amountToProvider DOUBLE NOT NULL, "
                    + "amountToNPO DOUBLE NOT NULL, "
                    + "amountPlatform DOUBLE NOT NULL, "
                    + "amountDiscount DOUBLE NOT NULL, "
                    + "amountToPay DOUBLE NOT NULL, "
                    + "PRIMARY KEY (TransactionNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Transaction getTransaction(int transactionNum) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT TransactionNumber, eventNumber, userNumber, providerNumber, status, message, totalAmount, amountToProvider, amountToNPO, amountPlatform, amountDiscount, amountToPay "
                    + "FROM transactions "
                    + "WHERE TransactionNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1, transactionNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String statement;
            int transactionNumber, eventNumber, userNumber, providerNumber;
            String message;
            Transaction.status status;
            double totalAmount, amountToProvider, amountToNPO, amountPlatform, amountDiscount, amountToPay;
            if (srs.next()) {
                transactionNumber = srs.getInt("TransactionNumber");
                eventNumber = srs.getInt("eventNumber");
                userNumber = srs.getInt("userNumber");
                providerNumber = srs.getInt("providerNumber");
                status = Transaction.status.valueOf(srs.getString("status"));
                message = srs.getString("message");
                totalAmount = srs.getDouble("totalAmount");
                amountToProvider = srs.getDouble("amountToProvider");
                amountToNPO = srs.getDouble("amountToNPO");
                amountPlatform = srs.getDouble("amountPlatform");
                amountDiscount = srs.getDouble("amountDiscount");
                amountToPay = srs.getDouble("amountToPay");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
                Transaction transaction  = new Transaction(transactionNumber, eventNumber, userNumber, providerNumber, status, message, totalAmount, amountToProvider, amountToNPO, amountPlatform, amountDiscount, amountToPay);
                return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void saveTransaction(Transaction transaction) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT TransactionNumber "
                    + "FROM transactions "
                    + "WHERE TransactionNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, transaction.getTransactionNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE transactions " +
                        "SET eventNumber = ? ," +
                        " userNumber = ? ," +
                        " providerNumber = ? ," +
                        " status = ? ," +
                        " message = ? ," +
                        " totalAmount = ? ," +
                        " amountToProvider = ? ," +
                        " amountToNPO = ? ," +
                        " amountPlatform = ? ," +
                        " amountDiscount = ? ," +
                        " amountToPay = ? " +
                        "WHERE TransactionNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, transaction.getEventNumber());
                stmt2.setInt(2, transaction.getUserNumber());
                stmt2.setInt(3, transaction.getProviderNumber());
                stmt2.setString(4, String.valueOf(transaction.getStatus()));
                stmt2.setString(5,transaction.getMessage());
                stmt2.setDouble(6, transaction.getTotalAmount());
                stmt2.setDouble(7, transaction.getAmountToPay());
                stmt2.setDouble(8, transaction.getAmountToNPO());
                stmt2.setDouble(9, transaction.getAmountPlatform());
                stmt2.setDouble(10, transaction.getAmountDiscount());
                stmt2.setDouble(11, transaction.getAmountToPay());
                stmt2.setInt(12,transaction.getTransactionNumber());
                stmt2.executeUpdate();
            } else {

                // INSERT
                String sqlInsert = "INSERT into transactions "
                        + "(TransactionNumber, eventNumber, userNumber, providerNumber, status, message, totalAmount, amountToProvider, amountToNPO, amountPlatform, amountDiscount, amountToPay) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1,transaction.getTransactionNumber());
                insertStm.setInt(2, transaction.getEventNumber());
                insertStm.setInt(3, transaction.getUserNumber());
                insertStm.setInt(4, transaction.getProviderNumber());
                insertStm.setString(5, String.valueOf(transaction.getStatus()));
                insertStm.setString(6,transaction.getMessage());
                insertStm.setDouble(7, transaction.getTotalAmount());
                insertStm.setDouble(8, transaction.getAmountToPay());
                insertStm.setDouble(9, transaction.getAmountToNPO());
                insertStm.setDouble(10, transaction.getAmountPlatform());
                insertStm.setDouble(11, transaction.getAmountDiscount());
                insertStm.setDouble(12, transaction.getAmountToPay());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static ArrayList<Transaction> getTransactions() {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT TransactionNumber "
                    + "FROM transactions";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            while (srs.next())
                transactions.add(getTransaction(srs.getInt("TransactionNumber")));
            return transactions;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteTransaction(Transaction transaction) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM transactions "
                    + "WHERE TransactionNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, transaction.getTransactionNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
