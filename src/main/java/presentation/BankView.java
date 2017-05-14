package presentation;

import entities.Account;
import entities.Person;
import presentationUtils.AccountTableModel;
import presentationUtils.GenericTableModel;
import presentationUtils.PersonTableModel;
import sun.java2d.jules.JulesAATileGenerator;

import javax.swing.*;
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


    public void addOtherButtonsListener(ActionListener actionListener){
        initializeFilesButton.addActionListener(actionListener);
    }

    public void addPersonButtonsListener(ActionListener actionListener){
        personReadButton.addActionListener(actionListener);
        personAddButton.addActionListener(actionListener);
        personDeleteButton.addActionListener(actionListener);
        personReadButton.setActionCommand("read");
        personAddButton.setActionCommand("add");
        personDeleteButton.setActionCommand("delete");
    }


    public void updatePersonTable(List<Person> personList) {
        personTableModel.setDataVector(personList);
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

    }

    private void setUpOtherPanel(){
        otherPanel.add(initializeFilesButton);
    }

    protected JPanel makeTextPanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(0, 2));
        return panel;
    }

}
