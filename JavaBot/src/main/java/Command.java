interface ICommand {
    public String execute(String question);
}

public abstract class Command implements ICommand {
    public String name;

    //TO DO: Добавить парсер
    @Override
    public String execute(String question) {
        return null;
    }
}

