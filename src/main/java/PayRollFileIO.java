import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}