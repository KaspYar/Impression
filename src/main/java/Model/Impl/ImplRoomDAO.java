package Model.Impl;

import Hotel.Room;
import Model.Intefaces.IRoomDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplRoomDAO implements IRoomDAO {
    Logger log = Logger.getLogger(ImplRoomDAO.class.toString());
    private Connection connection;
    public ImplRoomDAO(Connection connection) {
        log.info("ImplRoomDAO constructor");
        this.connection = connection;
    }
    public void setRoomFree(Room room) throws SQLException {
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

    @Override
    public List<Room> getFreeRooms() throws SQLException {
        log.info("getFreeRooms");
        ArrayList<Room> result = new ArrayList<Room>();
        String query = "SELECT * FROM ROOM WHERE available = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,1);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            //Room(int roomNum, String roomType, int level, int placeNum, double price, boolean avaliable)
            result.add(new Room(resultSet.getInt("roomNum"),
                    resultSet.getString("roomType"),
                    resultSet.getInt("level"),
                    resultSet.getInt("place_num"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("available") == 1));
        }
        return result;
    }
}
