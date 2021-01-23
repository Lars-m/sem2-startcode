package presentation_layer;

import db_access.DataAccessException;
import exceptions.DAOException;
import function_layer.LogicFacade;
import function_layer.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author kasper
 */
public class LoginCommand extends Command {
    public LoginCommand() {}

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            System.out.println(String.format("Email: %1$s, Password: %2$s",email,password));
            User user = LogicFacade.login(email, password);
            System.out.println("Loged In");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);  // ellers skal man skrive  user.email på jsp siderne og det er sgu lidt mærkeligt at man har adgang til private felter. Men måske er det meget fedt , jeg ved det ikke
            String pageToGoTo = request.getContextPath() + "/index.jsp";

            return REDIRECT_INDICATOR + pageToGoTo;
        } catch (DAOException e) {
            request.setAttribute("loginerror", "Wrong username or password!");
            return "loginpage";

        } catch (DataAccessException e) {
            request.setAttribute("problem", "Error while communicating with the database (developers should check logfiles)");
            return "errorpage";
        }

    }
}
