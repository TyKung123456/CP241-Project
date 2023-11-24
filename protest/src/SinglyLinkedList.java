import javax.swing.*;
import java.util.List;

public class SinglyLinkedList implements ListADT{
    Object e;
    MyNode first, current;

    private MyNode incomeFirst, incomeCurrent;
    private MyNode expenseFirst, expenseCurrent;
    private double expensesTotal;

    public SinglyLinkedList() {
        List();
    }


    @Override
    public void List() {
        incomeFirst = incomeCurrent = expenseFirst = expenseCurrent = null;
    }

    @Override
    public void insert(Object e) throws Exception {
        MyNode newNode = new MyNode(e, null);
        if (e instanceof Income) {
            // Insert into income list
            if (incomeFirst == null) {
                incomeFirst = incomeCurrent = newNode;
            } else {
                incomeCurrent.setNextNode(newNode);
                incomeCurrent = newNode;
            }
        } else if (e instanceof Spend) {
            // Insert into expense list
            if (expenseFirst == null) {
//                System.out.println("dddd");
                expenseFirst = expenseCurrent = newNode;
            } else {
//                System.out.println("aaaa");
                expenseCurrent.setNextNode(newNode);
                expenseCurrent = newNode;
            }
            // Update expensesTotal
//            Spend spend = (Spend) e;
//            expensesTotal += spend.getExpense();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + e.getClass().getName());
        }
    }

    @Override
    public Object retrieve() throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty");
        } else {
            return expenseCurrent.getData();
        }
    }

    @Override
    public void delete() throws Exception {
        MyNode previous;
        if (isEmpty()) {
            throw new Exception("List is empty");
        } else {
            previous = first;
            if (previous == current && current.getNextNode() == null)
                //have only one data
                first = current = null;
            else if (previous == current && current.getNextNode() != null) {
                // ตัวแรกเท่ากับcurrent และ data more than one
                first = first.getNextNode();
                current = first;
            } else {
                while (previous.getNextNode() != current) // ตัวแรกไม่เท่ากับcurrent
                    previous = previous.getNextNode();
                previous.setNextNode(current.getNextNode());
                current = first;
            }
        }
    }

    @Override
    public void update(Object e) throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty");
        } else {
            current.setData(e);
        }
    }

    @Override
    public void findFirstExpenses() throws Exception {
        if (expenseFirst == null) {
            throw new Exception("Expense list is empty");
        } else {
            expenseCurrent = expenseFirst;
        }
    }

    @Override
    public void findNextExpenses() throws Exception {
        if (expenseCurrent == null || expenseCurrent.getNextNode() == null) { // expenseCurrent = last
            throw new Exception("No next expense data");
        } else {
            expenseCurrent = expenseCurrent.getNextNode();
        }
    }

    @Override
    public boolean findKey(Object tKey) {
        return this == tKey;
    }

    @Override
    public boolean isEmpty() {
        return expenseFirst == null;
    }

    @Override
    public boolean isFull() { //ไม่ต้องตรวจ
        return false;
    }

//    public void showAllExpenses() {
//        try {
//            findFirstExpenses(); // Start from the first expense node
//
//            StringBuilder allExpenses = new StringBuilder();
//
//            while (expenseCurrent != null) {
//                Object obj = retrieve();
//                Spend spend = (Spend) obj;
//                allExpenses.append("Product: ").append(spend.getProduct()).append(", Expense: ").append(spend.getExpense()).append("\n");
//
//                findNextExpenses(); // Move to the next expense node
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
