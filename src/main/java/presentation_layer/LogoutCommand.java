package presentation_layer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        //String index = request.getContextPath()+"/index.jsp";
        String index = request.getContextPath()+"/";
        return "#*redirect*#_###_"+index;
    }
}
