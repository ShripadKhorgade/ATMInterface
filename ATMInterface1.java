import javax.swing.*;
import java.awt.event.ActionEvent;

public class ATMInterface1 {
    private static double balance = 10000.0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM Interface");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel balanceLabel = new JLabel("Balance: Rs." + balance);
        JTextField amountField = new JTextField();

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit Money");
        JButton withdrawButton = new JButton("Withdraw Money");

        checkBalanceButton.addActionListener((ActionEvent e) -> updateBalanceLabel(balanceLabel, balance));
        depositButton.addActionListener((ActionEvent e) -> performTransaction(amountField, balanceLabel, true));
        withdrawButton.addActionListener((ActionEvent e) -> performTransaction(amountField, balanceLabel, false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(balanceLabel);
        frame.add(amountField);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }

    private static void updateBalanceLabel(JLabel label, double currentBalance) {
        label.setText("Balance: Rs." + currentBalance);
    }

    private static void performTransaction(JTextField amountField, JLabel balanceLabel, boolean isDeposit) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (isDeposit) {
                balance += amount;
            } else {
                if (amount <= balance) {
                    balance -= amount;
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            updateBalanceLabel(balanceLabel, balance);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
