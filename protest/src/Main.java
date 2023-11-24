import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static Object obj;
    static String exList = "";
    static SinglyLinkedList data = new SinglyLinkedList();
    static Income income = new Income();
    static Spend spend = new Spend();
    static SinglyLinkedList spendlist = new SinglyLinkedList();
    static JLabel titleLabel;

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> createMainPage());
    }

    private static void createMainPage() {
        JFrame frame = new JFrame("Cashable - Main Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        titleLabel = new JLabel("Cashable" + income.getIncomeTotal());
        titleLabel.setBounds(170, 20, 200, 20);
        panel.add(titleLabel);

        JButton recordIncomeButton = new JButton("Record Income");
        recordIncomeButton.setBounds(50, 80, 150, 25);
        recordIncomeButton.addActionListener(e -> {
            try {
                createRecordIncomePage();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(recordIncomeButton);

        JButton recordExpenseButton = new JButton("Record Expense");
        recordExpenseButton.setBounds(200, 80, 150, 25);
        recordExpenseButton.addActionListener(e -> {
            try {
                createRecordExpensePage();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(recordExpenseButton);

        JButton showTransactionsButton = new JButton("Show Transactions");
        showTransactionsButton.setBounds(125, 120, 150, 25);
        showTransactionsButton.addActionListener(e -> showAllExpenses());
        panel.add(showTransactionsButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(150, 160, 100, 25);
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);
    }

    private static void createRecordIncomePage() {
        JFrame incomeFrame = new JFrame("Record Income");
        incomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        incomeFrame.setSize(300, 200);

        JPanel panel = new JPanel();
        incomeFrame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Enter income:");
        label.setBounds(20, 20, 100, 20);
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(120, 20, 150, 20);
        panel.add(textField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(120, 80, 80, 25);
        submitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(textField.getText());
                income.recordIncome(amount);
                data.insert(income);
                updateIncomeLabel();
                incomeFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(submitButton);

        incomeFrame.setVisible(true);
    }

    private static void createRecordExpensePage() {
        JFrame expenseFrame = new JFrame("Record Expense");
        expenseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        expenseFrame.setSize(300, 200);

        JPanel panel = new JPanel();
        expenseFrame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel prodLabel = new JLabel("What did you buy:");
        prodLabel.setBounds(20, 20, 150, 20);
        panel.add(prodLabel);

        JTextField prodTextField = new JTextField();
        prodTextField.setBounds(180, 20, 100, 20);
        panel.add(prodTextField);

        JLabel exLabel = new JLabel("Enter expense:");
        exLabel.setBounds(20, 50, 150, 20);
        panel.add(exLabel);

        JTextField exTextField = new JTextField();
        exTextField.setBounds(180, 50, 100, 20);
        panel.add(exTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(120, 100, 80, 25);
        submitButton.addActionListener(e -> {
            try {
                String prod = prodTextField.getText();
                double ex = Double.parseDouble(exTextField.getText());

                income.deductIncome(ex);

                exList += String.format("%-33s%-5s฿\n", prod, ex);

                spend.setSpend(prod, ex);
                data.insert(new Spend(prod, ex));
                updateIncomeLabel();
                expenseFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(submitButton);

        expenseFrame.setVisible(true);
    }

    private static void updateIncomeLabel() {
        SwingUtilities.invokeLater(() -> {
            titleLabel.setText("Cashable\n" + income.getIncomeTotal());
        });
    }

    private static void showAllExpenses() {
        JOptionPane.showMessageDialog(null, "            Expenses List \n\nProduct                      Price" +
                "\n-------------------------------------\n" + exList + "\n------------------------------------- " +
                "\nTotal                            " + Spend.getTotalExpense() + "฿");
    }
}
