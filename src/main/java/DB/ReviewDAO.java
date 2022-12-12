package DB;

import APPLICATION.Review;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {
    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE reviews ("
                    + "ReviewNumber int NOT NULL, "
                    + "EventNumber int NOT NULL, "
                    + "providerReview BOOLEAN NOT NULL, "
                    + "userNumber int NOT NULL, "
                    + "providerNumber int NOT NULL, "
                    + "subject varchar(100) NOT NULL, "
                    + "scoreOn10 int(10) NOT NULL, "
                    + "description varchar(100) NOT NULL, "
                    + "dateOfReview DATETIME NOT NULL, "
                    + "PRIMARY KEY (ReviewNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Review getReview(int reviewNum)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT ReviewNumber, EventNumber, providerReview, userNumber, providerNumber, subject, scoreOn10, description, dateOfReview "
                    + "FROM reviews "
                    + "WHERE ReviewNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,reviewNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String subject, description;
            int reviewNumber, eventNumber, userNumber, providerNumber, scoreOn10;
            Date dateOfReview;
            boolean providerReview;

            if (srs.next()) {
                reviewNumber = srs.getInt("ReviewNumber");
                eventNumber = srs.getInt("EventNumber");
                providerReview = srs.getBoolean("providerReview");
                userNumber = srs.getInt("userNumber");
                providerNumber = srs.getInt("providerNumber");
                subject = srs.getString("subject");
                scoreOn10 = srs.getInt("scoreOn10");
                description = srs.getString("description");
                dateOfReview = srs.getDate("dateOfReview");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Review review = new Review(reviewNumber, eventNumber, providerReview, userNumber, providerNumber, subject, scoreOn10, description, dateOfReview);
            return review;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }
    public void save(Review review)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT ReviewNumber "
                    + "FROM reviews "
                    + "WHERE ReviewNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,review.getReviewNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE reviews " +
                        "SET " +
                        "EventNumber = ?, " +
                        "providerReview = ?, " +
                        "userNumber = ?, " +
                        "providerNumber = ?," +
                        "subject = ? , " +
                        "scoreOn10 = ?, " +
                        "description = ?, " +
                        "dateOfReview = ?, " +
                        "WHERE ReviewNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, review.getEventNumber());
                stmt2.setBoolean(2, review.isProviderReview());
                stmt2.setInt(3, review.getUserNumber());
                stmt2.setInt(4, review.getProviderNumber());
                stmt2.setString(5, review.getSubject());
                stmt2.setInt(6, review.getScoreOn10());
                stmt2.setString(7, review.getDescription());
                stmt2.setDate(8, (Date) review.getDateOfReviews());

                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into reviews "
                        + "(ReviewNumber, EventNumber, providerReview, userNumber, providerNumber, subject, scoreOn10, description, dateOfReview) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, review.getReviewNumber());
                insertStm.setInt(2,review.getEventNumber());
                insertStm.setBoolean(3,review.isProviderReview());
                insertStm.setInt(4,review.getUserNumber());
                insertStm.setInt(5,review.getProviderNumber());
                insertStm.setString(6,review.getSubject());
                insertStm.setInt(7,review.getScoreOn10());
                insertStm.setString(8, review.getDescription());
                insertStm.setDate(9,(Date) review.getDateOfReviews());

                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    public ArrayList<Review> getReviews()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT ReviewNumber "
                    + "FROM reviews";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Review> reviews = new ArrayList<Review>();
            while (srs.next())
                reviews.add(getReview(srs.getInt("ReviewNumber")));
            return reviews;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public void deleteReview(Review review)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM reviews "
                    + "WHERE reviewNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,review.getReviewNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
