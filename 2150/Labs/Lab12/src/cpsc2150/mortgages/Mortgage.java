package cpsc2150.mortgages;
import java.util.*;

public class Mortgage extends AbsMortgage implements IMortgage{
    /**
     * @invariant payment = (rate * principal) / (1-(1+rate)^ -numPayments)
     * @invariant 0 <= rate <= 1
     * @invariant 0 < DtoIRatio
     * @invariant MIN_YEARS * 12 <= numPayments <= MAX_YEARS * 12
     * @invariant 0 < principal
     * @invariant 0 <= perDown < 1
     *
     * Correspondence Payment = payment
     * Correspondence Rate = rate
     * Correspondence Customer = c
     * Correspondence DebtToIncomeRatio = DtoIRatio
     * Correspondence Principal = principal
     * Correspondence NumberOfPayments = numPayments
     * Correspondence PercentDown = perDown
     */

    public static final int MONTHS_IN_YEAR = 12;

    private double payment;
    private double rate;
    private ICustomer c;
    private double DtoIRatio;
    private double principal;
    private int numPayments;
    private double perDown;
    private int years;

    /**
     * @param homeCost cost of the house
     * @param downPayment down payment for the house (initial upfront payment)
     * @param y number of years for which the loan is being paid
     * @param customer customer applying for the mortgage
     * @requires 0 < homeCost and 0 <= downPayment and 0 < y
     * @ensures principal = homeCost - downPayment
     * @ensures perDown = downPayment / homeCost
     * @ensures rate = BASERATE * years * perDown
     * @ensures numPayments = y * 12
     * @ensures payment = (rate * principal) / (1-((1+rate)^-numPayments))
     * @ensures c = customer
     * @ensures DtoIRatio = c.getMonthlyDebtPayments() / c.getIncome()
     * @ensures years = y;
     */
    public Mortgage(double homeCost, double downPayment, int y, ICustomer customer){
        principal = homeCost - downPayment;
        perDown = downPayment / homeCost;

        double APR = 0;
        APR += BASERATE;
        //add to rate based on years
        if( y < MAX_YEARS){ APR += GOODRATEADD;}
        else{ APR += NORMALRATEADD;}
        //add to rate based on percent down
        if(perDown < PREFERRED_PERCENT_DOWN){APR += GOODRATEADD;}
        //add to rate based on credit score
        if(customer.getCreditScore() < BADCREDIT){APR += VERYBADRATEADD;}
        else if(customer.getCreditScore() < FAIRCREDIT){ APR += BADRATEADD;}
        else if(customer.getCreditScore() < GOODCREDIT){APR += NORMALRATEADD;}
        else if(customer.getCreditScore() < GREATCREDIT){APR += GOODRATEADD;}

        rate = APR / MONTHS_IN_YEAR;

        numPayments = y * MONTHS_IN_YEAR;
        payment = (rate * principal) / (1- Math.pow((1+rate), -numPayments));
        c = customer;
        DtoIRatio = ((c.getMonthlyDebtPayments() + payment) * MONTHS_IN_YEAR) / c.getIncome();
        years = y;

    }

    public boolean loanApproved(){
        if( (getRate() < RATETOOHIGH) && (perDown >= MIN_PERCENT_DOWN) && (DtoIRatio <= DTOITOOHIGH)){
            return true;
        }
        else {return false;}
    }

    public double getPayment(){
        return payment;
    }

    public double getRate(){
        return (rate * MONTHS_IN_YEAR);
    }

    public double getPrincipal(){
        return principal;
    }

    public int getYears(){
        return years;
    }
}
