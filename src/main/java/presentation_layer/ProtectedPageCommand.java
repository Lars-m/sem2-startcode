package presentation_layer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProtectedPageCommand extends Command {
    private String role;
    private String pageToShow;
    public ProtectedPageCommand(String pageToShow, String role) {
        this.pageToShow = pageToShow;
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
