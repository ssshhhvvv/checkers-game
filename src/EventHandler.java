import java.util.ArrayList;

public class EventHandler {
    private static CheckersGame game;
    private static final Cell[] cells = Board.getCells();
    private static final ArrayList<WhiteChecker> whiteCheckers = Board.getWhiteCheckers();
    private static final ArrayList<BlackChecker> blackCheckers = Board.getBlackCheckers();
    private static ArrayList<Cell> availableCells;
    private static ArrayList<Cell> availableCellsIfEnemies;
    private static int whoseMove = CheckersGame.BLACK_TEAM;
    private static Checker selectedChecker;
    private static int isEnemy;

    public EventHandler(CheckersGame game) {
        EventHandler.game = game;
    }

    public void changeTurn() {
        refreshCheckers();
        whoseMove = whoseMove == CheckersGame.WHITE_TEAM ? 2 : 1;
        removeSelectedChecker();
    }

    public void setAvailableCells() {
        availableCells = new ArrayList<Cell>();
        availableCellsIfEnemies = new ArrayList<Cell>();
        if (whoseMove == CheckersGame.WHITE_TEAM) {
            for (WhiteChecker whiteChecker : whiteCheckers) {
                addCellToAvailable(whiteChecker);
            }
        } else {
            for (BlackChecker blackChecker : blackCheckers) {
                addCellToAvailable(blackChecker);
            }
        }
        if (!availableCellsIfEnemies.isEmpty()) availableCells = availableCellsIfEnemies;
        repaintCellsToChoiceColor(availableCells);
    }

    private void addCellToAvailable(Checker checker) {
        checker.scanCheckersAround();
        int enemy = checker.setOptionsToGo();
        if (checker.getOptionsToGo().isEmpty()) return;
        if (availableCellsIfEnemies.isEmpty() && enemy == 0) {
            availableCells.add(checker.getCell());
            return;
        }
        if (enemy != 0) {
            availableCellsIfEnemies.add(checker.getCell());
        }
    }

    private void refreshCheckers() {
        if (whoseMove == CheckersGame.WHITE_TEAM) {
            for (WhiteChecker whiteChecker : whiteCheckers) {
                whiteChecker.refreshChecker();
            }
        } else {
            for (BlackChecker blackChecker : blackCheckers) {
                blackChecker.refreshChecker();
            }
        }
    }

    public void handle(int x, int y) {
        Cell cell = getCell(x, y);
        if (cell == null) return;
        if (selectedChecker == null || cell.getTeam() == whoseMove) {
            if (cell.getTeam() != whoseMove) return;
            changeSelectedChecker(cell);
        } else {
            for (Cell c : selectedChecker.getOptionsToGo()) {
                if (cell == c) {
                    if (isEnemy == 0) {
                        repaintCellsToMainColor(selectedChecker.getOptionsToGo());
                        selectedChecker.moveToCell(cell, game);
                    } else {
                        selectedChecker.eat(cell, game);
                    }
                    repaintCellsToMainColor(availableCells);
                    game.nextMove();
                    return;
                }
            }
        }
    }

    private void changeSelectedChecker(Cell cell) {
        Checker checker = getChecker(cell);
        for (Cell c : availableCells)
            if (checker.getCell() == c) {
                if (selectedChecker != null) {
                    selectedChecker.getCell().rePaintToChoiceColor(game);
                    repaintCellsToMainColor(selectedChecker.getOptionsToGo());
                }
                selectedChecker = checker;
                selectedChecker.getCell().rePaintToSelectColor(game);
                repaintCellsToOptionsToGoColor(selectedChecker.getOptionsToGo());
                isEnemy = checker.setOptionsToGo();
                return;
            }
    }

    private Checker getChecker(Cell cell) {
        if (cell.getTeam() == CheckersGame.WHITE_TEAM)
            for (WhiteChecker checker : whiteCheckers)
                if (cell == checker.getCell()) return checker;
        if (cell.getTeam() == CheckersGame.BLACK_TEAM)
            for (BlackChecker checker : blackCheckers)
                if (cell == checker.getCell()) return checker;
        return null;
    }

    public static void repaintCellsToChoiceColor(ArrayList<Cell> cells) {
        for (Cell c : cells)
            c.rePaintToChoiceColor(game);
    }

    public static void repaintCellsToMainColor(ArrayList<Cell> cells) {
        for (Cell c : cells)
            c.rePaintToMainColor(game);
    }

    public static void repaintCellsToOptionsToGoColor(ArrayList<Cell> cells) {
        for (Cell c : cells)
            c.rePaintToOptionsToGoColor(game);
    }

    private Cell getCell(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) return cell;
        }
        return null;
    }

    private void removeSelectedChecker() {
        selectedChecker = null;
        isEnemy = 0;
    }
}

