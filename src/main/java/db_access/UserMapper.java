package db_access;

import exceptions.DAOException;
import function_layer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserMapper {

    //These values are defined here, since they influence SECURITY
    //Change this to a reasonable value for a REAL-LIFE project
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final int MIN_EMAIL_LENGTH = 6;

    public static void createUser( User user ) throws DAOException {

        try {
            validateUserInputs(user.getPassword(), user.getEmail());
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );
            ps.setString( 3, user.getRole() );
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt( 1 );
            user.setId( id );
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new DAOException( ex.getMessage() );
        }
    }

    public static User login( String email, String password ) throws DAOException, DataAccessException {
        validateUserInputs(password,email);
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT id, role FROM Users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, email );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                String role = rs.getString( "role" );
                int id = rs.getInt( "id" );
                User user = new User( email, password, role );
                user.setId( id );
                return user;
            } else {
                throw new DAOException( "Could not validate user" );
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            ex.printStackTrace();
            throw new DataAccessException(ex.getMessage());
        }
    }

    private static void validateUserInputs(String password, String email) throws DAOException {
        if((password==null ||password.length()< MIN_PASSWORD_LENGTH) ||
           (email==null || email.length()< MIN_EMAIL_LENGTH))
        {
            throw new DAOException("Either password or user name does not obey rules for min length");
        }
    }

}
