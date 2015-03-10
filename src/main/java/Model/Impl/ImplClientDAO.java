package Model.Impl;

import Model.Intefaces.IClientDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplClientDAO implements IClientDAO {
    Logger log = Logger.getLogger(ImplClientDAO.class.toString());
    private Connection connection;
    public ImplClientDAO(Connection connection) {
        log.info("ImplClientDAO constructor");
        this.connection = connection;
    }
}