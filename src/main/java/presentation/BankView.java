package presentation;

import controller.BankController;
import entities.Account;
import entities.Person;
import presentationUtils.AccountTableModel;
import presentationUtils.GenericTableModel;
import presentationUtils.PersonTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by radu on 12.05.2017.
 */
public class BankView extends JFrame {

    private final JFrame frame = new JFrame("Bank Application");

    private final JTabbedPane tabbedPane = new JTabbedPane();

    private final GenericTableModel<Person> personTableModel = new PersonTableModel();
    private final JTable personTable = new JTable();
    private final JScrollPane personScrollPane = new JScrollPane(personTable);
    private final JPanel personInputPanel = makeTextPanel();
    private final JPanel personButtonPanel = new JPanel();
    private final JSplitPane personInsideSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,personInputPanel, personButtonPanel);
    private final JSplitPane personSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, personScrollPane, personInsideSplitPane);

    private final JLabel personIdLabel = new JLabel("Person id: ");
    private final JLabel personPNCLabel = new JLabel("Person personal numerical code: ");
    private final JLabel personNameLabel = new JLabel("Person name: ");
    private final JLabel personAddressLabel = new JLabel("Person address: ");
    private final JTextField personIdInput = new JTextField(30);
    private final JTextField personPNCInput = new JTextField(30);
    private final JTextField personNameInput = new JTextField(30);
    private final JTextField personAddressInput = new JTextField(30);
    private final JButton personReadButton = new JButton("Read");
    private final JButton personAddButton = new JButton("Add");
    private final JButton personEditButton = new JButton("Edit");
    private final JButton personDeleteButton = new JButton("Delete");


    private final GenericTableModel<Account> accountTableModel = new AccountTableModel();
    private final JTable accountTable = new JTable();
    private final JScrollPane accountScrollPane = new JScrollPane(accountTable);
    private final JPanel accountInputPanel = makeTextPanel();
    private final JPanel accountButtonPanel = new JPanel();
    private final JSplitPane accountInsideSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,accountInputPanel, accountButtonPanel);
    private final JSplitPane accountSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, accountScrollPane, accountInsideSplitPane);

    private final JLabel accountIdLabel = new JLabel("Account id:");
    private final JLabel accountNumberLabel = new JLabel("Account number:");
    private final JLabel accountTypeLabel = new JLabel("Account type:");
    private final JLabel accountBalanceLabel = new JLabel("Account balance:");
    private final JLabel accountPersonIdLabel = new JLabel("Account person id:");
    private final JTextField accountIdInput = new JTextField(30);
    private final JTextField accountNumberInput = new JTextField(30);
    private final JTextField accountTypeInput = new JTextField(30);
    private final JTextField accountBalanceInput = new JTextField(30);
    private final JTextField accountPersonIdInput = new JTextField(30);
    private final JButton accountReadButton = new JButton("Read");
    private final JButton accountAddButton = new JButton("Add");
    private final JButton accountEditButton = new JButton("Edit");
    private final JButton accountDeleteButton = new JButton("Delete");


    private final JPanel otherPanel = new JPanel();
    private final JButton initializeFilesButton = new JButton("Reinitialize storage file");
    private final JButton saveInFileButton = new JButton("Save into file");
    private final JLabel accountBalanceMoneyLabel = new JLabel("Your account balance: ");
    private final JTextField accountBalanceMoneyInput = new JTextField(45);
    private final JLabel accountTypeMoneyLabel = new JLabel("  Your account type:   ");
    private final JTextField accountTypeMoneyInput = new JTextField(45);
    private final JLabel accountNumberMoneyLabel = new JLabel("Your account number: ");
    private final JTextField accountNumberMoneyInput = new JTextField(45);
    private final JLabel accountSumLabel = new JLabel("Add/Withdraw money");
    private final JTextField accountSumInput = new JTextField(30);
    private final JButton accountAddMoneyButton = new JButton("Add");
    private final JButton accountWithdrawMoneyButton = new JButton("Withdraw");



    public BankView() throws HeadlessException {
        initializeFrame();
        frame.setVisible(true);
    }

    private void initializeFrame(){

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,600));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dimension.width - frame.getWidth()) /2, (dimension.height - frame.getHeight()) / 2);
        frame.setResizable(false);

        setUpPersonPanel();
        setUpAccountPane();
        setUpOtherPanel();

        tabbedPane.add("People",personSplitPane);
        tabbedPane.add("Accounts", accountSplitPane);
        tabbedPane.add("Others", otherPanel);

        frame.add(tabbedPane);
    }


    public String getPersonIdInput(){return personIdInput.getText();}
    public String getPersonPNCInput(){return personPNCInput.getText();}
    public String getPersonNameInput(){return personNameInput.getText();}
    public String getPersonAddressInput(){return personAddressInput.getText();}

    public String getAccountIdInput(){return accountIdInput.getText();}
    public String getAccountNumberInput(){return accountNumberInput.getText();}
    public String getAccountTypeInput(){return accountTypeInput.getText();}
    public String getAccountBalanceInput(){return accountBalanceInput.getText();}
    public String getAccountPersonIdInput(){return accountPersonIdInput.getText();}
    public String getAccountSumInput(){return accountSumInput.getText();}


    public String getAccountOtherTypeInput(){return accountTypeMoneyInput.getText();}


    public void addOtherButtonsListener(ActionListener actionListener){
        saveInFileButton.addActionListener(actionListener);
        saveInFileButton.setActionCommand("save");
        accountAddMoneyButton.addActionListener(actionListener);
        accountAddMoneyButton.setActionCommand("addMoney");
        accountWithdrawMoneyButton.addActionListener(actionListener);
        accountWithdrawMoneyButton.setActionCommand("withdraw");
    }

    public void addInitializeButtonListener(ActionListener listener) {
        initializeFilesButton.addActionListener(listener);
        initializeFilesButton.setActionCommand("init");
    }

    public void addPersonButtonsListener(ActionListener listener){
        addXButtonsListener(listener,personReadButton,personAddButton,personEditButton,personDeleteButton);
    }

    public void addAccountButtonsListener(ActionListener listener){
        addXButtonsListener(listener,accountReadButton,accountAddButton,accountEditButton,accountDeleteButton);
    }

    public void addPersonTableSelectionListener(ListSelectionListener listSelectionListener){
        personTable.getSelectionModel().addListSelectionListener(listSelectionListener);
    }

    public void addAccountTableSelectionListener(ListSelectionListener listSelectionListener){
        accountTable.getSelectionModel().addListSelectionListener(listSelectionListener);
    }

    public void updatePersonTable(List<Person> personList) {
        personTableModel.setDataVector(personList);
    }

    public void updateAccountTable(List<Account> accountList) {
        accountTableModel.setDataVector(accountList);
    }

    public void updatePersonInputValues() {
        int row = personTable.getSelectedRow();
        if(row > -1 ){
            personIdInput.setText(personTable.getValueAt(row,0).toString());
            personPNCInput.setText(personTable.getValueAt(row,1).toString());
            personNameInput.setText(personTable.getValueAt(row,2).toString());
            personAddressInput.setText(personTable.getValueAt(row,3).toString());
        }
    }

    public void updateAccountInputs() {
        int row = accountTable.getSelectedRow();
        if(row > -1){
            accountIdInput.setText(accountTable.getValueAt(row,0).toString());
            accountNumberInput.setText(accountTable.getValueAt(row,1).toString());
            accountTypeInput.setText(accountTable.getValueAt(row,2).toString());
            accountBalanceInput.setText(accountTable.getValueAt(row,3).toString());
            accountPersonIdInput.setText(accountTable.getValueAt(row,4).toString());
        }
    }

    public void updateOtherPanel() {
        int row = accountTable.getSelectedRow();
        if(row > -1){
            accountNumberMoneyInput.setText(accountTable.getValueAt(row,1).toString());
            accountTypeMoneyInput.setText(accountTable.getValueAt(row,2).toString());
            accountBalanceMoneyInput.setText(accountTable.getValueAt(row,3).toString());
        }
    }


    public String getSelectedPersonId() {
        int row = personTable.getSelectedRow();
        return row > -1 ? personTable.getValueAt(row,0).toString() : null;
    }


    private void setUpPersonPanel(){
        personTable.setModel(personTableModel);
        personSplitPane.setDividerLocation(300);
        personInputPanel.add(personIdLabel);
        personIdInput.setEditable(false);
        personInputPanel.add(personIdInput);
        personInputPanel.add(personPNCLabel);
        personInputPanel.add(personPNCInput);
        personInputPanel.add(personNameLabel);
        personInputPanel.add(personNameInput);
        personInputPanel.add(personAddressLabel);
        personInputPanel.add(personAddressInput);
        personButtonPanel.add(personReadButton);
        personButtonPanel.add(personAddButton);
        personButtonPanel.add(personEditButton);
        personButtonPanel.add(personDeleteButton);
    }

    private void setUpAccountPane(){
        accountTable.setModel(accountTableModel);
        accountSplitPane.setDividerLocation(300);
        accountInputPanel.add(accountIdLabel);
        accountIdInput.setEditable(false);
        accountInputPanel.add(accountIdInput);
        accountInputPanel.add(accountNumberLabel);
        accountInputPanel.add(accountNumberInput);
        accountInputPanel.add(accountTypeLabel);
        accountInputPanel.add(accountTypeInput);
        accountInputPanel.add(accountBalanceLabel);
        accountInputPanel.add(accountBalanceInput);
        accountInputPanel.add(accountPersonIdLabel);
        accountInputPanel.add(accountPersonIdInput);
        accountButtonPanel.add(accountReadButton);
        accountButtonPanel.add(accountAddButton);
        accountButtonPanel.add(accountDeleteButton);
    }

    private void setUpOtherPanel(){
        otherPanel.add(accountNumberMoneyLabel);
        accountNumberMoneyInput.setEditable(false);
        otherPanel.add(accountNumberMoneyInput);
        otherPanel.add(accountTypeMoneyLabel);
        accountTypeMoneyInput.setEditable(false);
        otherPanel.add(accountTypeMoneyInput);
        otherPanel.add(accountBalanceMoneyLabel);
        accountBalanceMoneyInput.setEditable(false);
        otherPanel.add(accountBalanceMoneyInput);
        otherPanel.add(accountSumLabel);
        otherPanel.add(accountSumInput);
        otherPanel.add(accountAddMoneyButton);
        otherPanel.add(accountWithdrawMoneyButton);
        otherPanel.add(saveInFileButton);
        otherPanel.add(initializeFilesButton);
    }



    private void addXButtonsListener(ActionListener actionListener, JButton readButton, JButton addButton,
                                     JButton editButton ,JButton deleteButton) {
        readButton.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        editButton.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
        readButton.setActionCommand("read");
        addButton.setActionCommand("add");
        editButton.setActionCommand("edit");
        deleteButton.setActionCommand("delete");
    }

    private JPanel makeTextPanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(0, 2));
        return panel;
    }

    public void disableAddButton() {
        accountAddMoneyButton.setEnabled(false);
    }

    public void displayOnlyOneDepositMessage() {
        JOptionPane.showMessageDialog(frame,"Only one deposit is allowed to be made to a saving account");
    }

    public void displayOnlyOneWithdrawMessage() {
        JOptionPane.showMessageDialog(frame,"Only one withdraw is allowed to be made from a saving account");
    }

    public void displayWithdrawLimitMessage(String limit) {
        JOptionPane.showMessageDialog(frame,"You have a limit of " + limit + " per transaction");
    }

    public void displayNotEnoughMoneyMessage() {
        JOptionPane.showMessageDialog(frame,"You have not enough money in your account");
    }

    public void displayTransactionSuccessfulMessage() {
        JOptionPane.showMessageDialog(frame,"The transaction was successful");
    }

    public void displaySavingAccountSuccessfulMessage(double interest) {
        JOptionPane.showMessageDialog(frame,"The transaction was successful. An interest of " + interest +
        " has been charged from your account");
    }

    public void displayAccountModificationToPerson(String name) {
        JOptionPane.showMessageDialog(frame,"Hey " + name + "! A transaction has been made to your account");
    }

    public void displayAccountNotValidMessage() {
        JOptionPane.showMessageDialog(frame,"The information you have enter are not valid for a account");
    }

}
