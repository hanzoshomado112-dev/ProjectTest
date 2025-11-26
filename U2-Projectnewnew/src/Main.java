import java.util.Scanner;

public class Main
{
    UI userInterface = new UI();


    public static void main(String[] args)
    {

        if (!SaveManager.hasAnySaves()) {
            Player.playerCreation();
            DifficultyManager.difficulty currentDifficulty = DifficultyManager.askForDifficulty();
        } else {
            System.out.println("=== START ===");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.print("> ");

            Scanner s = new Scanner(System.in);
            int choice = s.nextInt();
            s.nextLine();

            if (choice == 1) {
                Player.playerCreation();
            } else {
                UI ui = new UI();
                ui.loadMenu();
            }
        }


        Main game = new Main();
        game.Start();
    }

    public void Start() {
        Scanner scanner = new Scanner(System.in);
        Boolean inputActive = true;

        while (inputActive) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "help" ->
                {
                    System.out.println("\nHELP COMMANDS \n========================");
                    System.out.println("\"inventory\" - opens the players inventory");
                    System.out.println("\"stats\" - prints your current stats");
                    System.out.println("\"upgrade\" - opens the upgrade menu");
                    System.out.println("\"menu\" - opens the main menu");
                    System.out.println("\"cheats\" - opens a cheat menu");
                }
                case "inventory" ->
                {
                    utils.delay(500);
                    userInterface.browseInventory();
                }
                case "stats" ->
                {
                    utils.delay(500);
                    userInterface.printStats();
                }
                case "cheats" ->
                {
                    utils.delay(300);
                    userInterface.openCheatMenu();
                }
                case "upgrade" ->
                {
                    utils.delay(100);
                    userInterface.openUpgradeMenu();
                }
                case "menu" ->
                {
                    utils.delay(100);
                    userInterface.openMenu();

                }
                case "testfight" ->
                {
                    utils.delay(500);
                    Combat testCombat = new Combat(EnemyManager.createBasicZombie());
                    testCombat.start();
                }

                default ->
                {
                    System.out.println("\"Invalid command. Type \\\"help\\\" for a list of commands.");
                }

            }

        }

    }

    public UI getUI()
    {
        return userInterface;
    }
}