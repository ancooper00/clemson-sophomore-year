package cpsc2150.mortgages;

import java.util.Scanner;

public class MortgageView implements IMortgageView{

    Scanner sc = new Scanner(System.in);
    private IMortgageController Controller;

    public void setController(IMortgageController c){
        this.Controller = c;
    }

    public double getHouseCost(){
        System.out.println("How much does the house cost?");
        return sc.nextDouble();
    }

    public double getDownPayment(){
        System.out.println("How much is the down payment?");
        return sc.nextDouble();
    }

    public int getYears(){
        System.out.println("How many years?");
        return sc.nextInt();
    }

    public double getMonthlyDebt(){
        System.out.println("How much are your monthly debt payments?");
        return sc.nextDouble();
    }

    public double getYearlyIncome(){
        System.out.println("How much is your yearly income?");
        return sc.nextDouble();
    }

    public int getCreditScore(){
        System.out.println("What is your credit score?");
        return sc.nextInt();
    }

    public String getName(){
        System.out.println("What is your name?");
        String name = sc.nextLine();
        return name;
    }

    public void printToUser(String s){
        System.out.println(s);
    }

    public void displayPayment(double p){
        System.out.println("Monthly Payment: $" + p);
    }

   public void displayRate(double r){
       double rPer = r * 100;
       System.out.println("Interest Rate: " + rPer + "%");
   }

    public void displayApproved(boolean a){
        if(a) {
            System.out.println("Mortgage Approved");
        }
        else{
            System.out.println("Mortgage Denied");
        }
    }

    public boolean getAnotherMortgage(){
        System.out.println("Would you like to apply for another mortgage? Y/N");
        char choice = sc.next().charAt(0);
        if(choice == 'y' || choice == 'Y'){
            return true;
        }
        else return false;
    }

    public boolean getAnotherCustomer(){
        System.out.println("Would you like to enter a new customer?");
        String s = sc.nextLine();
        char choice = sc.nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y'){
            return true;
        }
        else return false;
    }
}
