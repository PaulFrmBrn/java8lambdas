package template.method.newway;

/**
 * @author paul
 */
public class LoanApplication {

    private final Criteria checkIdentity;
    private final Criteria checkCreditHistory;
    private final Criteria checkIncomeHistory;
    private final Criteria reportFindings;

    public LoanApplication(Criteria checkIdentity,
                           Criteria checkCreditHistory,
                           Criteria checkIncomeHistory) {
        this.checkIdentity = checkIdentity;
        this.checkCreditHistory = checkCreditHistory;
        this.checkIncomeHistory = checkIncomeHistory;
        this.reportFindings = () -> System.out.println("Report the details!");
    }

    public void checkLoan() throws LoanDeniedException {
        checkIdentity.check();
        checkCreditHistory.check();
        checkIncomeHistory.check();
        reportFindings.check();
    }

}
