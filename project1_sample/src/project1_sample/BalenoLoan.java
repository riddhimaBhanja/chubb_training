package project1_sample;
import java.util.Scanner;

class Car {
    String model, color;
    double price;

    Car(String model, String color) {
        this.model = model;
        this.color = color;

        switch (model.toLowerCase()) {
            case "delta":
                price=800000;
                break;
            case "beta":
                price=1000000;
                break;
            case "alfa":
                price=1200000;
                break;
            default:
                System.out.println("Invalid model");
                price=0;
        }
    }
}

class LoanCalculator {
    double principal;
    double rate;
    int years;
    String calculatorType;

    LoanCalculator(double principal, double rate, int years, String calculatorType) {
        this.principal = principal;
        this.rate = rate;
        this.years = years;
        this.calculatorType = calculatorType.toLowerCase();
    }

    double totalAmount() {
        double total;
        if (calculatorType.equals("compound")) {
           
            total = principal * Math.pow((1 + rate / 100), years);
        } else {
         
            double si = (principal * rate * years) / 100;
            total = principal + si;
        }
        return total;
    }

    double emi() {
        double total = totalAmount();
        int months = years * 12;
        return total / months;
    }
}

public class BalenoLoan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter model (Delta/Beta/Alfa): ");
        String model = sc.next();

        System.out.print("Enter color: ");
        String color = sc.next();

        Car car = new Car(model, color);
        System.out.println("Car price: ₹" + car.price);

        System.out.print("EnterDelta down payment amount: ");
        double downPayment = sc.nextDouble();

        double loanAmount = car.price - downPayment;

        if (loanAmount <= 0) {
            System.out.println("Invalid down payment! It cannot be greater than or equal to car price.");
            sc.close();
            return;
        }

        System.out.print("Enter interest rate (% per year): ");
        double rate = sc.nextDouble();

        System.out.print("Enter loan tenure (3 or 5 years): ");
        int years = sc.nextInt();

        System.out.print("Which calculator do you want (simple/compound)? ");
        String calculatorType = sc.next().toLowerCase();

        LoanCalculator loanCalc = new LoanCalculator(loanAmount, rate, years, calculatorType);

        double total = loanCalc.totalAmount();
        double emi = loanCalc.emi();

        System.out.println("\n--- Loan Summary ---");
        System.out.println("Car Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Car Price: ₹" + car.price);
        System.out.println("Down Payment: ₹" + downPayment);
        System.out.println("Loan Amount: ₹" + loanAmount);
        System.out.println("Interest Rate: " + rate + "%");
        System.out.println("Tenure: " + years + " years");
        System.out.println("Calculator Type: " + calculatorType.toUpperCase());
        System.out.printf("Total Amount Payable: ₹%.2f%n", total);
        System.out.printf("Monthly EMI: ₹%.2f%n", emi);

        sc.close();
    }
}