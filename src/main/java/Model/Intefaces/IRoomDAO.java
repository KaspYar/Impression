package Model.Intefaces;

import Hotel.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public interface IRoomDAO {
    public void setRoomFree(Room room) throws SQLException;
    public List<Room> getFreeRooms() throws  SQLException;
}
