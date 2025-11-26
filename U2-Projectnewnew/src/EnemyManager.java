import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class EnemyManager
{
    private Inventory inventory;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    public ArrayList<Enemy> returnEnemies()
    {
        return enemies;
    }
    public void addItem(Enemy enemy)
    {
        enemies.add(enemy);
    }

    public void createNewEnemy()
    {
        Scanner scanner = new Scanner(System.in);

        String name;
        int health;
        int strength;
        int defense;
        int level;
        int coinsDropped;

        System.out.print("Enemy name: ");
        name = scanner.nextLine();
        while (name.trim().isEmpty()) {
            System.out.print("Please enter a valid weapon name: ");
            name = scanner.nextLine();
        }


        System.out.print("Health: ");
         health = utils.getValidInt(scanner);

        System.out.print("Strength: ");
         strength = utils.getValidInt(scanner);

        System.out.print("Defense: ");
         defense = utils.getValidInt(scanner);

        System.out.print("Level: ");
         level = utils.getValidInt(scanner);

        System.out.print("Coins Dropped (Will vary by 10%): ");
        coinsDropped = utils.getValidInt(scanner);

        // Enemies always undead cause I don't really have time to do anything with enemy types
        addItem(new Enemy(name, health, strength, defense, level, Enemy.enemyType.UNDEAD, coinsDropped));
        scanner.nextLine();
        utils.printEllipses(3, 300);
        System.out.println(name + " added!");
    }

    public void printEnemies()
    {
        for(int i = 0; i <= enemies.size() - 1; i++)
        {
            Enemy enemy = enemies.get(i);
            System.out.println("1. " + enemy.getName());
            System.out.println("----------------");
        }
    }

   public static Enemy createBasicZombie()
   {
       Enemy basicZombie = new Enemy("Zombie", 100, 8, 20, 10, Enemy.enemyType.UNDEAD, 100);
       return basicZombie;
   }




}
