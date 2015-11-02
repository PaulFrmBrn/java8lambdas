package template.method.newway;


import java.util.ArrayList;
import java.util.List;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world!");


        // new way (differs from the book)
        List<LoanApplication> loanApplication = new ArrayList<>();

        // class CompanyLoanApplication is redundant
        loanApplication.add(new LoanApplication(
                () -> System.out.println("checking identity for the COMPANY"),
                () -> System.out.println("checking credit history for the COMPANY"),
                () -> System.out.println("checking income history for the COMPANY")
        ));

        // class PersonalLoanApplication is redundant
        Criteria personIdentityCheck = () -> System.out.println("checking identity for the PERSON");
        Criteria personCreditHistoryCheck = () -> System.out.println("checking credit history for the PERSON");
        loanApplication.add(new LoanApplication(
                personIdentityCheck,
                personCreditHistoryCheck,
                () -> System.out.println("checking income history for the PERSON")
        ));

        // class EmployeeLoanApplication is redundant
        loanApplication.add(new LoanApplication(
                personIdentityCheck,
                personCreditHistoryCheck,
                () -> System.out.println("checking income history for the EMPLOYEE has been already done")
        ));

        for (LoanApplication loan : loanApplication) {
            try {
                System.out.println("loan --->>>");
                loan.checkLoan();
            } catch (LoanDeniedException e) {
                System.out.println("Ooooops... " + e.getMessage());
            }
        }


    }

}
