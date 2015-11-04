package patterns.method.oldway;

/**
 * @author paul
 */
public class CompanyLoanApplication extends LoanApplication {
    @Override
    protected void checkIdentity() throws LoanDenied {
        System.out.println("checking identity for the COMPANY");
    }

    @Override
    protected void checkCreditHistory() throws LoanDenied {
        System.out.println("checking credit history for the COMPANY");
    }

    @Override
    protected void checkIncomeHistory() throws LoanDenied {
        System.out.println("checking income history for the COMPANY");
    }

    @Override
    protected void reportFindings() {
        System.out.println("done for COMPANY");
    }
}
