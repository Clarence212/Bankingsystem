import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
//4th
public class BankingSystemFinal extends JFrame {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    private static double balance = 0;
    private static double loanAmount = 0;
    private static LocalDate loanDeadline;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    private JButton loginButton, withdrawButton, depositButton, loanButton, balanceButton, exitButton, helpdeskButton;

    public BankingSystemFinal() {
        setTitle("Silk Road Bank");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLoginScreen();
    }

    private void showLoginScreen() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Welcome to Silk Road Bank!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                showBankingInterface();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        getContentPane().removeAll();
        add(loginPanel);
        revalidate();
        repaint();
    }

    // Main Banking Interface
    private void showBankingInterface() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        loanButton = new JButton("Loan");
        balanceButton = new JButton("Check Balance");
        helpdeskButton = new JButton("Helpdesk");
        exitButton = new JButton("Exit");

        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(loanButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(helpdeskButton);
        buttonPanel.add(exitButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        withdrawButton.addActionListener(e -> withdraw());
        depositButton.addActionListener(e -> deposit());
        loanButton.addActionListener(e -> loan());
        balanceButton.addActionListener(e -> checkBalance());
        helpdeskButton.addActionListener(e -> openHelpdesk());
        exitButton.addActionListener(e -> System.exit(0));

        getContentPane().removeAll();
        add(mainPanel);
        revalidate();
        repaint();
    }

    // Withdraw Method
    private void withdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                outputArea.append("₱" + amount + " withdrawn successfully.\n");
                checkBalance();
            } else {
                outputArea.append("Insufficient balance or invalid withdrawal amount.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    // Deposit Method
    private void deposit() {
        String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0) {
                balance += amount;
                outputArea.append("₱" + amount + " deposited successfully.\n");
                checkBalance();
            } else {
                outputArea.append("Invalid deposit amount.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    // Loan Method (Restored Loan Repayment Date)
    // Loan Method (Updated without Repayment Deadline)
// Loan Method (Updated with Repayment Deadline Calculation)
private void loan() {
    String amountInput = JOptionPane.showInputDialog(this, "Enter loan amount:");

    try {
        double amount = Double.parseDouble(amountInput);

        if (amount > 0) {
            // Ask user how many months they want to pay the loan
            String monthsInput = JOptionPane.showInputDialog(this, "How many months would you like to pay your loan?");
            int months = Integer.parseInt(monthsInput);

            if (months > 0) {
                double monthlyPayment = amount / months;
                loanAmount += amount;
                balance += amount;

                // Calculate repayment deadline based on current date and number of months
                LocalDate repaymentDate = LocalDate.now().plusMonths(months);

                loanDeadline = repaymentDate; // Set the repayment deadline
                outputArea.append("Loan of ₱" + amount + " approved.\n");
                outputArea.append("Your monthly payment will be: ₱" + String.format("%.2f", monthlyPayment) + " for " + months + " months.\n");
                outputArea.append("Repayment deadline: " + repaymentDate.getMonth() + " " + repaymentDate.getYear() + ".\n");
                checkBalance();
            } else {
                outputArea.append("Invalid number of months.\n");
            }
        } else {
            outputArea.append("Invalid loan amount.\n");
        }
    } catch (Exception e) {
        outputArea.append("Invalid input. Please enter a valid amount.\n");
    }
}

    // Check Balance Method
    private void checkBalance() {
        outputArea.append("Your current balance is: ₱" + balance + "\n");

        if (loanAmount > 0) {
            outputArea.append("Outstanding loan amount: ₱" + loanAmount + "\n");
            outputArea.append("Repayment deadline: " + loanDeadline + "\n");

            LocalDate today = LocalDate.now();
            long daysRemaining = ChronoUnit.DAYS.between(today, loanDeadline);

            if (daysRemaining < 0) {
                outputArea.append("Warning: The repayment deadline has passed!\n");
            } else if (daysRemaining <= 7) {
                outputArea.append("Warning: The repayment deadline is approaching (" + daysRemaining + " days remaining).\n");
            }
        }
    }

    private void openHelpdesk() {
        String[] options = { "What is my current balance?", "Do I have any active loans?", "Contact Us" };
        int choice = JOptionPane.showOptionDialog(this, 
                "Select your question:", "Helpdesk",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    
        if (choice == 0) {
            checkBalance();
        } else if (choice == 1) {
            if (loanAmount > 0) {
                outputArea.append("Your current loan amount: ₱" + loanAmount + "\n");
    
                // Display the repayment deadline with month and year
                if (loanDeadline != null) {
                    outputArea.append("Repayment deadline: " + loanDeadline.getMonth() + " " + loanDeadline.getYear() + "\n");
                } else {
                    outputArea.append("No active loans.\n");
                }
            } else {
                outputArea.append("No active loans.\n");
            }
        } else if (choice == 2) {
            // When "Contact Us" option is selected
            JOptionPane.showMessageDialog(this, 
                "For more inquiries, you can email us at silkroadbank@gmail.com", 
                "Contact Us", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingSystemFinal().setVisible(true));
    }
}
