import java.util.HashMap;

public class Bot {
    private final HashMap<String, Command> miniDb = new HashMap<String, Command>();
    private final String ifNotFound = "Я ничего не понял(((";

    public Bot() {
        miniDb.put("/start", new Start());
        miniDb.put("/help", new Help());
        miniDb.put("/weather", new Weather());
        miniDb.put("/subscribe", new Subscribe());
        miniDb.put("/unsubscribe", new Unsubscribe());
    }

    public String takeAnswer(String question) {
//        String answer = miniDb.get(question).execute(question);
        Command handler = miniDb.get(question);
        return handler != null ? handler.execute(question) : ifNotFound;
//        return answer == null ? ifNotFound : answer;
    }
}
