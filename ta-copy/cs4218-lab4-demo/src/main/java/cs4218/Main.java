package cs4218;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "john");
        UserService usv = new UserService(user);
        UserController userCtrl = new UserController(usv);

        System.out.println(userCtrl.getUserName(1));
    }
}
