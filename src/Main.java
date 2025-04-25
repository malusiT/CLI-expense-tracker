import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    private static final String FILE_NAME = "list.txt";

    // static ArrayList<Expense> expenses = new ArrayList<>();
    static ArrayList<Expense> expenses = (new File("list.txt")).exists() && new File("list.txt").length() > 0)
            ? loadExpenses()
            : new ArrayList<>();

    public static void main(String[] args) {

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome");
        greetingDisplay();

        // ✅ Add this line to preload test expenses:
        // if (expenses.isEmpty()) {
        //     addSampleExpenses();
        // }


        while (isRunning) {
            System.out.print("expense-tracker ");
            try {
                String fullCommand = scanner.nextLine();

                if (fullCommand.equalsIgnoreCase("Exit")) {
                    System.out.println("\nExiting Program...");
                    isRunning = false;
                    break;
                }

                if (fullCommand.equalsIgnoreCase("help")) {
                    greetingDisplay();
                    continue;
                }

                commandSpecifier(fullCommand, scanner);

            } catch (Exception e) {
                System.out.println("Invalid Input");
            }

        }

        scanner.close();

    }

    public static void greetingDisplay() {
        System.out.println("To track your expenses, here is a list of commands available to you:\n");
        System.out.println("Commands:");
        System.out.println("1. list Expenses:");
        System.out.println("   - list                   : List all expenses");
        System.out.println();
        System.out.println("2. Add Expense:");
        System.out.println("   - add --description \"expense-description\" --amount \"expense-cost\"");
        System.out.println();
        System.out.println("3. Delete Expense:");
        System.out.println("   - delete --id (id number)");
        System.out.println();
        System.out.println("4. Summary:");
        System.out.println("   - summary");
        System.out.println("   - summary --month month-number  : List expenses for a specific month");
        System.out.println();
        System.out.println("5. Help:");
        System.out.println("   - For help, type \"Help\"");
        System.out.println("5. Exit:");
        System.out.println("   - To close, type \"Exit\"\n\n");

    }

    static void commandSpecifier(String fullCommand, Scanner scanner) {

        String[] parts = fullCommand.split(" ");

        String actionCommand = parts[0];

        switch (actionCommand) {
            case "list" -> {
                listExpenses(expenses);
            }
            case "add" -> {

                String description;
                double amount;
                // add --description expense-description --amount 50

                String secondCommand = parts[1];
                String thirdCommand = parts[2];

                String forthCommand = parts[3];
                double fifthCommand = Double.parseDouble(parts[4]);

                if (secondCommand.equalsIgnoreCase("--description") && (!thirdCommand.isEmpty())) {
                    description = thirdCommand;
                } else {
                    System.out.printf("Invalid command:-> %s\n", fullCommand);
                    return;
                }

                if (forthCommand.equalsIgnoreCase("--amount") && fifthCommand > 0) {
                    amount = fifthCommand;
                } else {
                    System.out.printf("Invalid command:-> %s\n", fullCommand);
                    return;
                }

                add(description, amount);
            }
            case "delete" -> {

                if (parts.length < 2 || parts[1].isEmpty()) {
                    System.out.println("Invalid command, include an id of number value");
                    return;
                }

                try {
                    int id = Integer.parseInt(parts[1]);

                    delete(id, expenses, scanner);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid command, id not a number");
                }

            }
            case "summary" -> {

                if (parts.length == 3 && parts[1].equalsIgnoreCase("--month") && !(parts[2].isEmpty())) {
                    try {
                        int month = Integer.parseInt(parts[2]);
                        summaryOfExpenses(month, expenses);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid command, month input should be a number: " + fullCommand);
                        return;
                    }

                } else if (parts.length == 1) {
                    summaryOfExpenses(expenses);
                } else {
                    System.out.println("Invalid command, make sure command has 3 keywords " +
                            "(use --month as second input to select Month)");
                }
            }
            default -> {
                System.out.printf("Invalid command: %s\n", fullCommand);
            }

        }
    }

    static void add(String description, double amount) {

        LocalDate date = LocalDate.now();

        Expense newExpense = new Expense(description, amount, date);
        expenses.add(newExpense);
        storeExpenses(expenses);
        System.out.printf("Expense added successfully (ID: %d)\n", newExpense.getId());
    }

    static void listExpenses(ArrayList<Expense> expenses) {

        if (expenses.isEmpty()) {
            System.out.println("Expenses list currently empty");
            return;
        }
        System.out.println("Expenses for all months");

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(expenses.get(i).viewExpenses());
        }
    }

    static void summaryOfExpenses(ArrayList<Expense> expenses) {

        double totalExpenses = 0;
        for (int i = 0; i < expenses.size(); i++) {
            totalExpenses += expenses.get(i).getAmount();
        }
        System.out.printf("Total Expenses $%.2f\n", totalExpenses);

    }

    static void summaryOfExpenses(int month, ArrayList<Expense> expenses) {
        String monthName = getMonthName(month);
        if (monthName.equals("Invalid Month")) {
            System.out.printf("Invalid command\nMonth chosen is invalid -> %d\n", month);
            return;
        }
        // add --description expense-description --amount 50
        // summary --month 01

        try {

            double totalExpenses = 0;
            for (int i = 0; i < expenses.size(); i++) {

                int monthDate = expenses.get(i).getDate().getMonthValue();

                if (monthDate == month) {
                    totalExpenses += expenses.get(i).getAmount();
                }
            }
            System.out.printf("Total Expenses for %s: $%.2f\n", monthName, totalExpenses);

        } catch (NumberFormatException e) {
            System.out.println("Invalid Month Input");
        }

    }

    private static String getMonthName(int month) {
        String monthName;
        switch (month) {
            case 1 -> monthName = "January";
            case 2 -> monthName = "February";
            case 3 -> monthName = "March";
            case 4 -> monthName = "April";
            case 5 -> monthName = "May";
            case 6 -> monthName = "June";
            case 7 -> monthName = "July";
            case 8 -> monthName = "August";
            case 9 -> monthName = "September";
            case 10 -> monthName = "October";
            case 11 -> monthName = "November";
            case 12 -> monthName = "December";
            default -> monthName = "Invalid Month";
        }
        return monthName;
    }

    static void delete(int id, ArrayList<Expense> expenses, Scanner scanner) {

        System.out.printf("Are you sure you want to delete expense (ID %d)\n(y) - yes; (N) - No\n", id);
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("Yes")) {
            for (int i = 0; i < expenses.size(); i++) {

                if (id == expenses.get(i).getId()) {
                    expenses.remove(i);
                    storeExpenses(expenses);
                    System.out.println("### Expense deleted successfully\n");
                    return;
                }
            }

            System.out.println(
                    "### Could not find expense to be deleted, element may not exist\n*** Please try again! ***");
        } else {
            System.out.println("Expense not deleted");
            return;
        }

    }

    static void addSampleExpenses() {
        expenses.clear(); // Clear any old data

        expenses.add(new Expense("Groceries", 150.00, LocalDate.of(2025, 1, 15)));
        expenses.add(new Expense("Electricity", 90.00, LocalDate.of(2025, 1, 20)));
        expenses.add(new Expense("Internet", 60.00, LocalDate.of(2025, 2, 10)));
        expenses.add(new Expense("Transport", 40.00, LocalDate.of(2025, 2, 25)));
        expenses.add(new Expense("Books", 120.00, LocalDate.of(2025, 3, 5)));
        expenses.add(new Expense("Clothes", 200.00, LocalDate.of(2025, 4, 18)));
        expenses.add(new Expense("Dining Out", 75.00, LocalDate.of(2025, 4, 25)));
    }

    static void storeExpenses(ArrayList<Expense> expense) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Expense list : expense) {
                file.write(list.toString());
                file.newLine();
            }

            System.out.println("List saved");

        } catch (IOException e) {
            System.out.println("Unable to create or write to file");
        }
    }

    static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.println(line);

                String[] stringParts = line.split(";");

                if (stringParts.length != 4) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }


                int id = Integer.parseInt(stringParts[0]);

                LocalDate date = LocalDate.parse(stringParts[1]);

                String description = stringParts[2];

                double amount = Double.parseDouble(stringParts[3]);

                Expense newExpense = new Expense(description, amount, date, id);
                expenses.add(newExpense);

            }

        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }

        return expenses;
    }

}
