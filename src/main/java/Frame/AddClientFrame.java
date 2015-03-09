package Frame;

import javax.swing.*;

/**
 * Created by Den on 09.03.2015.
 */
public class AddClientFrame  extends JFrame {

    private JTextField firstNameTf;
    private JTextField lastNameTf;
    private JTextField orgTf;
    private JTextField emailTf;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel orgLbl;
    private JLabel emailLbl;
    private JPanel rootPane;
    private JButton addClientBtn;

    public AddClientFrame(){
        super("Add client");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void createUIComponents() {

    }
}
