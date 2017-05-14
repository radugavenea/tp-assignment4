package presentation;

import controller.BankController;
import entities.Account;
import entities.Person;
import presentationUtils.AccountTableModel;
import presentationUtils.GenericTableModel;
import presentationUtils.PersonTableModel;
import sun.java2d.jules.JulesAATileGenerator;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
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
    private final JButton accountDeleteButton = new JButton("Delete");


    private final JPanel otherPanel = new JPanel();
    private final JButton initializeFilesButton = new JButton("Reinitialize storage files");




    public BankView() throws HeadlessException {
        initializeFrame();
        frame.setVisible(true);
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


    public void addOtherButtonsListener(ActionListener actionListener){
        initializeFilesButton.addActionListener(actionListener);
    }

    public void addPersonButtonsListener(ActionListener listener){
        addXButtonsListener(listener,personReadButton,personAddButton,personDeleteButton);
    }

    public void addAccountButtonsListener(ActionListener listener){
        addXButtonsListener(listener,accountReadButton,accountAddButton,accountDeleteButton);
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

    public String getSelectedPersonId() {
        int row = personTable.getSelectedRow();
        return row > -1 ? personTable.getValueAt(row,0).toString() : null;
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

    private void initializeFrame(){

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,600));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dimension.width - frame.getWidth()) /2, (dimension.height - frame.getHeight()) / 2);

        setUpPersonPanel();
        setUpAccountPane();
        setUpOtherPanel();

        tabbedPane.add("People",personSplitPane);
        tabbedPane.add("Accounts", accountSplitPane);
        tabbedPane.add("Others", otherPanel);

        frame.add(tabbedPane);
    }

    private void setUpPersonPanel(){
        personTable.setModel(personTableModel);
        personSplitPane.setDividerLocation(300);
        personInputPanel.add(personIdLabel);
        personInputPanel.add(personIdInput);
        personInputPanel.add(personPNCLabel);
        personInputPanel.add(personPNCInput);
        personInputPanel.add(personNameLabel);
        personInputPanel.add(personNameInput);
        personInputPanel.add(personAddressLabel);
        personInputPanel.add(personAddressInput);
        personButtonPanel.add(personReadButton);
        personButtonPanel.add(personAddButton);
        personButtonPanel.add(personDeleteButton);
    }

    private void setUpAccountPane(){
        accountTable.setModel(accountTableModel);
        accountSplitPane.setDividerLocation(300);
        accountInputPanel.add(accountIdLabel);
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
        otherPanel.add(initializeFilesButton);
    }



    private void addXButtonsListener(ActionListener actionListener, JButton readButton, JButton addButton, JButton deleteButton) {
        readButton.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
        readButton.setActionCommand("read");
        addButton.setActionCommand("add");
        deleteButton.setActionCommand("delete");
    }

    protected JPanel makeTextPanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(0, 2));
        return panel;
    }

}
