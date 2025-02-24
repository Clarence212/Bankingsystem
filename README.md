# Banking System

A simple banking system built with Java, featuring a user interface and admin functionalities. This project demonstrates basic banking operations such as account creation, balance inquiry, deposits, and withdrawals.

## Features
- User Account Management (Create, View, Update, Delete)
- Balance Inquiry
- Deposit and Withdrawal
- Transaction History
- Admin Panel for Account Management

## Requirements
- Java Development Kit (JDK) 11 or later
- Java Runtime Environment (JRE)
- A Java IDE (Eclipse, IntelliJ IDEA, or VS Code) (Optional)

## Code Preview
Below is a brief preview of the project's main functionality:

```java
import javax.swing.*;
import java.awt.*;

public class BankingSystemFinal extends JFrame {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";
    private static double balance = 0;
    
    public BankingSystemFinal() {
        setTitle("Silk Road Bank");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        showLoginScreen();
    }

    private void showLoginScreen() {
        JLabel titleLabel = new JLabel("Welcome to Silk Road Bank!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingSystemFinal().setVisible(true));
    }
}
```

## Contributing
Contributions are welcome! If you would like to improve this project:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License
This project is licensed under the Apache 2.0 License.

## Contact
For any inquiries, feel free to open an issue or contact me at renzz2711@gmail.com

[![GitHub forks](https://img.shields.io/github/forks/Mojang/brigadier.svg?style=social&label=Fork)](https://github.com/Mojang/brigadier/fork) [![GitHub stars](https://img.shields.io/github/stars/Mojang/brigadier.svg?style=social&label=Stars)](https://github.com/Mojang/brigadier/stargazers)

