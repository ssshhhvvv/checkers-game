import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Checker {
    private Cell cell;
    private ArrayList<Checker>[] checkersAround; // White - 0 Black - 1
    private ArrayList<Cell> optionsToGo;

    public Checker(Cell cell) {
        this.cell = cell;
    }


    public Cell getCell() {
        return cell;
    }

    public ArrayList<Checker>[] getCheckersAround() {
        return checkersAround;
    }

    public void createOptionsToGo() {
        optionsToGo = new ArrayList<Cell>();
    }

    public ArrayList<Cell> getOptionsToGo() {
        return optionsToGo;
    }

    public abstract int setOptionsToGo();

    public void moveToCell(Cell cell, Game game) {
        eraseChecker(game);
        this.cell.setTeam(0);
        this.cell = cell;
        cell.setTeam(this.getClass() == WhiteChecker.class ? CheckersGame.WHITE_TEAM : CheckersGame.BLACK_TEAM);
        EventHandler.repaintCellsToMainColor(optionsToGo);
        refreshChecker();
        drawChecker(game);
    }

    public void eat(Cell cell, Game game) {
        switch (getCell().getDirection(cell)) {
            case UP_LEFT:
                moveToCell(Cell.getCellByNumber(cell.addUpLeft()), game);
                Board.removeCheckerFromCell(cell, game);
                break;
            case UP_RIGHT:
                moveToCell(Cell.getCellByNumber(cell.addUpRight()), game);
                Board.removeCheckerFromCell(cell, game);
                break;
            case DOWN_LEFT:
                moveToCell(Cell.getCellByNumber(cell.addDownLeft()), game);
                Board.removeCheckerFromCell(cell, game);
                break;
            case DOWN_RIGHT:
                moveToCell(Cell.getCellByNumber(cell.addDownRight()), game);
                Board.removeCheckerFromCell(cell, game);
                break;
        }
    }

    public void scanCheckersAround() {
        checkersAround = new ArrayList[]{new ArrayList<WhiteChecker>(), new ArrayList<BlackChecker>()};
        Cell[] cellsAround = Cell.getCellsByNumbers(cell.getCellsAround());
        int i = 0;
        for (Cell cell : cellsAround) {
            if (cell.getTeam() == CheckersGame.WHITE_TEAM) {
                for (WhiteChecker whiteChecker : Board.getWhiteCheckers())
                    if (cell == whiteChecker.getCell())
                        checkersAround[0].add(whiteChecker);
            } else if (cell.getTeam() == CheckersGame.BLACK_TEAM) {
                for (BlackChecker blackChecker : Board.getBlackCheckers())
                    if (cell == blackChecker.getCell())
                        checkersAround[1].add(blackChecker);
            }
        }
    }


    public abstract void drawChecker(Game game);

    public void eraseChecker(Game game) {
        game.setCellValue(cell.getX(), cell.getY(), "");
    }

    public void refreshChecker() {
        checkersAround = null;
        optionsToGo = null;
    }
}
