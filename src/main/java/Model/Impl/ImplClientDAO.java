package Model.Impl;

import Model.Intefaces.IClientDAO;

import java.util.logging.Logger;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplClientDAO implements IClientDAO {
    Logger log = Logger.getLogger(ImplClientDAO.class.toString());
    public ImplClientDAO() {
        log.info("ImplClientDAO constructor");
    }
}
