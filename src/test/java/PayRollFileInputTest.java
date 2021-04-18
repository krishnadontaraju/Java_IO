import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PayRollFileInputTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        PayRoll[] arrayOfEmployees = {
                new PayRoll(1,"Emile",1200000.265),
                new PayRoll(2,"Smith",6461664.6164),
                new PayRoll(3,"Rowe",9797649797.2615)
        };
        PayRollOperation testPayRollOperation;
        testPayRollOperation = new PayRollOperation(Arrays.asList(arrayOfEmployees));
        testPayRollOperation.writePayRollDetails(PayRollOperation.IOService.FILE_IO);
        testPayRollOperation.printData(PayRollOperation.IOService.FILE_IO);
        long entries = testPayRollOperation.countEntries(PayRollOperation.IOService.FILE_IO);
        Assertions.assertEquals(3,entries);
    }
}
