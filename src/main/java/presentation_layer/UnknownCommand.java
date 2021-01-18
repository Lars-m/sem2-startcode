package presentation_layer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends Command {
    String unknownCommand;

    public UnknownCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response )  {
        String msg = String.format("Unknown command! '%s'",unknownCommand);
        request.setAttribute("problem",msg);
        return "404page";
    }

}
