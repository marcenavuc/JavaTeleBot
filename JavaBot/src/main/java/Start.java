public class Start extends Command {

    @Override
    public String execute(String question) {
        String answer = "Привет, меня зовут бот QQuestionBot\n";
        return answer + new Help().execute(question);
    }
}

