package Bot.Commands;

import Bot.UserRepository;
import CLI.Message;

import java.io.IOException;

interface ICommand {
    public String execute(Message update, UserRepository manager) throws IOException;
    public Integer getState();
}
