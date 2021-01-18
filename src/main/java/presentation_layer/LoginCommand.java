package presentation_layer;

import function_layer.LogicFacade;
import function_layer.DAOException;
import function_layer.User;

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
    String execute(HttpServletRequest request, HttpServletResponse response)  {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            User user = LogicFacade.login(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);  // ellers skal man skrive  user.email på jsp siderne og det er sgu lidt mærkeligt at man har adgang til private felter. Men måske er det meget fedt , jeg ved det ikke
            String pageToGoTo = request.getContextPath()+"/index.jsp";
            return "#*redirect*#_###_"+pageToGoTo;
        } catch (DAOException e) {
            request.setAttribute("loginerror", "Wrong username or password!");
            return "loginpage";
        }
    }

}
