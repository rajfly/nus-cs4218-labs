package cs4218;

public class Main {
    public static void main(String[] args) {
        User user = new User("John");
        UserService userService = new UserService(user);
        UserController userCtrl = new UserController(userService);

        System.out.println(userCtrl.getUserName());
        System.out.println(userCtrl.getUserId());
    }
}
