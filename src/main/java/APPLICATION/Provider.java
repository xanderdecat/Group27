package APPLICATION;

import DB.ProviderDAO;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

public class Provider extends User {

    public enum genres {Techno, Rock, Pop, Dance, Blues, Jazz, Soul, Party, Hiphop, Acoustic, Disco, Funk, Classic, Background, Nineties, Eighties, Seventies, Sixties, Latin, Lounge}

    // instantievariabelen
    private static int helpProviderNumber = 0;
    private int providerNumber;            // unique key
    private String VATNumber;
    private String accountNumber;
    private String streetName;
    private int houseNumber;
    private int ZIP;
    private String city;
    private String country;
    private String artistName;
    private genres genre;
    private java.sql.Date activityDate;
    private double priceHour;
    private double minHours;
    private double maxHours;
    private String conditions;
    private String description;
    private URL teaserSet;
    private URL linkToPage;

    // constructor for GUI
    public Provider(int userNumber, String firstName, String lastName, java.sql.Date dateOfBirth, int age, String email, String phoneNumber, String password, String VATNumber, String accountNumber, String streetName, int houseNumber, int ZIP, String city, String country, String artistName, genres genre, java.sql.Date activityDate, double priceHour, double minHours, double maxHours, String conditions, String description, URL teaserSet, URL linkToPage) {
        super(userNumber, firstName, lastName, dateOfBirth, age, email, phoneNumber, password);
        this.providerNumber = ProviderDAO.getProviders().size() + 1;
        this.VATNumber = VATNumber;
        this.accountNumber = accountNumber;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.ZIP = ZIP;
        this.city = city;
        this.country = country;
        this.artistName = artistName;
        this.genre = genre;
        this.activityDate = activityDate;
        this.priceHour = priceHour;
        this.minHours = minHours;
        this.maxHours = maxHours;
        this.conditions = conditions;
        this.description = description;
        this.teaserSet = teaserSet;
        this.linkToPage = linkToPage;

    }

    // constructor for DAO
    public Provider(int userNumber, String firstName, String lastName, java.sql.Date dateOfBirth, int age, String email, String phoneNumber, String password, int providerNumber, String VATNumber, String accountNumber, String streetName, int houseNumber, int ZIP, String city, String country, String artistName, genres genre, java.sql.Date activityDate, double priceHour, double minHours, double maxHours, String conditions, String description, URL teaserSet, URL linkToPage) {
        super(userNumber, firstName, lastName, dateOfBirth, age, email, phoneNumber, password);
        this.providerNumber = providerNumber;
        this.VATNumber = VATNumber;
        this.accountNumber = accountNumber;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.ZIP = ZIP;
        this.city = city;
        this.country = country;
        this.artistName = artistName;
        this.genre = genre;
        this.activityDate = activityDate;
        this.priceHour = priceHour;
        this.minHours = minHours;
        this.maxHours = maxHours;
        this.conditions = conditions;
        this.description = description;
        this.teaserSet = teaserSet;
        this.linkToPage = linkToPage;
    }


    public static void setHelpProviderNumber(int helpProviderNumber) {
        Provider.helpProviderNumber = helpProviderNumber;
    }

    public int getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(int providerNumber) {
        this.providerNumber = providerNumber;
    }

    public String getVATNumber() {
        return VATNumber;
    }

    public void setVATNumber(String VATNumber) {
        this.VATNumber = VATNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getZIP() {
        return ZIP;
    }

    public void setZIP(int ZIP) {
        this.ZIP = ZIP;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public genres getGenre() {
        return genre;
    }

    public void setGenre(genres genre) {
        this.genre = genre;
    }

    public java.sql.Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(java.sql.Date activityDate) {
        this.activityDate = activityDate;
    }

    public double getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(double priceHour) {
        this.priceHour = priceHour;
    }

    public double getMinHours() {
        return minHours;
    }

    public void setMinHours(double minHours) {
        this.minHours = minHours;
    }

    public double getMaxHours() {
        return maxHours;
    }

    public void setMaxHours(double maxHours) {
        this.maxHours = maxHours;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getTeaserSet() {
        return teaserSet;
    }

    public void setTeaserSet(URL teaserSet) {
        this.teaserSet = teaserSet;
    }

    public URL getLinkToPage() {
        return linkToPage;
    }

    public void setLinkToPage(URL linkToPage) {
        this.linkToPage = linkToPage;
    }

    public static boolean checkVATnumber(String VATNumberToCheck) {
        if (VATNumberToCheck == null || VATNumberToCheck.length() == 0)
            return false;
        for (Provider provider : ProviderDAO.getProviders())
            if (provider.VATNumber.equals(VATNumberToCheck))
                return true;
        return false;
    }
    public static boolean checkMinMaxHours(TextField minHoursInput, TextField maxHoursInput){
        double maxHours = Double.parseDouble(maxHoursInput.getText());
        double minHours = Double.parseDouble(minHoursInput.getText());
        return maxHours >= minHours;
    }
}
