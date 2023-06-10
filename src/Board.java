import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;

public class Board {

    private static ArrayList<WhiteChecker> whiteCheckers = new ArrayList();
    private static ArrayList<BlackChecker> blackCheckers = new ArrayList();
    private static Cell[] cells = new Cell[32];

    public static Cell[] getCells() {
        return cells;
    }

    public static ArrayList<WhiteChecker> getWhiteCheckers() {
        return whiteCheckers;
    }

    public static ArrayList<BlackChecker> getBlackCheckers() {
        return blackCheckers;
    }

    public static void createBoard(Game game) {
        for (int y = 0, i = 0; y < CheckersGame.HEIGHT; y++) {
            for (int x = 0; x < CheckersGame.WIDTH; x++) {
                if ((y % 2 == 0 && x % 2 != 0) || (y % 2 != 0 && x % 2 == 0)) {
                    cells[i] = new Cell(x, y, i++, game);
                } else {
                    game.setCellColor(x, y, CheckersGame.SECOND_COLOR);
                }
            }
        }
    }

    public static void createCheckers(Game game) {
        for (int i = 0; i < 12; i++) {
            cells[i].setTeam(CheckersGame.BLACK_TEAM);
            blackCheckers.add(new BlackChecker(cells[i], game));
        }
        for (int i = 20; i < 32; i++) {
            cells[i].setTeam(CheckersGame.WHITE_TEAM);
            whiteCheckers.add(new WhiteChecker(cells[i], game));
        }
    }

    public static void removeCheckerFromCell(Cell cell, Game game) {
        if (cell.getTeam() == 1) {
            for (WhiteChecker whiteChecker : whiteCheckers) {
                if (cell == whiteChecker.getCell()) {
                    whiteChecker.refreshChecker();
                    whiteChecker.eraseChecker(game);
                    whiteChecker.getCell().setTeam(0);
                    whiteCheckers.remove(whiteChecker);
                    break;
                }
            }
        } else if (cell.getTeam() == 2) {
            for (BlackChecker blackChecker : blackCheckers) {
                if (cell == blackChecker.getCell()) {
                    blackChecker.refreshChecker();
                    blackChecker.eraseChecker(game);
                    blackChecker.getCell().setTeam(0);
                    blackCheckers.remove(blackChecker);
                    break;
                }
            }
        }
    }
}


class Cell {
    private int x;
    private int y;
    private Color color;
    private int type; // 0 - Default Cell, 1 - Corner Cell, 2 - Vertical Cell, 3 - Horizontal Cell
    private int number;
    private int team; // 1 - White 2 - Black
    private int[] cellsAround;

    public Cell(int x, int y, int number, Game game) {
        this.x = x;
        this.y = y;
        this.number = number;
        rePaintToMainColor(game);
        setCellsAround();
    }

    public Cell() { // gag
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }


    public int getNumber() {
        return number;
    }

    public int[] getCellsAround() {
        return cellsAround;
    }

    public int getTeam() {
        return team;
    }

    public static Cell getCellByNumber(int number) {
        int i = 0;
        for (Cell cell : Board.getCells())
            if (cell.getNumber() == number)
                return cell;
        return null;
    }

    public static Cell[] getCellsByNumbers(int... numbers) {
        Cell[] cells = new Cell[numbers.length];
        int i = 0;
        for (int number : numbers)
            for (Cell cell : Board.getCells())
                if (cell.getNumber() == number)
                    cells[i++] = cell;
        return cells;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    private void setCellsAround() {
        if (number == 3) {
            cellsAround = new int[]{7};
            type = 1;
        } else if (number == 28) {
            cellsAround = new int[]{24};
            type = 1;
        } else if (number == 4 || number == 12 || number == 20 || number == 11 || number == 19 || number == 27) {
            cellsAround = new int[]{number - 4, number + 4};
            type = 2;
        } else if (number < 3) {
            cellsAround = new int[]{number + 4, number + 5};
            type = 3;
        } else if (number > 28) {
            cellsAround = new int[]{number - 5, number - 4};
            type = 3;
        } else if (y % 2 == 0) {
            cellsAround = new int[]{number - 4, number - 3, number + 4, number + 5};
        } else {
            cellsAround = new int[]{number - 5, number - 4, number + 3, number + 4};
        }
    }

    public int addUpLeft() {
        int diff = y % 2 == 0 ? 4 : 5;
        return number - diff;
    }

    public int addUpRight() {
        int diff = y % 2 == 0 ? 3 : 4;
        return number - diff;
    }

    public int addDownLeft() {
        int diff = y % 2 == 0 ? 4 : 3;
        return number + diff;
    }

    public int addDownRight() {
        int diff = y % 2 == 0 ? 5 : 4;
        return number + diff;
    }

    public Direction getDirection(Cell cell) {
        boolean isEven = y % 2 == 0;
        if (cell.getNumber() == number - (isEven ? 4 : 5)) return Direction.UP_LEFT;
        if (cell.getNumber() == number - (isEven ? 3 : 4)) return Direction.UP_RIGHT;
        if (cell.getNumber() == number + (isEven ? 4 : 3)) return Direction.DOWN_LEFT;
        else return Direction.DOWN_RIGHT;
    }

    public void rePaintToChoiceColor(Game game) {
        color = CheckersGame.CHOICE_COLOR;
        game.setCellColor(x,y, color);
    }

    public void rePaintToMainColor(Game game) {
        color = CheckersGame.MAIN_COLOR;
        game.setCellColor(x,y,color);
    }

    public void rePaintToOptionsToGoColor(Game game) {
        color = CheckersGame.OPTIONS_TO_GO_COLOR;
        game.setCellColor(x,y,color);
    }

    public void rePaintToSelectColor(Game game) {
        color = CheckersGame.SELECT_COLOR;
        game.setCellColor(x,y,color);
    }
}

