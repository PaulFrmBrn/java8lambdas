package patterns.method.oldway;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world!");


        // oldway way
        List<LoanApplication> loanApplication = new ArrayList<>();
        loanApplication.add(new CompanyLoanApplication());
        loanApplication.add(new PersonalLoanApplication());
        loanApplication.add(new EmployeeLoanApplication());

        for (LoanApplication loan : loanApplication) {
            try {
                System.out.println("loan --->>>");
                loan.checkLoan();
            } catch (LoanDenied e) {
                System.out.println("Ooooops... " + e.getMessage());
            }
        }


    }

}
