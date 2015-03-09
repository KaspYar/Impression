package Frame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Den on 09.03.2015.
 */
public class MainFrame extends JFrame{

    private JLabel hotelNameLabel;
    private JPanel rootPane;

    public MainFrame(){
        super("Hotel \"Impression\"");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(600,400));
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        hotelNameLabel = new JLabel();
    }
}
