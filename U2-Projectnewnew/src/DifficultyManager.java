import java.util.Scanner;

public class DifficultyManager
{
    public enum difficulty {
        EASY(0.75, 1.25),
        MEDIUM(1, 1),
        HARD(1.25, 0.90);


        private double enemyHealthMultiplier;
        private double playerHealthMultiplier;


        difficulty(double enemyHealthMultiplier, double playerHealthMultiplier) {
            this.enemyHealthMultiplier = enemyHealthMultiplier;
            this.playerHealthMultiplier = playerHealthMultiplier;
        }


        //return statements
        public double getEnemyHealthMultiplier() {
            return enemyHealthMultiplier;
        }


        public double getPlayerHealthMultiplier() {
            return playerHealthMultiplier;
        }
    }

    private static difficulty currentDifficulty = null;

    //handle difficulty within the method so main doesn't get messy
    public static difficulty askForDifficulty()
    {

        if(currentDifficulty != null)
        {
            return currentDifficulty;
        }

        Scanner scanner = new Scanner(System.in);
        boolean confirmedDifficulty = false;


        System.out.println("Select your difficulty:");
        System.out.print("EASY \nMEDIUM \nHARD\n");
        System.out.println("==============================");
        System.out.print("> ");


        while(!confirmedDifficulty) {
            String selectedDifficulty = scanner.nextLine();

            if (selectedDifficulty.equalsIgnoreCase("EASY")) {
                currentDifficulty = difficulty.EASY;
                confirmedDifficulty = true;
            }
            else if (selectedDifficulty.equalsIgnoreCase("MEDIUM")) {
                currentDifficulty = difficulty.MEDIUM;
                confirmedDifficulty = true;
            }
            else if (selectedDifficulty.equalsIgnoreCase("HARD")) {
                currentDifficulty = difficulty.HARD;
                confirmedDifficulty = true;
            }
            else
            {
                System.out.println("Please enter a valid input");
                System.out.print("> ");
            }
        }
        return currentDifficulty;
    }

    public static difficulty getCurrentDifficulty()
    {
        return currentDifficulty;
    }

    public static void setDifficulty(difficulty diff)
    {
        currentDifficulty = diff;
    }
}
