import java.util.Scanner;
import java.io.File;

public class UI {
    Scanner scanner = new Scanner(System.in);
    Scanner input = new Scanner(System.in);
    boolean hasAnswered = false;
    EnemyManager enemyManager = new EnemyManager();

    public void browseInventory() {
        hasAnswered = false;

        while (!hasAnswered) {

            System.out.println("INVENTORY");
            System.out.println("===========================");
            System.out.println("1.) See Weapons");
            System.out.println("2.) See Potions");
            System.out.println("3.) Equip Weapon");
            System.out.println("0.) Leave");
            System.out.println("Equipped Weapon: " + Player.currentPlayer().getEquippedWeapon());
            System.out.println("Coins: " + Player.currentPlayer().getCurrentCoins());

            int answer;
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Invalid Input! Try again. \n");
                utils.delay(200);
                continue;
            }
            switch (answer) {
                case 1 -> {
                    seeWeapons();
                    hasAnswered = true;
                }
                case 2 -> {
                    seePotions();
                    hasAnswered = true;
                }
                case 3 -> {
                    equipWeapon();
                    hasAnswered = true;
                }
                case 0 -> {
                    hasAnswered = true;
                    break;
                }

                default -> {
                    System.out.println("Invalid Input! Try again. \n");
                    utils.delay(200);
                }
            }
        }
    }

    public void seeWeapons() {
        Player.currentPlayer().getInventory().printWeapons();
        hasAnswered = false;
    }

    public void seePotions() {
        Player.currentPlayer().getInventory().printPotions();
        hasAnswered = false;
    }

    public void equipWeapon() {
        Player.currentPlayer().equipWeapon(input, Player.currentPlayer().getInventory());
        hasAnswered = false;
    }

    public void printStats() {
        Player.currentPlayer().printStats();
    }

    public void openCheatMenu() {
        hasAnswered = false;
        Player player = Player.currentPlayer();

        while (!hasAnswered) {

            System.out.println("CHEATS");
            System.out.println("===========================");
            System.out.println("1.) Create New Weapon");
            System.out.println("2.) Create New Enemy");
            System.out.println("3.) Fight Enemy");
            System.out.println("8.) Set Coin Amount");
            System.out.println("9.) Set Player Level");
            System.out.println("0.) Leave");
            System.out.println("Player Name: " + player.getName());
            System.out.println("Coins: " + player.getCurrentCoins());
            System.out.println("===========================");

            int answer;
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Invalid Input! Try again. \n");
                utils.delay(200);
                continue;
            }
            switch (answer) {
                case 1 -> {
                    hasAnswered = true;
                    new ItemManager(Player.currentPlayer().getInventory()).createNewWeapon();
                }
                case 2 -> {
                    hasAnswered = true;
                    enemyManager.createNewEnemy();
                }
                case 3 -> {
                    hasAnswered = true;
                    fightEnemy();
                }
                case 8 -> {
                    hasAnswered = true;
                    setCoins();
                }
                case 9 -> {
                    hasAnswered = true;
                    setLevelMenu();
                }
                case 0 -> {
                    hasAnswered = true;
                    return;
                }

                default -> {
                    System.out.println("Invalid Input! Try again. \n");
                    utils.delay(200);
                }
            }
        }
    }

    public void openUpgradeMenu() {
        hasAnswered = false;
        Player player = Player.currentPlayer();

        while (!hasAnswered) {

            System.out.println("=== UPGRADE MENU ===");
            System.out.println("Level: " + player.getLevel());
            System.out.println("Coins: " + player.getCurrentCoins());
            System.out.println("1. Vigor (" + player.getVigor() + ") - Increases Health");
            System.out.println("2. Strength (" + player.getStrength() + ") - Increases Attack");
            System.out.println("3. Defense (" + player.getDefense() + ") - Reduces Damage");
            System.out.println("4. Intelligence (" + player.getIntelligence() + ")");
            System.out.println("5. Exit");

            int answer;
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Invalid Input! Try again. \n");
                utils.delay(200);
                continue;
            }
            switch (answer) {
                case 1 -> {
                    player.upgradeStat("Vigor");
                }
                case 2 -> {
                    player.upgradeStat("Strength");
                }
                case 3 -> {
                    player.upgradeStat("Defense");
                }
                case 4 -> {
                    player.upgradeStat("Intelligence");
                }
                case 5 -> {
                    hasAnswered = true;
                    break;
                }

                default -> {
                    System.out.println("Invalid Input! Try again. \n");
                    utils.delay(200);
                }
            }
        }
    }

    public void setLevelMenu() {
        System.out.println("\n=== SET LEVEL ===");
        System.out.println("1. Vigor (" + Player.currentPlayer().getVigor() + ") - Increases Health");
        System.out.println("2. Strength (" + Player.currentPlayer().getStrength() + ") - Increases Attack");
        System.out.println("3. Defense (" + Player.currentPlayer().getDefense() + ") - Reduces Damage");
        System.out.println("4. Intelligence (" + Player.currentPlayer().getIntelligence() + ")");
        System.out.println("5. Exit");

        int answer;
        if (scanner.hasNextInt()) {
            answer = scanner.nextInt();
        } else {
            scanner.next();
            System.out.println("Invalid Input! Try again. \n");
            utils.delay(200);
            return;
        }

        if (answer == 5) {
            return;
        }

        System.out.println("Set Level To? (Max 150)");
        int level;
        if (scanner.hasNextInt()) {
            level = scanner.nextInt();
            if (level > 150) {
                System.out.println("Invalid Input! Try again. \n");
                return;
            }
        } else {
            scanner.next();
            System.out.println("Invalid Input! Try again. \n");
            utils.delay(200);
            return;
        }

        switch (answer) {
            case 1 -> {
                hasAnswered = true;
                Player.currentPlayer().setLevel("Vigor", level);
            }
            case 2 -> {
                hasAnswered = true;
                Player.currentPlayer().setLevel("Strength", level);
            }
            case 3 -> {
                hasAnswered = true;
                Player.currentPlayer().setLevel("Defense", level);
            }
            case 4 -> {
                hasAnswered = true;
                Player.currentPlayer().setLevel("Intelligence", level);
            }
            case 5 -> {
                hasAnswered = true;
                break;
            }

            default -> {
                System.out.println("Invalid Input! Try again. \n");
                utils.delay(200);
            }
        }
    }

    public void fightEnemy() {
        if (enemyManager.returnEnemies().isEmpty()) {
            System.out.println("Custom Enemy List is Empty! Please create a new enemy first");
            return;
        }

        System.out.println("Choose an enemy to fight:");
        enemyManager.printEnemies();

        int answer = -1;

        while (true) {
            System.out.print("> ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            answer = scanner.nextInt();
            scanner.nextLine();

            if (answer < 1 || answer > enemyManager.returnEnemies().size()) {
                System.out.println("Invalid choice!");
                continue;
            }

            Enemy selectedEnemy = enemyManager.returnEnemies().get(answer - 1);

            System.out.println("You selected: " + selectedEnemy.getName());
            utils.delay(300);

            Combat combat = new Combat(selectedEnemy);
            combat.start();
            break;
        }
    }

    public void setCoins() {
        int setCoinsTo;
        System.out.print("Set Coins To? ");
        if (scanner.hasNextInt()) {
            setCoinsTo = scanner.nextInt();
        } else {
            scanner.next();
            System.out.println("Invalid Input! Coins not set.\n");
            return;
        }
        Player.currentPlayer().setCoins(setCoinsTo);
        System.out.println("New Coins Amount: " + Player.currentPlayer().getCurrentCoins());
        utils.delay(100);
    }

    public void openMenu() {
        boolean hasAnswered = false;

        while (!hasAnswered) {
            System.out.println("=== MAIN MENU ===");
            System.out.println("1. Save Game");
            System.out.println("2. Load Game");
            System.out.println("3. Delete saves");
            System.out.println("0. Exit");
            System.out.println("Difficulty: " + DifficultyManager.getCurrentDifficulty());
            System.out.print("> ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Invalid Input! Try again.\n");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    saveMenu();
                    hasAnswered = true;
                }
                case 2 -> {
                    loadMenu();
                    hasAnswered = true;
                }
                case 3 -> {
                    deleteSaveMenu();
                    hasAnswered = true;
                }
                case 0 -> hasAnswered = true;
                default -> System.out.println("Invalid Input! Try again.\n");
            }
        }
    }

    private void saveMenu() {
        File folder = new File("saves");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        System.out.println("\n=== SAVE GAME ===");

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName().replace(".json", ""));
            }
        } else {
            System.out.println("No saves found. You can create a new one.");
        }

        System.out.print("Enter save slot number (or new name): ");
        String input = scanner.nextLine();

        String slotName;
        try {
            int slotIndex = Integer.parseInt(input) - 1;
            if (files != null && slotIndex >= 0 && slotIndex < files.length) {
                slotName = files[slotIndex].getName().replace(".json", "");
            } else {
                System.out.println("Invalid number! Save cancelled.\n");
                return;
            }
        } catch (NumberFormatException e) {
            slotName = input;
        }

        SaveManager.saveGame(slotName);
    }

    public void loadMenu() {
        File folder = new File("saves");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        System.out.println("\n=== LOAD GAME ===");

        if (files == null || files.length == 0) {
            System.out.println("No saves found!\n");
            return;
        }

        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName().replace(".json", ""));
        }

        System.out.print("Enter save slot number to load: ");
        int slotIndex;
        if (scanner.hasNextInt()) {
            slotIndex = scanner.nextInt() - 1;
            scanner.nextLine();
        } else {
            scanner.nextLine();
            System.out.println("Invalid Input! Load cancelled.\n");
            return;
        }

        if (slotIndex < 0 || slotIndex >= files.length) {
            System.out.println("Invalid choice! Load cancelled.\n");
            return;
        }

        String slotName = files[slotIndex].getName().replace(".json", "");
        SaveManager.loadGame(slotName);
    }

    private void deleteSaveMenu() {
        File folder = new File("saves");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null || files.length == 0) {
            System.out.println("No saves to delete.\n");
            return;
        }

        System.out.println("=== DELETE SAVE ===");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName().replace(".json", ""));
        }

        System.out.print("Enter save number to delete: ");
        int slotIndex;
        if (scanner.hasNextInt()) {
            slotIndex = scanner.nextInt() - 1;
            scanner.nextLine();
        } else {
            scanner.nextLine();
            System.out.println("Invalid input. Delete cancelled.\n");
            return;
        }

        if (slotIndex < 0 || slotIndex >= files.length) {
            System.out.println("Invalid choice. Delete cancelled.\n");
            return;
        }

        String slotName = files[slotIndex].getName().replace(".json", "");
        SaveManager.deleteSave(slotName);
    }
}