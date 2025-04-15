import java.time.LocalDate;

public class Expense {

    private static int idCounter = 1;
    private String description;
    private LocalDate date;
    private double amount;
    private int id;

    public Expense(String description, double amount, LocalDate date){
        this.description = description;
        this.amount = amount;
        this.id = idCounter++;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //get expenses
    //add expenses
    //delete expenses

    public String viewExpenses(){
        if(id > 1 ) {
            return String.format("### %-2d   %-15s   %-25s   $%5.2f ", id, date, description, amount);
        }else{
            return String.format("### Id " + "  Date            " + "Description              " + "     Amount \n" +
                                 "### %-2d   %-15s   %-25s   $%5.2f ", id, date, description, amount);
        }
    }



    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", id=" + id +
                '}';
    }


}
