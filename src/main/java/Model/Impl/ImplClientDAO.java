package Model.Impl;

import Model.Intefaces.IClientDAO;
import org.apache.log4j.Logger;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplClientDAO implements IClientDAO {
    Logger log = Logger.getLogger(ImplClientDAO.class.toString());
    public ImplClientDAO() {
        log.info("ImplClientDAO constructor");
    }
}
