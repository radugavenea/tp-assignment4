package controller;

import businessLayer.Bank;
import dataAccessLayer.AccountDAO;
import dataAccessLayer.PersonDAO;
import entities.Person;
import presentation.BankView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by radu on 12.05.2017.
 */
public class BankController {

    private BankView view;
    private Bank bankService;

    public BankController(BankView view) {
        this.view = view;
        this.bankService = new Bank(new PersonDAO(), new AccountDAO());

        view.addOtherButtonsListener(new ButtonsActionListener());
        view.addPersonButtonsListener(new PersonButtonsActionListener());
    }


    class ButtonsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            bankService.reinitializeFiles();
        }
    }

    class PersonButtonsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "read":
                    view.updatePersonTable(bankService.getMappedAllPerson());
                    break;
                case "add":
                    bankService.addNewPerson(new Person(Integer.parseInt(view.getPersonIdInput()),
                            view.getPersonPNCInput(),view.getPersonNameInput(),view.getPersonAddressInput()));
                    break;
                case "delete":
                    bankService.deletePersonById(Integer.parseInt(view.getPersonIdInput()));
                    break;
            }
        }
    }
}
