package cpsc2150.mortgages;

public class MortgageController implements IMortgageController{

    private IMortgageView View;


    //constructor
    public MortgageController(IMortgageView v){
        this.View = v;
    }

    /**
     * This will handle the processing of a mortgage application
     * @pre: none
     * @post: none
     */
    public void submitApplication(){

        boolean newCustomer = true;

        //get info for the customer
        while(newCustomer){
            //ask for name
            String name = View.getName();

            boolean valid = false;

            //ask for yearly income and validate
            double inc = 0;
            while(!valid){
                inc = View.getYearlyIncome();
                //see if entered income is invalid
                if(inc <= 0.0) {
                    View.printToUser("Income must be greater than 0.");
                }
                else valid = true;
            }

            //ask for monthly debt and validate
            valid = false;
            double debt = 0;
            while(!valid){
                debt = View.getMonthlyDebt();
                //see if entered debt is invalid
                if(debt < 0) {
                    View.printToUser("Debt must be greater than or equal to 0.");
                }
                else valid = true;
            }

            //ask for credit score and validate
            valid = false;
            int score = 0;
            while(!valid){
                score = View.getCreditScore();
                //is entered score in valid range
                if(score <= 0 || score > 850) {
                    View.printToUser("Credit Score must be greater than 0 and less than 850");
                }
                else valid = true;
            }

            //create the new customer object
            Customer c = new Customer(debt, inc, score, name);

            //get info for the mortgage
            boolean newMortgage = true;
            while(newMortgage){

                //ask for house cost and validate
                valid = false;
                double HCost = 0;
                while(!valid){
                    HCost = View.getHouseCost();
                    //is home cost valid
                    if(HCost < 0) {
                        View.printToUser("Cost must be greater than 0.");
                    }
                    else valid = true;
                }
                //ask for down payment and validate
                valid = false;
                double DP = 0;
                while(!valid){
                    DP = View.getDownPayment();
                    //is down payment in valid range
                    if(DP <= 0 || DP >= HCost) {
                        View.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
                    }
                    else valid = true;
                }
                //ask for years of loan and validate
                valid = false;
                int y = 0;
                while(!valid){
                    y = View.getYears();
                    //is the number of years valid
                    if(y <= 0) {
                        View.printToUser("Years must be greater than 0.");
                    }
                    else valid = true;
                }

                //create the mortgage
                Mortgage m = new Mortgage(HCost, DP, y, c);

                //print out info
                View.printToUser(c.toString());
                View.printToUser(m.toString());

                //asl if user wants another mortgage
                newMortgage = View.getAnotherMortgage();
            }

            //prompt if user wants to enter a new customer
            newCustomer = View.getAnotherCustomer();

        }

    }
}
