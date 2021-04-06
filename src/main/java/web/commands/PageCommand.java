package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageCommand extends Command {
    protected String pageToShow;
    public PageCommand(String pageToShow) {
        this.pageToShow = pageToShow;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return pageToShow;
    }
}


