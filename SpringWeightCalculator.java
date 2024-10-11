import java.util.Scanner;

public class SpringWeightCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("WELCOME TO THE SPRING WEIGHT CALCULATOR (0 TO QUIT, 1 TO PROCEED)");
        int proceed = input.nextInt();
        if (proceed == 0) {
            System.out.println("GOODBYE!");
        } else if (proceed == 1) {
            System.out.println("Enter coil diameter D (m):");
            double diameter = getValidInput(input, 0.25, 1.3);
            System.out.println("Enter wire diameter d (m):");
            double wireDiameter = getValidInput(input, 0.05, 2);
            System.out.println("Enter number of turns N:");
            int turns = getValidInput(input, 1, 15);
            double weight = calculateSpringWeight(diameter, wireDiameter, turns);
            double truncated = Math.floor(weight * 100) / 100.0;
            System.out.println("Weight: " + truncated + " kgm/s^2");
            System.out.println("GOODBYE!");
        }
    }

    public static double getValidInput(Scanner input, double min, double max) {
        double value = 0;
        boolean validInput = false;

        while (!validInput) {
            if (input.hasNextDouble()) {
                value = input.nextDouble();
                // Check if value is positive and within bounds
                if (value > 0 && value >= min && value <= max) {
                    validInput = true;
                } else {
                    if (value < 0) {
                        System.out.println("ENTER A POSITIVE INPUT");
                    } else if (value < min || value > max) {
                        System.out.println("INPUT MUST BE WITHIN BOUNDS");
                    }
                }
            } else {
                // if character/non-numeric input is entered
                System.out.println("ENTER A VALID INPUT");
                input.next(); // Clear the invalid input
            }
        }
        return value;
    }

    public static int getValidInput(Scanner input, int min, int max) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            if (input.hasNextInt()) {
                // Handle integer input
                value = input.nextInt();

                // Check if value is positive and within bounds
                if (value >= min && value <= max) {
                    validInput = true;
                } else {
                    if (value < 0) {
                        System.out.println("N SHOULD BE A POSITIVE INTEGER");
                    } else {
                        System.out.println("INPUT MUST BE WITHIN BOUNDS");
                    }
                }

            } else if (input.hasNextDouble()) {
                // Handle double input
                double doubleValue = input.nextDouble();

                // Check if it's a whole number (like 1.0, 2.0, etc.)
                if (doubleValue == (int) doubleValue && doubleValue > 0) {
                    value = (int) doubleValue;
                    if (value >= min && value <= max) {
                        validInput = true;
                    } else {
                        if (value < 0) {
                            System.out.println("N SHOULD BE A POSITIVE INTEGER");
                        } else {
                            System.out.println("INPUT MUST BE WITHIN BOUNDS");
                        }
                    }

                } else if (doubleValue < 0) {
                    // Output message for non-integer, negative doubles
                    System.out.println("N SHOULD BE A POSITIVE INTEGER");
                } else {
                    // Output message for non-integer doubles
                    System.out.println("N SHOULD BE AN INTEGER");
                }

            } else {
                // if character/non-numeric input is entered
                System.out.println("ENTER A VALID INPUT");
                input.next(); // Clear the invalid input
            }
        }

        return value;
    }

    public static double calculateSpringWeight(double diameter, double wireDiameter, int turns) {
        double springMass = (turns + 2) * ((diameter) * (wireDiameter * wireDiameter));
        double springWeight = springMass * 9.81;

        return springWeight;
    }
}
