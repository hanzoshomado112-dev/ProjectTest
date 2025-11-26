import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Player implements Serializable
{
    private PlayerClass playerClass;
    private String name;

    public int level;

    public int vigor;
    public int strength;
    public int defense;
    public int intelligence;

    private double critChance;
    private int critMultiplier;
    private int evadeChance;


    public int maxHealth;
    public int currentHealth;

    private Inventory inventory = new Inventory();
    private Item equippedWeapon;

    private int currentCoins;



    public enum PlayerClass
    {
        WARRIOR(12, 15, 10, 6, 2, 5),
        ROGUE(8, 10, 6, 8, 4, 20 ),
        MAGE(7, 4, 5, 18, 3, 8 ),
        GUARDIAN(16, 9, 15, 5, 1, 3);

        private int vigor;
        private int strength;
        private int defense;
        private int intelligence;
        private int critMultiplier;
        private int evadeChance;

        PlayerClass(int vigor, int strength, int defense, int intelligence, int critMultiplier, int evadeChance)
        {
            this.vigor = vigor;
            this.strength = strength;
            this.defense = defense;
            this.intelligence = intelligence;
            this.critMultiplier = critMultiplier;
            this.evadeChance = evadeChance;
        }

        //getters
        public int getVigor()
        {
            return vigor;
        }
        public int getStrength()
        {
            return strength;
        }
        public int getDefense()
        {
            return defense;
        }
        public int getIntelligence()
        {
            return intelligence;
        }
        public int getCritMultiplier()
        {
            return critMultiplier;
        }
        public int getEvadeChance()
        {
            return evadeChance;
        }

    }
    public int getVigor() {
        return vigor;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public double getCritChance()
    {
        return critChance;
    }
    public int getCritMultiplier() {
        return critMultiplier;
    }
    public int getCurrentHealth()
    {
        return currentHealth;
    }
    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getEvadeChance() {
        return evadeChance;
    }

    public Item getEquippedWeapon()
    {
        return equippedWeapon;
    }

    public String getName()
    {
        return name;
    }

    public int getCurrentCoins()
    {
        return currentCoins;
    }

    public int getLevel()
    {
        return level;
    }


    public Player(String name, PlayerClass playerClass)
    {
        this.name = name;
        this.playerClass = playerClass;

        this.vigor = playerClass.getVigor();
        this.strength = playerClass.getStrength();
        this.defense = playerClass.getDefense();
        this.intelligence = playerClass.getIntelligence();
        this.critMultiplier = playerClass.getCritMultiplier();
        this.evadeChance = playerClass.getEvadeChance();


        calculateStats();

        switch(playerClass)
        {
            case WARRIOR -> inventory.addItem(ItemManager.createBasicSword());
            case MAGE -> inventory.addItem(ItemManager.createBasicStaff());
            case ROGUE -> inventory.addItem(ItemManager.createBasicDagger());
            case GUARDIAN -> inventory.addItem(ItemManager.createBasicWarHammer());
        }

        equippedWeapon = inventory.getItems().get(0);
    }


    private void calculateStats()
    {
        DifficultyManager.difficulty diff = DifficultyManager.getCurrentDifficulty();

        level = getVigor() + getIntelligence() + getDefense() + getStrength();

        this.maxHealth = vigor * 10;
        maxHealth = (int) (maxHealth * diff.getPlayerHealthMultiplier());
        this.currentHealth = maxHealth;

        double maxCrit = 0.5;
        double k = 0.12;
        double x0 = 70;
        double threshold = 30;

        double sigmoid = maxCrit / (1 + Math.exp(-k * (level - x0)));

        double suppression = 1 - (threshold / (level + threshold));

        critChance = sigmoid * suppression;


    }


    public static Player playerCreation()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        System.out.println("Name Is: " + playerName);
        utils.printEllipses(3, 300);

        System.out.println("CHOOSE A CLASS");
        System.out.println(
                String.format("%-12s %-12s %-12s %-12s", "WARRIOR", "ROGUE", "MAGE", "GUARDIAN") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "------------", "------------", "------------", "------------") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "Vigor: 12", "Vigor: 8", "Vigor: 7", "Vigor: 16") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "Str: 15", "Str: 10", "Str: 4", "Str: 9") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "Def: 10", "Def: 6", "Def: 5", "Def: 15") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "Int: 6", "Int: 8", "Int: 18", "Int: 5") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "Crit: 2", "Crit: 4", "Crit: 3", "Crit: 1") +
                        "\n" +
                        String.format("%-12s %-12s %-12s %-12s", "Evade: 5", "Evade: 20", "Evade: 8", "Evade: 3")
        );

        Player player = null;

        Boolean hasAnswered = false;

        while (hasAnswered == false) {
            System.out.print("Choose Your Class: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("warrior")) {
                 player = new Player(playerName, PlayerClass.WARRIOR);
                hasAnswered = true;
            }
            else if (answer.equalsIgnoreCase("rogue")) {
                 player = new Player(playerName, PlayerClass.ROGUE);
                hasAnswered = true;
            }
            else if (answer.equalsIgnoreCase("mage")) {
                 player = new Player(playerName, PlayerClass.MAGE);
                hasAnswered = true;
            }
            else if (answer.equalsIgnoreCase("guardian")) {
                 player = new Player(playerName, PlayerClass.GUARDIAN);
                hasAnswered = true;
            }
            else
            {
                System.out.println("Please enter a valid class");
            }
        }

        System.out.println("PLAYER CREATED" );
        currentPlayer = player;
        return player;
    }



    //Player stuff
    private static Player currentPlayer;

    public static Player currentPlayer()
    {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }



    public void printStats()
    {
        System.out.println("=== PLAYER STATS ===");
        System.out.println("Vigor: " + getVigor());
        System.out.println("Strength: " + getStrength());
        System.out.println("Defense: " + getDefense());
        System.out.println("Intelligence: " + getIntelligence());
        System.out.println("Critical Multiplier: " + getCritMultiplier());
        System.out.println("Evade Chance: " + getEvadeChance() + "%");
        System.out.println("Current Health: " + currentHealth + "/" + maxHealth);
        System.out.println("====================");
        System.out.println("Equipped Weapon: " + equippedWeapon);
        System.out.println("Coins: " + currentCoins);

        System.out.println("====================");
    }


    // COMBAT

    public int calculatePlayerDamage(int strength, int weaponBaseDamage, double critChancePercent, int critMultiplier)
    {
        int baseDamage = weaponBaseDamage + strength;

        // : Determine if crit
        boolean isCrit = utils.randomInt(1, 100) <= critChancePercent;

        // crit multiplier
        double damage = baseDamage;
        if (isCrit)
        {
            damage *= critMultiplier;
            System.out.println("Critical Hit!");
        }

        // variance
        double variance = utils.randomDouble(0.9, 1.1);
        damage *= variance;

        // Round
        int finalDamage = Math.max(1, (int) Math.round(damage));

        return finalDamage;
    }

    public int calculateChargeIncrease() {
    int maxCharge = 4;
    int minCharge = 1;
    int maxLevel = 100;

    double scale = (double)(level - 40) / (maxLevel - 40);
    if (scale < 0) scale = 0;
    if (scale > 1) scale = 1;

    double baseCharge = minCharge + scale * (maxCharge - minCharge);


    int variance = utils.randomInt(-1, 1);

    int finalCharge = (int) Math.round(baseCharge) + variance;

    // Clamp to minCharge and maxCharge
    if (finalCharge < minCharge) finalCharge = minCharge;
    if (finalCharge > maxCharge) finalCharge = maxCharge;

    return finalCharge;
}

    public double calculateChargeMultiplier() {
        int minLevel = 30;      // level where multiplier starts increasing
        int maxLevel = 100;     // level where multiplier nears max
        double maxMultiplier = 2.5;  // max possible multiplier
        double minMultiplier = 1.0;  // base multiplier at minLevel

        // Clamp level to range
        if (level < minLevel) level = minLevel;
        if (level > maxLevel) level = maxLevel;

        // Parameters for sigmoid curve
        double k = 0.08;       // steepness
        double x0 = (maxLevel + minLevel) / 2.0;  // midpoint

        // Sigmoid function scaled between 0 and 1
        double normalized = 1 / (1 + Math.exp(-k * (level - x0)));

        // Scale sigmoid from minMultiplier to maxMultiplier
        double multiplier = minMultiplier + normalized * (maxMultiplier - minMultiplier);

        return multiplier;
    }

    public double calculateDefendSuccessChance() {
        double maxChance = 0.80;
        double minChance = 0.25;

        double k = 0.3;               // Steepness of curve
        double x0 = 10;               // Midpoint defense value for 50% chance

        // Sigmoid function to smooth success chance
        double sigmoid = 1 / (1 + Math.exp(-k * (defense - x0)));

        // Scale between minChance and maxChance
        double successChance = minChance + sigmoid * (maxChance - minChance);

        return successChance;
    }

    public int calculateDamageTaken(int incomingDamage, int defense)
    {
        // Calculate damage reduction with diminishing returns
        double damageReduction = (double) defense / (defense + 100);

        // Apply damage reduction
        double rawDamage = incomingDamage * (1 - damageReduction);

        // Add random variance
        double variance = utils.randomDouble(0.9, 1.1);
        rawDamage *= variance;

        // Minimum damage is 1
        int finalDamage = (int) Math.max(1, Math.round(rawDamage));

        return finalDamage;
    }

    public double calculateRunawaySuccessChance() {
        double maxChance = 0.7;
        double k = 25.0;         // midpoint shifted right
        double n = 2.0;          // quadratic curve

        double evadePow = Math.pow(evadeChance, n);
        double kPow = Math.pow(k, n);

        double chance = maxChance * (evadePow / (evadePow + kPow));

        return chance;
    }



    public void takeDamage(int damage)
    {
        int damageTaken = calculateDamageTaken(damage, defense);
        currentHealth -= damageTaken;
        if(currentHealth < 0)
        {
            currentHealth = 0;
        }
    }

    // COINS

    public void addCoins(int numberAdded)
    {
        currentCoins+=numberAdded;
    }

    public void setCoins(int setNumber)
    {
        currentCoins = setNumber;
    }


    //INVENTORY STUFF

    //Getter
    public Inventory getInventory()
    {
        return inventory;
    }

    //Modify Inventory

    public void equipWeapon(Scanner scanner, Inventory inventory)
    {
        ArrayList<Item> weapons = inventory.printWeaponsAndReturnList();

        if (weapons.isEmpty())
        {
            System.out.println("You have no weapons to equip.");
            return;
        }

        int choice = -1;
        boolean validChoice = false;

        while (!validChoice)
        {
            System.out.print("Choose a weapon by number: ");
            String input = scanner.nextLine();

            try
            {
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > weapons.size()) {
                    System.out.println("You don't have this many weapons!");
                }
                else
                {
                    validChoice = true;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        Item selected = weapons.get(choice - 1);
        equippedWeapon = selected;
        System.out.println("Equipped: " + selected.getName());
    }



    public boolean isAlive()
    {
        if(currentHealth > 0)
        {
            return true;
        }
        else
        {
            revive();
            return false;
        }
    }

    public void revive()
    {
        currentHealth = maxHealth;
    }



    // LEVELING

    public void upgradeStat(String statName)
    {
        int cost = calculateUpgradeCost();

        switch (statName) {
            case "Vigor" -> {
                if (vigor >= 150) {
                    System.out.println("Vigor is already at the maximum level (150)!");
                    return;
                }
            }
            case "Strength" -> {
                if (strength >= 150) {
                    System.out.println("Strength is already at the maximum level (150)!");
                    return;
                }
            }
            case "Defense" -> {
                if (defense >= 150) {
                    System.out.println("Defense is already at the maximum level (150)!");
                    return;
                }
            }
            case "Intelligence" -> {
                if (intelligence >= 150) {
                    System.out.println("Intelligence is already at the maximum level (150)!");
                    return;
                }
            }
        }

        if (currentCoins >= cost) {
            switch (statName) {
                case "Vigor" -> vigor += 1;
                case "Strength" -> strength += 1;
                case "Defense" -> defense += 1;
                case "Intelligence" -> intelligence += 1;
            }
            calculateStats();
            System.out.println("Upgraded " + statName + "! Cost: " + cost + " coins.");
            currentCoins -= cost;

        }
        else
        {
            System.out.println("You don't have enough coins to upgrade! Need: " + calculateUpgradeCost());
        }
    }




    private int calculateUpgradeCost()
    {
        int lvl = level;

        double x = Math.max((lvl + 81) - 92, 0) * 0.02;
        double cost = (x + 0.1) * Math.pow(lvl + 81, 2) + 1;

        int adjustedCost = (int) (cost / 10);

        return adjustedCost;
    }

    public void setLevel(String statName, int level)
    {
        switch (statName)
        {
            case "Vigor" -> vigor = level;
            case "Strength" -> strength = level;
            case "Defense" -> defense = level;
            case "Intelligence" -> intelligence = level;
        }
        calculateStats();
        System.out.println("Upgraded " + statName + "!");


    }



    //SAVE STUFF
    public void autoEquipWeapon(Item weapon)
    {
        this.equippedWeapon = weapon;
    }


}

