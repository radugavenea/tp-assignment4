package bank;

import controller.BankController;
import presentation.BankView;

import javax.swing.*;

/**
 * Created by radu on 12.05.2017.
 */
public class Launcher {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankController(new BankView());
            }
        });

    }
}
