package Controller;

import Frame.AddClientFrame;
import Frame.SetFreeFrame;
import Frame.MainFrame;
import Hotel.Room;
import Model.ManagerDAO;
import config.ConfigurationHotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by KaspYar on 15.03.2015.
 */
public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.toString());

    private MainFrame frame;
    private SetFreeFrame setFreeFrame;
    private AddClientFrame addClientFrame;

    public Controller(MainFrame mc) {
        this.frame = mc;
        frame.setVisible(true);
        frame.addListener(new FrameListener());
    }
    private class FrameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == frame.getBtnSetFree()){
                log.info("setFree pressed");
                try {
                    setFreeFrame = new  SetFreeFrame((ArrayList<Room>)ManagerDAO.getInstance().getRooms(ConfigurationHotel.unavailable));
                } catch (SQLException e1) {
                    log.warning("Can't get rooms");
                }
                setFreeFrame.addListener(new SetFreeFrameListener());
            }
            if (source == frame.getBtnAddClient()) {
                log.info("addClient pressed");
                addClientFrame = new AddClientFrame();
            }
        }

    }
    private class SetFreeFrameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == setFreeFrame.getBtnSetFree()){
                try {
                    int idSelectedItem = setFreeFrame.getComboBoxRoom().getSelectedIndex();
                    Room roomSelected =  setFreeFrame.getLst().get(idSelectedItem);
                    ManagerDAO.getInstance().setRoomFree(roomSelected);
                } catch (SQLException e1) {
                    log.warning("Can't do room free");
                }
                setFreeFrame.dispose();
            }
            if(source == setFreeFrame.getComboBoxRoom()){
                int idSelectedItem = setFreeFrame.getComboBoxRoom().getSelectedIndex();
                Room roomSelected =  setFreeFrame.getLst().get(idSelectedItem);
                setFreeFrame.getBtnSetFree().setText("Set Room#"+roomSelected.getRoomNum()+" Free");
                setFreeFrame.getBtnSetFree().setVisible(true);
                StringBuilder message = new StringBuilder("---------------Room #"+ roomSelected.getRoomNum()+"---------------");
                message.append("\nType: "+roomSelected.getRoomType());
                message.append("\nLevel: "+roomSelected.getLevel());
                message.append("\nPrice: "+roomSelected.getPrice());
                setFreeFrame.getTextPaneInfo().setText(message.toString());
            }
        }
    }
}
