import java.util.Random;

public class AI {

    private final char DOT;
    private Random random;

    public AI(char ch) {
        DOT = ch;
        random = new Random();
    }

    public void turn(Field field) {
        int x, y;
        do {
            x = random.nextInt((field.getSize()));
            y = random.nextInt((field.getSize()));
        } while (!field.isCellValid(x, y));
        field.setDot(x, y, DOT);
    }
}
