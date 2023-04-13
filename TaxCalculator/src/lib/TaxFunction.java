package lib;

public class TaxFunction {
    
    private static final int TAX_RATE = 5;
    private static final int MAX_CHILDREN = 3;
    private static final int MAX_TAX_DEDUCTIBLE = 54000000;
    private static final int MARRIED_DEDUCTIBLE = 4500000;
    private static final int CHILD_DEDUCTIBLE = 1500000;
    private static final int DEFAULT_TAX = 0;

    /**
     * Calculates the tax amount an employee has to pay per year.
     *
     * Tax is calculated as 5% of the annual net income (monthly salary and other monthly income multiplied by the number of months worked minus the tax deductible)
     * minus the tax-free income.
     *
     * If the employee is unmarried and has no children, the tax-free income is Rp 54,000,000.
     * If the employee is married, the tax-free income is increased by Rp 4,500,000.
     * If the employee has children, the tax-free income is increased by Rp 4,500,000 per child up to three children.
     *
     * @param monthlySalary       the employee's monthly salary
     * @param otherMonthlyIncome  the employee's other monthly income
     * @param numberOfMonthWorking the number of months worked per year
     * @param deductible          the amount of tax deductible
     * @param isMarried           indicates whether the employee is married or not
     * @param numberOfChildren    the number of children the employee has
     * @return                    the amount of tax to pay per year
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("Error: number of months worked per year exceeds 12.");
            return DEFAULT_TAX;
        }

        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int taxFreeIncome = MAX_TAX_DEDUCTIBLE;

        if (isMarried) {
            taxFreeIncome += MARRIED_DEDUCTIBLE;
        }

        if (numberOfChildren > 0) {
            taxFreeIncome += (numberOfChildren * CHILD_DEDUCTIBLE);
        }

        int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible - taxFreeIncome;

        if (taxableIncome < 0) {
            return DEFAULT_TAX;
        }

        int tax = Math.round((TAX_RATE / 100f) * taxableIncome);
        return tax;
    }
}
