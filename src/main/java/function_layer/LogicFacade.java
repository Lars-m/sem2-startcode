package function_layer;

import db_access.UserMapper;

public class LogicFacade {

    public static User login( String email, String password ) throws DAOException {
        return UserMapper.login( email, password );
    }

    public static User createUser( String email, String password ) throws DAOException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

}
