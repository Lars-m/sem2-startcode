package presentation_layer;

import function_layer.LogicFacade;
import function_layer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response )  {
        String email = request.getParameter( "email" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        try {
            if (password1.equals(password2)) {
                User user = LogicFacade.createUser(email, password1);
                /**
                 HttpSession session = request.getSession();
                 session.setAttribute("email",email);
                 session.setAttribute( "user", user );
                 session.setAttribute( "role", user.getRole() );
                 return user.getRole() + "page"; */
                //Normal procedure seems to be that after creating ourself, we need to login as a check
                String pageToGoTo = request.getContextPath()+"/index.jsp";
                //TODO Define this as a constant -->    #*redirect*#_###_
                return "#*redirect*#_###_"+"loginpage?msg=You must login to use your new account";
            } else {
                //TODO TJEK THIS
                throw new Exception("the two passwords did not match");
            }
        } catch (Exception e){
            request.setAttribute("userinputerror", e.getMessage());
            return "registerpage";
        }

    }

}
