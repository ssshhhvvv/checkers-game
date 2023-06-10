import com.javarush.engine.cell.Game;

public class BlackChecker extends Checker {
    private String sign = "⛂";

    public BlackChecker(Cell cell, Game game) {
        super(cell);
        drawChecker(game);
    }

    @Override
    public int setOptionsToGo() {
        int countOfAvailableEnemies = 0;
        createOptionsToGo();
        if (!(Conditions.enemyAtUpLeft(this) == null)) {
            WhiteChecker enemyAtUpLeft = (WhiteChecker) Conditions.enemyAtUpLeft(this);
            enemyAtUpLeft.scanCheckersAround();
            if (Conditions.allyAtUpLeft(enemyAtUpLeft) == null && Conditions.enemyAtUpLeft(enemyAtUpLeft) == null) {
                getOptionsToGo().add(
                        enemyAtUpLeft.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtUpLeft.refreshChecker();
        }
        if (!(Conditions.enemyAtUpRight(this) == null)) {
            WhiteChecker enemyAtUpRight = (WhiteChecker) Conditions.enemyAtUpRight(this);
            enemyAtUpRight.scanCheckersAround();
            if (Conditions.allyAtUpRight(enemyAtUpRight) == null && Conditions.enemyAtUpRight(enemyAtUpRight) == null) {
                getOptionsToGo().add(
                        enemyAtUpRight.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtUpRight.refreshChecker();
        }
        if (!(Conditions.enemyAtDownLeft(this) == null)) {
            WhiteChecker enemyAtDownLeft = (WhiteChecker) Conditions.enemyAtDownLeft(this);
            enemyAtDownLeft.scanCheckersAround();
            if (Conditions.allyAtDownLeft(enemyAtDownLeft) == null && Conditions.enemyAtDownLeft(enemyAtDownLeft) == null) {
                getOptionsToGo().add(
                        enemyAtDownLeft.getCell());
                countOfAvailableEnemies++;
            }
            enemyAtDownLeft.refreshChecker();
        }
        if (!(Conditions.enemyAtDownRight(this) == null)) {
            WhiteChecker enemyAtDownRight = (WhiteChecker) Conditions.enemyAtDownRight(this);
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
                if (Conditions.allyAtDownLeft(this) == null && Conditions.enemyAtDownLeft(this) == null)
                    getOptionsToGo().add(Cell.getCellByNumber(getCell().addDownLeft()));
            }
            if (getCell().getType() == 2) {
                if (getCell().getNumber() % 2 == 0) {
                    if (Conditions.allyAtDownRight(this) == null && Conditions.enemyAtDownLeft(this) == null)
                        getOptionsToGo().add(Cell.getCellByNumber(getCell().addDownRight()));
                } else {
                    if (Conditions.allyAtDownLeft(this) == null && Conditions.enemyAtDownLeft(this) == null)
                        getOptionsToGo().add(Cell.getCellByNumber(getCell().addDownLeft()));
                }
            } else {
                if (Conditions.allyAtDownLeft(this) == null && Conditions.enemyAtDownLeft(this) == null)
                    getOptionsToGo().add(Cell.getCellByNumber(getCell().addDownLeft()));
                if (Conditions.allyAtDownRight(this) == null && Conditions.enemyAtDownRight(this) == null)
                    getOptionsToGo().add(Cell.getCellByNumber(getCell().addDownRight()));

            }
        }
        return countOfAvailableEnemies;
    }

    @Override
    public void drawChecker(Game game) {
        game.setCellValue(getCell().getX(), getCell().getY(), sign);
    }
}
