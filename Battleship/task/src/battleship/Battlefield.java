package battleship;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Battlefield {
    private final char[][] field;
    private final char[][] foggedField;
    private final Ship[] ships;

    public Battlefield() {
        this.field = new char[10][10];
        this.foggedField = new char[10][10];
        this.ships = new Ship[] {
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)
        };

        for (int i = 0; i < 10; i++) {
            Arrays.fill(field[i], '~');
            Arrays.fill(foggedField[i], '~');
        }
    }

    public List<Ship> getShips() {
        return Arrays.asList(ships);
    }

    public void printField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean placeShip(Ship ship, String start, String end) {
        int startRow = start.charAt(0) - 'A';
        int startCol = Integer.parseInt(start.substring(1)) - 1;
        int endRow = end.charAt(0) - 'A';
        int endCol = Integer.parseInt(end.substring(1)) - 1;

        boolean isHorizontal = startRow == endRow;
        boolean isVertical = startCol == endCol;

        int minRow = Math.min(startRow, endRow);
        int maxRow = Math.max(startRow, endRow);
        int minCol = Math.min(startCol, endCol);
        int maxCol = Math.max(startCol, endCol);

        if (isHorizontal) {
            if (Math.abs(endCol - startCol) + 1 != ship.getSize()) {
                return false;
            }
        } else if (isVertical) {
            if (Math.abs(endRow - startRow) + 1 != ship.getSize()) {
                return false;
            }
        } else {
            return false;
        }

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                if (field[row][col] != '~') {
                    return false;
                }
            }
        }

        for (int row = Math.max(0, minRow - 1); row <= Math.min(9, maxRow + 1); row++) {
            for (int col = Math.max(0, minCol - 1); col <= Math.min(9, maxCol + 1); col++) {
                if (field[row][col] != '~' && (row != minRow || row != maxRow || col != minCol || col != maxCol)) {
                    return false;
                }
            }
        }

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                field[row][col] = 'O';
            }
        }

        return true;
    }


    public boolean takeShot(String target) {
        int targetRow = target.charAt(0) - 'A';
        int targetCol = Integer.parseInt(target.substring(1)) - 1;

        if (targetRow < 0 || targetRow > 9 || targetCol < 0 || targetCol > 9) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:");
            return false;
        }

        if (field[targetRow][targetCol] == 'O') {
            field[targetRow][targetCol] = 'X';
            foggedField[targetRow][targetCol] = 'X';
            System.out.println("\nYou hit a ship!");
        } else {
            field[targetRow][targetCol] = 'M';
            foggedField[targetRow][targetCol] = 'M';
            System.out.println("\nYou missed!");
        }
        return true;
    }

    public void printFoggedField() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i));
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + foggedField[i][j]);
            }
            System.out.println();
        }
    }
    public boolean isGameOver() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}
