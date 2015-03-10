package Model.Intefaces;

import Hotel.Room;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public interface IRoomDAO {
    public void setRoomFree(Room room) throws SQLException;
}
