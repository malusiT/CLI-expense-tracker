# CLI Expense Tracker 🧾
A simple command-line interface (CLI) expense tracker written in Java. This tool helps you manage and track your personal expenses directly from the terminal.

## 🚀 Features

- 📋 Add new expenses with descriptions and amounts  
- 🗑️ Delete expenses by ID  
- 📜 View a full list of all expenses  
- 📆 Get a monthly or full summary of your expenses  
- 🧪 Preloaded sample data for testing (can be disabled or modified)

## 🛠️ Usage

### Start the App

Compile and run the program:

```bash
javac Main.java Expense.java
java Main
```

## Available Commands
```bash
list                                # List all expenses
add --description "desc" --amount 50.00   # Add a new expense
delete ID                           # Delete an expense by its ID
summary                             # View total expenses
summary --month MM                  # View expenses for a specific month (e.g., 01 = January)
help                                # Show help message
Exit                                # Exit the program

```

### Example Usage
```bash
add --description "Groceries" --amount 150
summary --month 04
delete 2
list
```

## 📁 Project Structure

├── Main.java

├── Expense.java

├── list.txt (auto-generated)

└── README.md

## 🔒 Data Format

Data is saved to list.txt using the following format:
```bash
id;YYYY-MM-DD;description;amount
```
For example:
```bash
1;2025-04-25;Groceries;150.0
```


# 👨‍💻 Author
Created by [malusiT](https://github.com/malusit)
