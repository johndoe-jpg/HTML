package pkg4ita_machado_ukolnicek;

/**
 *
 * @author THUND
 */
public class Note {
    String timestamp;
    String title;
    String body;
    int id;

    public Note(String timestamp, String title, String body, int id) {
        this.timestamp = timestamp;
        this.title = title;
        this.body = body;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "[#" + id + "]" + timestamp + "; " + title + ": " + body;
    }
}
