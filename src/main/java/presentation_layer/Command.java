package presentation_layer;

import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//abstract class Command {
public abstract class Command {

    //Return a token string from the execute method to make a client side redirect, instead of a server side (forward) redirect
    public final static String REDIRECT_INDICATOR = "#*redirect*#_###_";

    public final static String WAS_NOT_FOUND_COMMAND ="404_NOT_FOUND";

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

    public static Command fromPath(HttpServletRequest request) {

        String targetName = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + targetName);
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(targetName, new UnknownCommand(targetName));   // unknowncommand er default.
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;


}
