package Model;

import Model.Impl.ImplClientDAO;
import Model.Impl.ImplRoomDAO;
import Model.Intefaces.IClientDAO;
import Model.Intefaces.IRoomDAO;
import org.apache.log4j.Logger;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ManagerDAO implements IClientDAO, IRoomDAO {
    private static Logger log = Logger.getLogger(ManagerDAO.class.toString());
    private static ManagerDAO instance = null;

    private ManagerDAO() {
        log.info("ManagerDAO Constructor");
    }

    public static ManagerDAO getInstance() {
        if (instance == null) instance = new ManagerDAO();
        return instance;
    }


    private IClientDAO clientDAO = new ImplClientDAO();
    private IRoomDAO roomDAO = new ImplRoomDAO();

}
