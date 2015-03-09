package Model;

import Model.Impl.ImplClientDAO;
import Model.Impl.ImplRoomDAO;
import Model.Intefaces.IClientDAO;
import Model.Intefaces.IRoomDAO;

import java.util.logging.Logger;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ManagerDAO implements IClientDAO, IRoomDAO {
    private static Logger log = Logger.getLogger(ManagerDAO.class.toString());
    static private IClientDAO clientDAO = new ImplClientDAO();
    static private IRoomDAO roomDAO = new ImplRoomDAO();
    public ManagerDAO(){
        log.info("ManagerDAO Constructor");
    }
}
