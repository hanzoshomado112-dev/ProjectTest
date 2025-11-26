import java.util.Scanner;

public class ItemManager
{
    private Inventory inventory;

    public ItemManager(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public static Item createBasicSword()
    {
        Item basicSword = new Item("Basic Sword", Item.ItemType.WEAPON, 20, 0, 4);
        return basicSword;
    }

    public static Item createBasicStaff()
    {
        Item basicStaff = new Item("Basic Staff", Item.ItemType.WEAPON, 20, 0, 4);
        return basicStaff;
    }

    public static Item createBasicDagger()
    {
        Item basicDagger = new Item("Basic Dagger", Item.ItemType.WEAPON, 10, 0, 2);
        return basicDagger;
    }

    public static Item createBasicWarHammer()
    {
        Item warHammer = new Item("Basic Sword", Item.ItemType.WEAPON, 20, 0, 6);
        return warHammer;
    }

    public void createNewWeapon()
    {
        Scanner scanner = new Scanner(System.in);

         String name;
         int damage;
         int chargeCounter;

            System.out.print("Weapon name: ");
            name = scanner.nextLine();

            System.out.print("Damage: ");
            damage = scanner.nextInt();

            System.out.print("Charge needed for charged attack: ");
            chargeCounter = scanner.nextInt();
            scanner.nextLine();
            utils.printEllipses(3, 300);
            System.out.println(name + " added!");

            // Add new weapon to existing inventory
            inventory.addItem(new Item(name, Item.ItemType.WEAPON, damage, 0, chargeCounter));

    }

}
