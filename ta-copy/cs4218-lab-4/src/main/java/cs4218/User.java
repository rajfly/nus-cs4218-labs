package cs4218;
import java.util.UUID;

public class User {
    private final String id;
    private final String name;

    User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
