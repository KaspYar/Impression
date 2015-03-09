package Model.Impl;

import Model.Intefaces.IRoomDAO;

import java.util.logging.Logger;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplRoomDAO implements IRoomDAO {
    Logger log = Logger.getLogger(ImplRoomDAO.class.toString());
    public ImplRoomDAO() {
        log.info("ImplClientDAO constructor");
    }
}
