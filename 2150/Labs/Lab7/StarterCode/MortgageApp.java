package cpsc2150.banking;

/**
 * Created by kplis on 1/23/2018.
 */
public class MortgageApp {

    public static void main(String [] args)
    {
        double cost = 150000;
        double dp = cost * .1;
        int y = 15;

        //customer constructor
        double debt = 100;
        double inc = 50000;
        int score = 850 ;
        String name = "Billy Bob";

        ICustomer c = new Customer(debt, inc, score, name);
        c.applyForLoan(dp, cost, y);




        System.out.println(c.toString());


    }
}
