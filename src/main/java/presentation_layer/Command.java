package presentation_layer;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//abstract class Command {
public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("loginpage", new PageCommand("loginpage"));
        commands.put("registerpage", new PageCommand("registerpage"));
        commands.put("employeepage", new ProtectedPageCommand("employeepage", "employee"));
        commands.put("customerpage", new ProtectedPageCommand("customerpage", "customer"));
    }

    static Command from(HttpServletRequest request) {
        String targetName = request.getParameter("target");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand(targetName));   // unknowncommand er default.
    }

    public static Command fromPath(HttpServletRequest request) {

        String targetName = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + targetName);
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand(targetName));   // unknowncommand er default.
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response);


}
