package Controller;

import Frame.*;
import Hotel.Client;
import Hotel.Room;
import Model.ManagerDAO;
import config.ConfigurationHotel;

import javax.swing.*;
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
    private SettleClientFrame settleClientFrame;
    private AddClientFrame addClientFrame;
    private ChoosePaymentFrame choosePaymentFrame;
    private FreeRoomForToday freeRoomForToday;
    private FreeRoomsFrame freeRoomsFrame;
    private DaysFrame daysFrame;

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
            if (source == frame.getBtnSettleClient()) {
                settleClientFrame = new SettleClientFrame();
                settleClientFrame.addListener(new assignClientToRoom());
            }
            if (source == frame.getBtnPayments()){
                log.info("get payments");
                try {
                    choosePaymentFrame = new  ChoosePaymentFrame((ArrayList<Client>)ManagerDAO.getInstance().getAllClients());
                } catch (SQLException e1) {
                    log.warning("Can't get all clients");
                }
                choosePaymentFrame.addListener(new choosePaymentFrameListener());
            }
            if (source == frame.getBtnGetFree()){
                log.info("get free");
                try {
                    freeRoomForToday = new FreeRoomForToday((ArrayList<Room>)ManagerDAO.getInstance().getRooms(ConfigurationHotel.available));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                freeRoomForToday.addListener( new freeRoomForTodayListener());
                log.info("end");
            }


        }

    }
    private class freeRoomForTodayListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == freeRoomForToday.getComboBox1()){
                Room selectedRoom = (Room) freeRoomForToday.getLst().get(freeRoomForToday.getComboBox1().getSelectedIndex());
                        freeRoomForToday.getTextArea1().setText(
                        "Room #" + selectedRoom.getRoomNum()+
                                "\nLevel: "+selectedRoom.getLevel()+
                                "\nRoom type: "+selectedRoom.getRoomType()+
                                "\nPrice: "+selectedRoom.getPrice()+"$"
                );

            }
        }
    }

    private class assignClientToRoom implements ActionListener{

        private int days;

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if(src == settleClientFrame.getBtnAssignRoom()){
                daysFrame = new DaysFrame();
                daysFrame.getBtnChoose().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        freeRoomsFrame = new FreeRoomsFrame();
                        days = (Integer)daysFrame.getSpinnerDays().getValue();
                        daysFrame.dispose();
                        freeRoomsFrame.getBtnApply().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Client client = ManagerDAO.getInstance().getClientByID(settleClientFrame.getSelectedID());
                                Room room = ManagerDAO.getInstance().getRoomByNum(freeRoomsFrame.getSelectedID());
                                ManagerDAO.getInstance().addClientToRoom(client, room, days);
                                JOptionPane.showMessageDialog(freeRoomsFrame, client.getFirstname() + " " + client.getLastname()
                                        + " settled!", "Client settled!", JOptionPane.INFORMATION_MESSAGE);
                                freeRoomsFrame.dispose();
                                settleClientFrame.dispose();
                            }
                        });
                    }
                });

            }
        }
    }

    private class choosePaymentFrameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == choosePaymentFrame.getBtnChooseClientsPayment()){
                log.info("choose client");
                log.info("Row# "+choosePaymentFrame.getTableClientPayments().getSelectedRow());
                String res  = "";
                for(int i=0;i<choosePaymentFrame.getModel().getColumnCount();i++) {
                    res+=choosePaymentFrame.getModel().getValueAt(choosePaymentFrame.getTableClientPayments().getSelectedRow(), i)+" ";
                }
                log.info(res);

            }
            if (source == choosePaymentFrame.getComboBoxSelectClient()){
                log.info("Client choosed");
                int idSelectedClient = choosePaymentFrame.getComboBoxSelectClient().getSelectedIndex();
                Client clientSelected =  choosePaymentFrame.getLst().get(idSelectedClient);
                try {
                    choosePaymentFrame.initModelSource(ManagerDAO.getInstance().getPaymentResultSet(clientSelected));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
