package function_layer;

import db_access.DataAccessException;
import db_access.UserMapper;
import exceptions.DAOException;

public class LogicFacade {

    public static User login( String email, String password ) throws DAOException, DataAccessException {
        return UserMapper.login( email, password );
    }

    public static User createUser( String email, String password ) throws DAOException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

}
