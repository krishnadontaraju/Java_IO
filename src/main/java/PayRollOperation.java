import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PayRollOperation {

    private List<PayRoll> payRollList;

    public PayRollOperation(){}

    public PayRollOperation(List<PayRoll> payRollList){}

    public static void main(String[] args) {

        ArrayList<PayRoll> payRollArrayList = new ArrayList<>();
        PayRollOperation newPayRollOperation = new PayRollOperation(payRollArrayList);
        Scanner fetch = new Scanner(System.in);

        newPayRollOperation.readPayRollDetails(fetch);

        newPayRollOperation.writePayRollDetails();
    }

    private void readPayRollDetails(Scanner inputFetcher){
        System.out.println("WHAT IS THE EMPLOYEE ID ?");
        int employeeId = inputFetcher.nextInt();

        System.out.println("WHAT IS THE NAME OF THE EMPLOYEE ?");
        String employeeName = inputFetcher.next();

        System.out.println("WHAT IS THE SALARY OF THE EMPLOYEE ?");
        double employeeSalary = inputFetcher.nextDouble();
    }

    private void writePayRollDetails(){
        System.out.println("THE DETAILS OF THE PAYROLL ARE : \n"+payRollList);
    }
}
