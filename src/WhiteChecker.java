import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class WhiteChecker extends Checker {
    private String sign = "⛀";

    public WhiteChecker(Cell cell, Game game) {
        super(cell);
        drawChecker(game);
    }

    @Override
    public int setOptionsToGo() {
        int countOfAvailableEnemies = 0;
        createOptionsToGo();
        if (!(Conditions.enemyAtUpLeft(this) == null)) {
            BlackChecker enemyAtUpLeft = (BlackChecker) Conditions.enemyAtUpLeft(this);
            enemyAtUpLeft.scanCheckersAround();
            if (Conditions.allyAtUpLeft(enemyAtUpLeft) == null && Conditions.enemyAtUpLeft(enemyAtUpLeft) == null) {
                getOptionsToGo().add(
                        enemyAtUpLeft.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtUpLeft.refreshChecker();
        }
        if (!(Conditions.enemyAtUpRight(this) == null)) {
            BlackChecker enemyAtUpRight = (BlackChecker) Conditions.enemyAtUpRight(this);
            enemyAtUpRight.scanCheckersAround();
            if (Conditions.allyAtUpRight(enemyAtUpRight) == null && Conditions.enemyAtUpRight(enemyAtUpRight) == null) {
                getOptionsToGo().add(
                        enemyAtUpRight.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtUpRight.refreshChecker();
        }
        if (!(Conditions.enemyAtDownLeft(this) == null)) {
            BlackChecker enemyAtDownLeft = (BlackChecker) Conditions.enemyAtDownLeft(this);
            enemyAtDownLeft.scanCheckersAround();
            if (Conditions.allyAtDownLeft(enemyAtDownLeft) == null && Conditions.enemyAtDownLeft(enemyAtDownLeft) == null) {
                getOptionsToGo().add(
                        enemyAtDownLeft.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtDownLeft.refreshChecker();
        }
        if (!(Conditions.enemyAtDownRight(this) == null)) {
            BlackChecker enemyAtDownRight = (BlackChecker) Conditions.enemyAtDownRight(this);
            enemyAtDownRight.scanCheckersAround();
            if (Conditions.allyAtDownRight(enemyAtDownRight) == null && Conditions.enemyAtDownRight(enemyAtDownRight) == null) {
                getOptionsToGo().add(
                        enemyAtDownRight.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtDownRight.refreshChecker();
        }
        if (countOfAvailableEnemies == 0) {
            if (getCell().getType() == 1) {
                if (Conditions.allyAtUpRight(this) == null && Conditions.enemyAtUpRight(this) == null)
                    getOptionsToGo().add(Cell.getCellByNumber(getCell().addUpRight()));
            }
            if (getCell().getType() == 2) {
                if (getCell().getNumber() % 2 == 0) {
                    if (Conditions.allyAtUpRight(this) == null && Conditions.enemyAtUpRight(this) == null)
                        getOptionsToGo().add(Cell.getCellByNumber(getCell().addUpRight()));
                } else {
                    if (Conditions.allyAtUpLeft(this) == null && Conditions.enemyAtUpLeft(this) == null)
                        getOptionsToGo().add(Cell.getCellByNumber(getCell().addUpLeft()));
                }
            } else {
                if (Conditions.allyAtUpLeft(this) == null && Conditions.enemyAtUpLeft(this) == null)
                    getOptionsToGo().add(Cell.getCellByNumber(getCell().addUpLeft()));
                if (Conditions.allyAtUpRight(this) == null && Conditions.enemyAtUpRight(this) == null)
                    getOptionsToGo().add(Cell.getCellByNumber(getCell().addUpRight()));
            }
        }
        return countOfAvailableEnemies;
    }

    @Override
    public void drawChecker(Game game) {
        game.setCellValue(getCell().getX(), getCell().getY(), sign);
    }
}

