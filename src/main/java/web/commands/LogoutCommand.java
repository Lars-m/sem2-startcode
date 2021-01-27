package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        String index = request.getContextPath()+"/";
        return REDIRECT_INDICATOR+index;
    }
}
