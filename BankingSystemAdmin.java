import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankingSystemAdmin extends JFrame {

    private Map<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1;

    private JTextArea outputArea;
    private JButton addButton, editButton, deleteButton, viewButton, exitButton;

    public BankingSystemAdmin() {

        setTitle("Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        addButton = new JButton("Add Account");
        editButton = new JButton("Edit Account");
        deleteButton = new JButton("Delete Account");
        viewButton = new JButton("View Accounts");
        exitButton = new JButton("Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(exitButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccount();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAccount();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAccounts();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(mainPanel);
    }

    private void addAccount() {
        String name = JOptionPane.showInputDialog(this, "Enter account holder's name:");
        String balanceInput = JOptionPane.showInputDialog(this, "Enter initial balance:");

        try {
            double balance = Double.parseDouble(balanceInput);
            if (balance >= 0) {
                Account account = new Account(nextAccountNumber, name, balance);
                accounts.put(nextAccountNumber, account);
                outputArea.append("Account added successfully. Account Number: " + nextAccountNumber + "\n");
                nextAccountNumber++;
            } else {
                outputArea.append("Invalid balance. Balance must be non-negative.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid number for balance.\n");
        }
    }

    private void editAccount() {
        String accountNumberInput = JOptionPane.showInputDialog(this, "Enter account number to edit:");
        try {
            int accountNumber = Integer.parseInt(accountNumberInput);
            if (accounts.containsKey(accountNumber)) {
                String newName = JOptionPane.showInputDialog(this, "Enter new account holder's name:");
                String newBalanceInput = JOptionPane.showInputDialog(this, "Enter new balance:");

                try {
                    double newBalance = Double.parseDouble(newBalanceInput);
                    if (newBalance >= 0) {
                        Account account = accounts.get(accountNumber);
                        account.setName(newName);
                        account.setBalance(newBalance);
                        outputArea.append("Account updated successfully. Account Number: " + accountNumber + "\n");
                    } else {
                        outputArea.append("Invalid balance. Balance must be non-negative.\n");
                    }
                } catch (NumberFormatException e) {
                    outputArea.append("Invalid input. Please enter a valid number for balance.\n");
                }
            } else {
                outputArea.append("Account not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid account number.\n");
        }
    }

    private void deleteAccount() {
        String accountNumberInput = JOptionPane.showInputDialog(this, "Enter account number to delete:");
        try {
            int accountNumber = Integer.parseInt(accountNumberInput);
            if (accounts.containsKey(accountNumber)) {
                accounts.remove(accountNumber);
                outputArea.append("Account deleted successfully. Account Number: " + accountNumber + "\n");
            } else {
                outputArea.append("Account not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid account number.\n");
        }
    }

    private void viewAccounts() {
        if (accounts.isEmpty()) {
            outputArea.append("No accounts found.\n");
        } else {
            outputArea.append("List of accounts:\n");
            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
                Account account = entry.getValue();
                outputArea.append("Account Number: " + account.getAccountNumber() +
                        ", Name: " + account.getName() +
                        ", Balance: $" + account.getBalance() + "\n");
            }
        }
    }

    private class Account {
        private int accountNumber;
        private String name;
        private double balance;

        public Account(int accountNumber, String name, double balance) {
            this.accountNumber = accountNumber;
            this.name = name;
            this.balance = balance;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankingSystemAdmin().setVisible(true);
            }
        });
    }
}