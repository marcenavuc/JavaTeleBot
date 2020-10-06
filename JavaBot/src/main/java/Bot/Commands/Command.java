package Bot.Commands;

import Bot.Repository;
import CLI.Message;

import java.io.IOException;

public abstract class Command implements ICommand {
    public String name;

    @Override
    public String execute(Message message, Repository manager) throws IOException {
        return null;
    }
}

