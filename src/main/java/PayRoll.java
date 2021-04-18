public class PayRoll {
    public int employeeId;
    public String employeeName;
    public double employeeSalary;

    public PayRoll(Integer employeeId,String employeeName,Double employeeSalary){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }

    @Override
    public String toString() {
        return
                "employeeId=" + employeeId +
                ", employeeName=" + employeeName + '\'' +
                ", employeeSalary=" + employeeSalary ;
    }
}
