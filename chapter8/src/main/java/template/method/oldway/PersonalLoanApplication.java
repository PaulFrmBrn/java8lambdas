package template.method.oldway;

/**
 * @author paul
 */
public class PersonalLoanApplication extends LoanApplication {
    @Override
    protected void checkIdentity() throws LoanDenied {
        System.out.println("checking identity for the PERSON");
    }

    @Override
    protected void checkCreditHistory() throws LoanDenied {
        System.out.println("checking credit history for the PERSON");
    }

    @Override
    protected void checkIncomeHistory() throws LoanDenied {
        System.out.println("checking income history for the PERSON");
    }

    @Override
    protected void reportFindings() {
        System.out.println("done for PERSON");
    }
}
