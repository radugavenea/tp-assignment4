package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by radu on 12.05.2017.
 */
public class BankView extends JFrame {

    private final JFrame frame = new JFrame("Bank Application");

    public BankView() throws HeadlessException {
        initializeFrame();
        frame.setVisible(true);
    }


    public void initializeFrame(){

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,600));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dimension.width - frame.getWidth()) /2, (dimension.height - frame.getHeight()) / 2);


    }
}
