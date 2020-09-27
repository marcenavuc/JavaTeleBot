package Bot.Commands;

import CLI.CLIUpdate;

interface ICommand {
    public String execute(CLIUpdate update);
}
