package Bot.Commands;

import Bot.UserManager;
import CLI.CLIUpdate;

interface ICommand {
    public String execute(CLIUpdate update, UserManager manager);
}
