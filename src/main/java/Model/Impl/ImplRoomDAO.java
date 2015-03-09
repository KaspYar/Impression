package Model.Impl;

import Model.Intefaces.IRoomDAO;
import org.apache.log4j.Logger;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplRoomDAO implements IRoomDAO {
    Logger log = Logger.getLogger(ImplRoomDAO.class.toString());
    public ImplRoomDAO() {
        log.info("ImplRoomDAO constructor");
    }
}
