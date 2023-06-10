import com.javarush.engine.cell.Game;

public class Conditions {
    private static final Checker gag = new Checker(new Cell()) {
        @Override
        public int setOptionsToGo() {
            return 0;
        }

        @Override
        public void drawChecker(Game game) {

        }
    };

    public static Checker allyAtUpLeft(Checker checker) {
        if (checker.getCell().getType() == 2) {
            if (checker.getCell().getY() % 2 != 0) return gag;
        }
        if (checker.getCell().getType() == 3) {
            if (checker.getCell().getNumber() < 3) return gag;
        }
        if (checker.getCell().getNumber() == 28 || checker.getCell().getNumber() == 3) return gag;
        int checkerTeam = checker.getCell().getTeam() - 1;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addUpLeft()) return c;
        }
        return null;
    }

    public static Checker allyAtUpRight(Checker checker) {
        if (checker.getCell().getType() == 2) {
            if (checker.getCell().getY() % 2 == 0) return gag;
        }
        if (checker.getCell().getNumber() == 3) return gag;
        if (checker.getCell().getType() == 3) {
            if (checker.getCell().getNumber() < 3) return gag;
        }
        int checkerTeam = checker.getCell().getTeam() - 1;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addUpRight()) return c;
        }
        return null;
    }

    public static Checker allyAtDownLeft(Checker checker) {
        if (checker.getCell().getType() == 2) {
            if (checker.getCell().getY() % 2 != 0) return gag;
        }
        if (checker.getCell().getType() == 3) {
            if (checker.getCell().getNumber() > 28) return gag;
        }
        if (checker.getCell().getNumber() == 28) return gag;
        int checkerTeam = checker.getCell().getTeam() - 1;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addDownLeft()) return c;
        }
        return null;
    }

    public static Checker allyAtDownRight(Checker checker) {
        if (checker.getCell().getType() == 2) {
            if(checker.getCell().getY() % 2 == 0) return gag;
        }
        if (checker.getCell().getType() == 3) {
            if (checker.getCell().getNumber() > 28) return gag;
        }
        if (checker.getCell().getNumber() == 28 || checker.getCell().getNumber() == 3) return gag;
        int checkerTeam = checker.getCell().getTeam() - 1;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addDownRight()) return c;
        }
        return null;
    }

    public static Checker enemyAtUpLeft(Checker checker) {
        int checkerTeam = checker.getCell().getTeam() == 1 ? 1 : 0;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addUpLeft()) return c;
        }
        return null;
    }

    public static Checker enemyAtUpRight(Checker checker) {
        int checkerTeam = checker.getCell().getTeam() == 1 ? 1 : 0;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addUpRight()) return c;
        }
        return null;
    }

    public static Checker enemyAtDownLeft(Checker checker) {
        int checkerTeam = checker.getCell().getTeam() == 1 ? 1 : 0;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addDownLeft()) return c;
        }
        return null;
    }

    public static Checker enemyAtDownRight(Checker checker) {
        int checkerTeam = checker.getCell().getTeam() == 1 ? 1 : 0;
        for (Checker c : checker.getCheckersAround()[checkerTeam]) {
            if (c.getCell().getNumber() == checker.getCell().addDownRight()) return c;
        }
        return null;
    }
}
