package APPLICATION;

public class NonProfitOrganisation {

    public enum cause {Education, AnimalRights, ClimateChange, HumanRights, Poverty, Research, Healthcare, Other}      // nog aan te vullen

    // instance variables
    private int nPONumber;
    private String nPOName;
    private String description;
    private String accountNumber;
    private cause causeOfNPO;

    // constructor for NonProfitOrganisationDAO
    public NonProfitOrganisation(int nonPONumber, String NPOName, String description, String accountNumber, cause causeOfNPO) {
        this.nPONumber = nonPONumber;
        this.nPOName = NPOName;
        this.description = description;
        this.accountNumber = accountNumber;
        this.causeOfNPO = causeOfNPO;
    }

    // getters and setters
    public int getNPONumber() {
        return nPONumber;
    }

    public void setNPONumber(int nonPONumber) {
        this.nPONumber = nonPONumber;
    }

    public String getNPOName() {
        return nPOName;
    }

    public void setNPOName(String NPOName) {
        this.nPOName = NPOName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public cause getCauseOfNPO() {
        return causeOfNPO;
    }

    public void setCauseOfNPO(cause causeOfNPO) {
        this.causeOfNPO = causeOfNPO;
    }
}
