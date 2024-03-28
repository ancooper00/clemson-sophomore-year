package cpsc2150.mortgages;

public class MortgageController implements IMortgageController{

    private IMortgageView View;


    //constructor
    public MortgageController(IMortgageView v){
        View = v;
    }

    /**
     * This will handle the processing of a mortgage application
     * @pre: none
     * @post: none
     */
    public void submitApplication(){

        int MAX_CREDIT_SCORE = 850; //constant for max bound on credit score

        boolean approved = false;

        //ask for name
        String name = View.getName();

        //ask for yearly income and validate
        boolean validInc = true;
        double inc = 0;
        inc = View.getYearlyIncome();
        //see if entered income is invalid
        if(inc <= 0) validInc = false;
        else validInc = true;

        //ask for monthly debt and validate
        boolean validDebt = true;
        double debt = 0;
        debt = View.getMonthlyDebt();
        //see if entered debt is invalid
        if(debt < 0) validDebt = false;
        else validDebt = true;

        //ask for credit score and validate
        boolean validCS = false;
        int score = 0;
        score = View.getCreditScore();
        //is entered score in valid range
        if(score <= 0 || score > MAX_CREDIT_SCORE) validCS = false;
        else validCS = true;

        //ask for house cost and validate
        boolean validCost = false;
        double HCost = 0;
        HCost = View.getHouseCost();
        //is home cost valid
        if(HCost < 0)  validCost = false;
        else validCost = true;

        //ask for down payment and validate
        boolean validDP = false;
        double DP = 0;
        DP = View.getDownPayment();
        //is down payment in valid range
        if(DP <= 0 || DP >= HCost) validDP = false;
        else validDP = true;

        //ask for years of loan and validate
        boolean validY= false;
        int y = 0;
        y = View.getYears();
        //is the number of years valid
        if(y <= 0) validY = false;
        else validY = true;

        //if any input returns false, show error message to user, else move on
        if(!validInc) View.printToUser("Income must be greater than 0.");
        else if(!validDebt) View.printToUser("Debt must be greater than or equal to 0.");
        else if(!validCS) View.printToUser("Credit Score must be greater than 0 and less than 850");
        else if(!validCost) View.printToUser("Cost must be greater than 0.");
        else if(!validDP) View.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
        else if(!validY) View.printToUser("Years must be greater than 0.");
        else approved = true;

        //create the new customer object
        Customer c = new Customer(debt, inc, score, name);
        //create the mortgage
        Mortgage m = new Mortgage(HCost, DP, y, c);

        //display info to user
        View.displayApproved(approved);
        //display proper info if loan approved or not
        if(!approved){ //if loan not approved
            View.displayRate(0);
            View.displayPayment(0);
        }
        else{
            View.displayRate(m.getRate());
            View.displayPayment(m.getPayment());
        }

    }
}

