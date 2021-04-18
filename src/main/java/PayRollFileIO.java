import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PayRollFileIO {
    public static String PAYROLL_FILE = "payrollDetails.txt";


    public void writeDataToAFile(List<PayRoll> payRollList) {
        StringBuffer newBuffer = new StringBuffer();
        payRollList.forEach(employee -> {
            String payRollDataString = employee.toString().concat("\n");
            newBuffer.append(payRollDataString);
        });
        try {
            Files.write(Paths.get(PAYROLL_FILE), newBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void printDataFromFile() {
        try {
            Files.lines(new File(PAYROLL_FILE).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countDataOfPayBill() {
        long entries = 0;
        try {
            entries = Files.lines(new File(PAYROLL_FILE).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public List<PayRoll> readData() {
        List<PayRoll> employeePayrollList = new ArrayList();
        try {
            Files.lines(new File(PAYROLL_FILE).toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
}
