package cs4218;

public class Main {
    public static void main(String[] args) {
        new Shell(new ApplicationRunner(), new CommandBuilder()).start();
    }
}