import java.util.HashMap;

public class Bot {
    private final HashMap<String, String> miniDb = new HashMap<String, String>();
    private final String ifNotFound = "Я ничего не понял(((";

    public Bot() {
        miniDb.put("/start", "Привет, меня зовут Глиномес!");
        miniDb.put("/help", "К сожалению, я ничего не умеб((((");
        miniDb.put("Дурак", "Сам дурка");
    }

    public String takeAnswer(String question) {
        String answer = miniDb.get(question);
        return answer == null ? ifNotFound : answer;
    }
}
