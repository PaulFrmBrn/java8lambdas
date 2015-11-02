package template.method.oldway;

/**
 * @author paul
 */
public class EmployeeLoanApplication extends PersonalLoanApplication {
    @Override
    protected void checkIncomeHistory() throws LoanDenied {
        System.out.println("checking income history for the EMPLOYEE has been already done");
    }
}
