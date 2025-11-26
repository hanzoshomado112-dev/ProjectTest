import java.util.Scanner;

public class Combat
{
    private Player player;
    private Enemy enemy;

    private int currentCharge = 0;

    boolean defendSuccess;
    private boolean combatRunning = true;
    private boolean playerDefendedThisTurn = false;


    Scanner scanner = new Scanner(System.in);

    public Combat(Enemy enemy)
    {
        this.player = Player.currentPlayer();
        this.enemy = enemy;
    }



    public void start()
    {
        System.out.println("You are fighting a " + enemy.getName());
        System.out.println("It's HP is " + enemy.getCurrentHealth());
        utils.delay(1000);

        while (combatRunning && player.isAlive() && enemy.isAlive())
        {
            playerTurn();

            if (!enemy.isAlive())
            {
                System.out.println("Victory!");
                int coinsDropped = enemy.getCoinsDropped();
                player.addCoins(coinsDropped);
                System.out.println("Gained " + coinsDropped + " coins!");
                player.revive();
                break;
            }
            enemyTurn();
            if (!player.isAlive()) {
                System.out.println("You have died.");
                break;
            }
        }

    }


    public void playerTurn()
    {
        boolean hasAnswered = false;

        while (hasAnswered == false) {
            System.out.println("Choose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Charge");
            System.out.println("3. Defend");
            System.out.println("4. Run");
            System.out.print("> ");

            String input = scanner.nextLine();

            //account for if user enteres a letter
            try {
                int answer = Integer.parseInt(input);

                switch (answer) {
                    case 1 -> {
                        playerAttack();
                        utils.delay(500);
                        hasAnswered = true;
                    }
                    case 2 -> {
                        charge();
                        utils.delay(500);
                        hasAnswered = true;
                    }
                    case 3 -> {
                        defend();
                        utils.delay(500);
                        hasAnswered = true;
                    }
                    case 4 -> {
                        run();
                        utils.delay(500);
                        hasAnswered = true;
                    }
                    default -> System.out.println("Please input a number 1-4");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
            }
        }

    }



    public void enemyTurn() {
        System.out.println(enemy.getName() + "'s turn.");

        int enemyDamage = enemy.calculateDamageDealt();

        // Calculate evasion
        double evadePercent = player.getEvadeChance();
        boolean playerEvades = utils.randomDouble(0, 100) < evadePercent;

        if (playerEvades) {
            System.out.println("You evaded the attack! No damage taken.");
        } else {
            if (playerDefendedThisTurn)
            {
                if (defendSuccess)
                {
                    System.out.println("You defended the attack! You go in for a counter attack!");
                    int counterDamage = (int)(0.7 * enemyDamage);
                    enemy.takeDamage(counterDamage);
                    System.out.println(enemy.getName() + " took " + counterDamage + " damage from your counterattack!");
                }
                else
                {
                    int reducedDamage = (int)(0.7 * enemyDamage);
                    System.out.println("You defended but failed! You take reduced damage: " + reducedDamage);
                    player.takeDamage(reducedDamage);
                }
            }
            else
            {
                System.out.println(enemy.getName() + " attacks for " + enemyDamage + " damage!");
                player.takeDamage(enemyDamage);
            }
        }

        System.out.println("Your HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println("Enemy HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());


        defendSuccess = false;
        playerDefendedThisTurn = false;

        utils.delay(1000);
    }




    //player options
    public void playerAttack()
    {
        double dodgeChance = 0.05;

        if (utils.randomDouble(0, 1) < dodgeChance) {
            System.out.println(enemy.getName() + " dodged your attack!");
        }
        else {
            int damage = (int) (2 * player.calculatePlayerDamage(player.getStrength(), player.getEquippedWeapon().getDamage(), player.getCritChance(), player.getCritMultiplier()));
            enemy.takeDamage(damage);
            System.out.print(enemy.getName() + " took " + damage + " damage! \nit's new HP is " + enemy.getCurrentHealth());
        }
        utils.delay(1000);
    }

    public void charge()
    {
        int damage = player.calculatePlayerDamage(player.getStrength(), player.getEquippedWeapon().getDamage(), player.getCritChance(), player.getCritMultiplier());
        int chargeIncrement = player.calculateChargeIncrease();
        int chargeGauge = player.getEquippedWeapon().getChargeCounter();

        double multiplier = player.calculateChargeMultiplier();

        currentCharge+=chargeIncrement;

        if(currentCharge < chargeGauge)
        {
            System.out.println("Charge Meter: "+ currentCharge + "/" + chargeGauge);
        }
        else
        {
            System.out.println("Fully Charged Attack!");
            int chargeDamage = (int) (damage * multiplier);
            enemy.takeDamage(chargeDamage);
            System.out.println(enemy.getName() + " took " + chargeDamage + " damage! \nit's new HP is " + enemy.getCurrentHealth());
            currentCharge = 0;
        }

    }
    public void defend()
    {
        playerDefendedThisTurn = true;
        double defendChance = player.calculateDefendSuccessChance();
        defendSuccess = utils.randomDouble(0, 1) < defendChance;

        if (defendSuccess)
        {
            System.out.println("You successfully defended the attack! You'll counter the enemy's next attack!");
        }
        else
        {
            System.out.println("Defend failed! You will take reduced damage this turn.");
        }
    }


    public void run() {
        double runawayChance = player.calculateRunawaySuccessChance();
        double roll = utils.randomDouble(0, 1);

        if (roll < runawayChance) {
            System.out.println("You successfully ran away!");
            combatRunning = false;
        }
        else
        {
            System.out.println("Failed to run away!");
            enemyTurn();
        }
    }

}
