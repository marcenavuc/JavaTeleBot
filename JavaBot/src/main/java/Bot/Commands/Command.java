package Bot.Commands;

import CLI.CLIUpdate;

public abstract class Command implements ICommand {
    public String name;

    //TO DO: Добавить парсер
    @Override
    public String execute(CLIUpdate update) {
        return null;
    }
}

