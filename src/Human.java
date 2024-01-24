public class Human {

    private final char DOT;

    public Human(char ch) {
        DOT = ch;
    }

    public void turn(int x, int y, Field field, AI ai) {
        if (field.isCellValid(x, y)) {
            if (!field.isGameOver()) field.setDot(x, y, DOT);
            if (!field.isGameOver()) ai.turn(field);
        }
    }
}
