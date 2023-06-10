import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class CheckersGame extends Game {
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;
    public static final int WHITE_TEAM = 1;
    public static final int BLACK_TEAM = 2;
    public static final Color SECOND_COLOR = Color.BEIGE;
    public static final Color MAIN_COLOR = Color.DARKSEAGREEN;
    public static final Color CHOICE_COLOR = Color.YELLOWGREEN;
    public static final Color SELECT_COLOR = Color.LIMEGREEN;
    public static final Color OPTIONS_TO_GO_COLOR = Color.GREENYELLOW;
    private EventHandler eventHandler = new EventHandler(this);

    private void createGame() {
        Board.createBoard(this);
        Board.createCheckers(this);
        nextMove();
    }

    public void nextMove() {
        eventHandler.changeTurn();
        eventHandler.setAvailableCells();
        checkGameStatus();
    }

    private void checkGameStatus() {
        if (Board.getWhiteCheckers().isEmpty()) showMessageDialog(Color.LIGHTGREEN, "Черные Выиграли!", Color.BLACK, 50);
        if (Board.getBlackCheckers().isEmpty()) showMessageDialog(Color.LIGHTGREEN, "Белые Выиграли!", Color.BLACK, 50);
    }

    @Override
    public void initialize() {
        setScreenSize(8, 8);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        eventHandler.handle(x, y);
    }
}
