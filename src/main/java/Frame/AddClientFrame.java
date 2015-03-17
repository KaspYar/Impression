package Frame;

import Hotel.Client;
import Model.ManagerDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Den on 09.03.2015.
 */
public class AddClientFrame extends JFrame {

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
    private JLabel pswLbl;
    private JPasswordField pswTf;
    private JLabel cardLbl;
    private JTextField cardNumTf;

    public AddClientFrame(){
        super("Add client");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();

        addClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cardNum = cardNumTf.getText();
                String firstName = firstNameTf.getText();
                String lastName = lastNameTf.getText();
                String email = emailTf.getText();
                String password = pswTf.getText();
                String organization = orgTf.getText();

                if (cardNum.equals("") || firstName.equals("") || lastName.equals("")
                        || email.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(AddClientFrame.this,"Please, fill empty fields!");
                } else if(!validateEmail(email)){
                    JOptionPane.showMessageDialog(AddClientFrame.this,"Please, enter correct email!");
                } else{
                    Client client = new Client(Integer.valueOf(cardNum),firstName,lastName,organization,email,password);
                    ManagerDAO.getInstance().addClient(client);
                    JOptionPane.showMessageDialog(AddClientFrame.this,"Client added!");
                }

            }
        });

        setVisible(true);
    }


    public static boolean validateEmail(String email){
        Pattern p = Pattern.compile(".+[@].+\\.(com|ua|ru|net)");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private void createUIComponents() {

    }
}
