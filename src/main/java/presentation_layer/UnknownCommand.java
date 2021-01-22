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
        String msg = String.format("Unknown Command!   '%s'",unknownCommand);
        request.setAttribute("problem",msg);
        return Command.WAS_NOT_FOUND_COMMAND;
    }

}
