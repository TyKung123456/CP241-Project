import javax.swing.*;

public class Main {
    static Object obj;
    static String exList = "";
    static SinglyLinkedList data = new SinglyLinkedList();
    static Income income = new Income();
    static Spend spend = new Spend();
    static SinglyLinkedList spendlist = new SinglyLinkedList();


    public static void main(String[] args) throws Exception {
        //Income income = new Income();

        String optionInput = JOptionPane.showInputDialog(null, "-----------------------------------\n" +
                "               Cashable\n                    " + income.getIncomeTotal() +
                "\n-----------------------------------\n" + "     1. Record Income\n" +
                "     2. Record Expense\n" + "     3. Show Transactions\n" + "     4. Exit\n");

        switch (optionInput) {
            case "1":
                try {
                    recordIncome();
                    //data.insert(recordIncome(income));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                main(new String[]{});
                break;
            case "2":
                try {
                    //data.insert(recordExpense());
                    recordExpense();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                main(new String[]{});
                break;
            case "3":
                showAllExpenses();
                main(new String[]{});
                break;
            case "4":
                break;
            default:
                System.out.print("Please Input Option");
                main(new String[]{});
        }

    }
    private static void recordIncome() {
        double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter income: "));
        income.recordIncome(amount);
        try {
            data.insert(income);
//            JOptionPane.showMessageDialog(null, "All Income: " + income.getIncomeTotal());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
//    private static Object recordIncome(Income income) {
//        double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter income: "));
//        income.recordIncome(amount);
//        return amount;
//    }



    private static void recordExpense() {
        String prod = JOptionPane.showInputDialog(null, "What did you buy: ");
        double ex = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter expense: "));

        income.deductIncome(ex);

        exList += String.format("%-33s%-5s฿\n",prod,ex);

        spend.setSpend(prod,ex);
        try {
            data.insert(new Spend(prod,ex));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

//    private static Object recordExpense() {
//        Spend temp;
//        String prod = JOptionPane.showInputDialog(null, "What is you buy: ");
//        double ex = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter expense : "));
//        temp = new Spend(prod, ex);
//        return temp;
//    }


    
//    public static void showAllExpenses() {
//        try {
//            data.findFirstExpenses();
//            while (data.retrieve() != null) {
//                obj = data.retrieve();
////                System.out.println("obj = " + obj);
//                Spend expense = (Spend) obj;
////                if (expense.getExpense() > 0) {
//                    JOptionPane.showMessageDialog(null, "Expense: " + expense.getProduct() + ", Amount: " + expense.getExpense(), "Message", JOptionPane.INFORMATION_MESSAGE);
//               // }
//                if (data.retrieve() != null) {
//                    data.findNextExpenses();
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }


    public static void showAllExpenses() {
        JOptionPane.showMessageDialog(null,"            Expenses List \n\nProduct                      Price" +
                "\n-------------------------------------\n" + exList + "\n------------------------------------- " +
                "\nTotal                            " + Spend.getTotalExpense() + "฿");
    }

//    private static void showAllExpenses() {
//        try {
//            data.findFirstExpenses();
//            StringBuilder allExpenses = new StringBuilder();
//
//            while (data.retrieve() != null) {
//                Object obj = data.retrieve();
//                if (obj instanceof Spend) {
//                    Spend spend = (Spend) obj;
//                    allExpenses.append("Product: ").append(spend.getProduct()).append(", Expense: ").append(spend.getExpense()).append("\n");
//                }
//
//                data.findNextExpenses();  // Assuming you have a method to find the next expense
//            }
//
//            if (allExpenses.length() > 0) {
//                JOptionPane.showMessageDialog(null, allExpenses.toString(), "All Expenses", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "No expenses recorded.", "All Expenses", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }


}