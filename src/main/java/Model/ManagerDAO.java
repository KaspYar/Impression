package Model;

import Model.Impl.ImplClientDAO;
import Model.Impl.ImplRoomDAO;
import Model.Intefaces.IClientDAO;
import Model.Intefaces.IRoomDAO;
import config.ConfigurationHotel;
import org.apache.log4j.Logger;

import java.sql.*;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ManagerDAO implements IClientDAO, IRoomDAO {
    private static Logger log = Logger.getLogger(ManagerDAO.class.toString());
    private static ManagerDAO instance = null;
    private Connection connection;
    private IClientDAO clientDAO = new ImplClientDAO();
    private IRoomDAO roomDAO = new ImplRoomDAO();

    private ManagerDAO() {
        log.info("ManagerDAO Constructor");
        try {
            Class.forName(ConfigurationHotel.driver);
            connection = DriverManager.getConnection(ConfigurationHotel.server+ConfigurationHotel.base_name,ConfigurationHotel.base_user, ConfigurationHotel.base_pass);
        } catch (ClassNotFoundException e) {
            log.error("No such class: "+e.toString());
        } catch (SQLException e) {
            log.error("Can't connect to database: " + e.toString());
        }
        ResultSet rs = null;
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * from room");
            rs = ps.executeQuery();

        } catch (SQLException e) {
            log.error("Bad query: " + e.toString());
        }
        log.info("Connected");
        try {
            while(rs.next()){
                log.info(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("No data in resultSet: " + e.toString());
        }

    }

    public static ManagerDAO getInstance() {
        if (instance == null) instance = new ManagerDAO();
        return instance;
    }


}
