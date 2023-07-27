package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Battlefield battlefield = new Battlefield();

        battlefield.printField();
        for (Ship ship : battlefield.getShips()) {
            boolean isPlaced = false;
            while (!isPlaced) {
                System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getSize());
                String start = scanner.next();
                String end = scanner.next();
                if (battlefield.placeShip(ship, start, end)) {
                    battlefield.printField();
                    isPlaced = true;
                }
            }
        }
        System.out.println("The game starts!");

        while (!battlefield.isGameOver()) {
            battlefield.printFoggedField();
            System.out.println("Take a shot!");
            String target = scanner.next();
            if (!battlefield.takeShot(target)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
        System.out.println("Congratulations! You sank all the ships!");
    }
}
