package presentation_layer;

import exceptions.ValidationException;
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
             validateInputs(email,password1,password2);
             User user = LogicFacade.createUser(email, password1);
             //Normal procedure seems to be,that after creating ourself, We  STILL need to login as a check
             String pageToGoTo = request.getContextPath()+"/index.jsp";
                //TODO Define this as a constant -->    #*redirect*#_###_
                return REDIRECT_INDICATOR + "loginpage?msg=You must login to use your new account";
        } catch (Exception e){
            request.setAttribute("userinputerror", e.getMessage());
            return "registerpage";
        }

    }
    /* Values below are chosen to make dev-testing easier */
    void validateInputs(String email, String password1, String password2) throws ValidationException {
       if(email.length() < 6){
           throw new ValidationException("Email must include a minimum of 6 characters");
       }
       if(!password1.equals(password2)){
           throw new ValidationException("The two passwords did not match");
       }
       if(password1.length() < 4){
           throw new ValidationException("Passwords must include at least 4 characters ");
       }
    }

}
