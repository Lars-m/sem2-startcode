package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import presentation_layer.Command;
import presentation_layer.ProtectedPageCommand;

//@WebFilter(urlPatterns = {"/*"}, servletNames = {"FrontController"})
@WebFilter(servletNames = {"FrontController"})
public class AuthorizationFilter implements Filter {

    private enum FailingStrategy {
        REDIRECT_TO_LOGIN,
        HARD_ERROR
    }

    private final FailingStrategy FAIL_STRATEGY = FailingStrategy.HARD_ERROR;

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();
        if (servletPath != null && servletPath.equals("/fc")) {
            Command command = Command.fromPath(req);
            HttpSession session = req.getSession(false);
            if (command instanceof ProtectedPageCommand) {
                String roleFromCommand = ((ProtectedPageCommand) command).getRole();
                if (session == null || session.getAttribute("user")==null) {
                    handleIllegalAccess(req, res, "You are not authenticated. Please login first",401);
                    return;
                }
                else {
                    String role = (String) session.getAttribute("role");
                    if (role == null || !role.equals(roleFromCommand)) {
                        handleIllegalAccess(req, res, "You tried to call a protected resource without being authorized. Please login first",403);
                        return;
                    }

                }
            }
        }

        //Prevents users, who has logged out, to use the back-button and see pages the could see, while loged in
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0); // Proxies.

        filterChain.doFilter(request, response);
    }

    private void handleIllegalAccess(HttpServletRequest req, HttpServletResponse res, String msg,int errCode) throws IOException, ServletException {
        if (FAIL_STRATEGY == FailingStrategy.REDIRECT_TO_LOGIN) {
//            String redirectPath = String.format("%s/fc/loginpage?loginerror=%s", req.getContextPath(), msg);
//            res.sendRedirect(redirectPath);
            req.setAttribute("loginerror",msg);
            req.getRequestDispatcher("/WEB-INF/" +"loginpage.jsp").forward(req, res);
        } else {
            res.sendError(errCode);
        }
    }


    public void destroy() {
    }
}