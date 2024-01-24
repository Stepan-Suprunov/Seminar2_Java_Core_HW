import java.awt.*;

public class Field {

    private final int FIELD_SIZE;
    private final int CELL_SIZE;
    private final char HUMAN_DOT = 'x';
    private final char AI_DOT = 'o';
    private final char EMPTY_DOT = '.';
    private final String MSG_DRAW = "Draw, sorry...";
    private final String MSG_HUMAN_WON = "YOU WON !";
    private final String MSG_AI_WON = "AI WON !";

    private char[][] map;
    private String gameOverMsg;

    public Field(int fieldSize, int cellSize) {
        FIELD_SIZE = fieldSize;
        CELL_SIZE = cellSize;

        map = new char[fieldSize][fieldSize];
        init();
    }

    public void init() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
        gameOverMsg = null;
    }

    int getSize() {
        return FIELD_SIZE;
    }

    char getHumanDot() {
        return HUMAN_DOT;
    }

    char getAiDot() {
        return AI_DOT;
    }

    boolean isGameOver() {
        return gameOverMsg != null;
    }

    public String getGameOverMsg() {
        return gameOverMsg;
    }

    void setDot(int x, int y, char dot) {  // set dot and check fill and win
        map[x][y] = dot;
        if (checkWin(HUMAN_DOT)) gameOverMsg = MSG_HUMAN_WON;
        else if (checkWin(AI_DOT)) gameOverMsg = MSG_AI_WON;
        else if (isMapFull()) gameOverMsg = MSG_DRAW;

    }

    boolean isMapFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (map[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    boolean checkWin(char dot) {
        // check verticals
        for (int i = 0; i < FIELD_SIZE; i++) {
            boolean flagHorizontal = true;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (map[i][j] != dot) flagHorizontal = false;
            }
            if (flagHorizontal == true) return true;
        }

        // check horizontals
        int index0Count = 0;
        int index1Count = 0;
        int index2Count = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (map[i][j] == dot) {
                    if (j == 0) index0Count++;
                    if (j == 1) index1Count++;
                    if (j == 2) index2Count++;
                }
            }
        }
        if (index0Count == 3 || index1Count == 3 || index2Count == 3) return true;

        // checking diagonals
        int countRightDiagonal = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (i == j && map[i][j] == dot) countRightDiagonal++;
            }
        }
        if (countRightDiagonal == 3) return true;

        int countLeftDiagonal = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (map[i][(FIELD_SIZE - 1) - i] == dot) countLeftDiagonal++;
        }
        if (countLeftDiagonal == 3) return true;

        return false;
    }

//    boolean checkWin(char dot) {
//        for (int i = 0; i < FIELD_SIZE; i++) {
//            // check horizontals / verticals
//            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
//                    (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
//                return true;
//
//            // checking diagonals
//            if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
//                    (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
//                return true;
//        }
//        return false;
//    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1)
            return false;
        if (map[x][y] == EMPTY_DOT)
            return true;
        return false;
    }

    public void paint(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.lightGray);
        for (int i = 1; i < FIELD_SIZE; i++) {
            g.drawLine(0, i * CELL_SIZE, FIELD_SIZE * CELL_SIZE, i * CELL_SIZE);
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, FIELD_SIZE * CELL_SIZE);
        }

        g.setStroke(new BasicStroke(5));

        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (map[x][y] == HUMAN_DOT) {
                    g.setColor(Color.blue);
                    g.drawLine(x * CELL_SIZE + CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4,
                            (x + 1) * CELL_SIZE - CELL_SIZE / 4, (y + 1) * CELL_SIZE - CELL_SIZE / 4);
                    g.drawLine(x * CELL_SIZE + CELL_SIZE / 4, (y + 1) * CELL_SIZE - CELL_SIZE / 4,
                            (x + 1) * CELL_SIZE - CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4);
                }
                if (map[x][y] == AI_DOT) {
                    g.setColor(Color.red);
                    g.drawOval(x * CELL_SIZE + CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4,
                            CELL_SIZE / 2,
                            CELL_SIZE / 2);
                }
            }
        }
    }
}
