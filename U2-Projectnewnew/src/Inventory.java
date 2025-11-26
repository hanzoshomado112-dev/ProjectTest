import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable
{

    private ArrayList<Item> inventory = new ArrayList<>();


    public ArrayList<Item> getItems()
    {
        return inventory;
    }

    public void addItem(Item addition)
    {
        inventory.add(addition);
    }

    public ArrayList<Item> printWeaponsAndReturnList() {
        ArrayList<Item> weapons = new ArrayList<>();
        int count = 1;

        for (Item item : inventory) {
            if (item.getType() == Item.ItemType.WEAPON) {
                System.out.println(count + ") " + item);
                System.out.println("----------------");
                weapons.add(item);
                count++;
            }
        }

        return weapons;
    }



    public void printWeapons()
    {
        for(int i = 0; i <= inventory.size() - 1; i++)
        {
            Item item = inventory.get(i);
            if (item.getType() == Item.ItemType.WEAPON)
            {
                System.out.println(item);
                System.out.println("----------------");
            }
        }
    }

    public void printPotions()
    {
        int count = 0;
            for (int i = 0; i <= inventory.size() - 1; i++) {
                Item item = inventory.get(i);
                if (item.getType() == Item.ItemType.CONSUMABLE) {
                    System.out.println(item);
                    System.out.println("----------------");
                    count++;
                }
            }
            if (count == 0)
            {
                System.out.println("You have no potions!");
                utils.delay(200);
                count = 0;
            }
    }








}
