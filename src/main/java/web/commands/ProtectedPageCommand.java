package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProtectedPageCommand extends PageCommand {
    private String role;

    public ProtectedPageCommand(String pageToShow, String role) {
        super(pageToShow);
        this.role = role;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return pageToShow;
    }

    public String getRole() {
        return role;
    }
}
