package patterns.method.oldway;

/**
 * @author paul
 */
public abstract class LoanApplication {

    public void checkLoan() throws LoanDenied {
        checkIdentity();
        checkCreditHistory();
        checkIncomeHistory();
        reportFindings();
    }

    protected abstract void checkIdentity() throws LoanDenied;

    protected abstract void checkCreditHistory() throws LoanDenied;

    protected abstract void checkIncomeHistory() throws LoanDenied;

    protected abstract void reportFindings();

}
