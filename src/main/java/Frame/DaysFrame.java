package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Den on 17.04.2015.
 */
public class DaysFrame extends JFrame{
    private JPanel rootPane;
    private JLabel daysLbl;
    private JSpinner spinnerDays;
    private JButton btnChoose;

    public DaysFrame(){
        super("How many days?");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(rootPane);
        SpinnerModel sm = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        spinnerDays.setModel(sm);
        setSize(new Dimension(350, 200));
        setVisible(true);
    }

    public void addListener(ActionListener l){
        btnChoose.addActionListener(l);
    }

    public JSpinner getSpinnerDays() {
        return spinnerDays;
    }

    public JButton getBtnChoose() {
        return btnChoose;
    }
}
