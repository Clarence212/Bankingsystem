import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
//
public class BankingSystemUI extends JFrame {

    private static final String USERNAME = "user123";
    private static final String PASSWORD = "password123";

    private static double balance = 0;

    private static double loanAmount = 0;
    private static LocalDate loanDeadline;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    private JButton loginButton, withdrawButton, depositButton, loanButton, balanceButton, exitButton;

    public BankingSystemUI() {

        setTitle("BDO Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLoginScreen();
    }

    // Method to show the login screen
    private void showLoginScreen() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals(USERNAME) && password.equals(PASSWORD)) {

                    showBankingInterface();
                } else {
                    JOptionPane.showMessageDialog(BankingSystemUI.this, "Invalid username or password.", "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getContentPane().removeAll();
        add(loginPanel);
        revalidate();
        repaint();
    }

    private void showBankingInterface() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        loanButton = new JButton("Loan");
        balanceButton = new JButton("Check Balance");
        exitButton = new JButton("Exit");

        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(loanButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(exitButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loan();
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        getContentPane().removeAll();
        add(mainPanel);
        revalidate();
        repaint();
    }

    // Withdraw method
    private void withdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                outputArea.append("$" + amount + " withdrawn successfully.\n");
                checkBalance();
            } else {
                outputArea.append("Insufficient balance or invalid withdrawal amount.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    // Deposit method
    private void deposit() {
        String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0) {
                balance += amount;
                outputArea.append("$" + amount + " deposited successfully.\n");
                checkBalance();
            } else {
                outputArea.append("Invalid deposit amount.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    private void loan() {
        String amountInput = JOptionPane.showInputDialog(this, "Enter loan amount:");
        String dateInput = JOptionPane.showInputDialog(this, "Enter repayment deadline (YYYY-MM-DD):");

        try {
            double amount = Double.parseDouble(amountInput);
            LocalDate deadline = LocalDate.parse(dateInput, DateTimeFormatter.ISO_DATE);

            if (amount > 0) {
                loanAmount += amount;
                balance += amount;
                loanDeadline = deadline;
                outputArea.append("Loan of $" + amount + " approved. Repayment deadline: " + deadline + ".\n");
                checkBalance();
            } else {
                outputArea.append("Invalid loan amount.\n");
            }
        } catch (Exception e) {
            outputArea.append("Invalid input. Please enter a valid amount and date (YYYY-MM-DD).\n");
        }
    }

    private void checkBalance() {
        outputArea.append("Your current balance is: $" + balance + "\n");

        if (loanAmount > 0) {
            outputArea.append("Outstanding loan amount: $" + loanAmount + "\n");
            outputArea.append("Repayment deadline: " + loanDeadline + "\n");

            LocalDate today = LocalDate.now();
            long daysRemaining = ChronoUnit.DAYS.between(today, loanDeadline);

            if (daysRemaining < 0) {
                outputArea.append("Warning: The repayment deadline has passed!\n");
            } else if (daysRemaining <= 7) {
                outputArea.append(
                        "Warning: The repayment deadline is approaching (" + daysRemaining + " days remaining).\n");
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankingSystemUI().setVisible(true);
            }
        });
    }
}
