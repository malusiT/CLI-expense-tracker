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
list — List all expenses

add --description "desc" --amount amount — Add a new expense

delete id — Delete an expense by its ID

summary — View total expenses

summary --month MM — View total expenses for a specific month (01–12)

help — Show help message

Exit — Exit the program
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

├── README.md

# 👨‍💻 Author
Created by [malusiT](https://github.com/malusit)
