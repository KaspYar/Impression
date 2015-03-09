package Model.Impl;

import Hotel.Room;
import Model.Intefaces.IRoomDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplRoomDAO implements IRoomDAO {
    Logger log = Logger.getLogger(ImplRoomDAO.class.toString());
    public ImplRoomDAO() {
        log.info("ImplRoomDAO constructor");
    }
    public void setRoomFree(Room room, Connection connection) throws SQLException {
        log.info("setRoomFree: "+ room.getRoomNum());
        String query = "UPDATE room SET available = ? WHERE roomNum = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,0);
        preparedStatement.setInt(2, room.getRoomNum());

        int count = preparedStatement.executeUpdate();
        if (count !=1) {
            log.error("No room with id: "+ room.getRoomNum());
        }
        return;
    }
}
