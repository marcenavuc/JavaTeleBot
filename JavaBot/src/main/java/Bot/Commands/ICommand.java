package Bot.Commands;

import Bot.Repository;
import CLI.Message;

import java.io.IOException;

interface ICommand {
    public String execute(Message update, Repository manager) throws IOException;
    public Integer getState();
}
