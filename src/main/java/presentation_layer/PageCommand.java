package presentation_layer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageCommand extends Command {
    private String pageToShow;
    public PageCommand(String pageToShow) {
        this.pageToShow = pageToShow;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response )  {
        return pageToShow;
    }
}


