package presentation_layer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends PageCommand {
    public LogoutCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return pageToShow;
    }
}
