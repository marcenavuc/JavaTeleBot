package Bot.Commands;

import Bot.UserRepository;
import CLI.Message;

import java.io.IOException;

public abstract class Command implements ICommand {
    public String name;

    @Override
    public String execute(Message message, UserRepository userRepository) throws IOException {
        return null;
    }

    @Override
    public Integer getState() {
        return 0;
    }
}

