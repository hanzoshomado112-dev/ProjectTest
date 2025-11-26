import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;


public class SaveManager {
    
    
    private static final String SAVE_FOLDER = "saves";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    

    

    static
    {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
    }

    public static boolean hasAnySaves() 
    {
        File folder = new File(SAVE_FOLDER);
        //folder.isEmpty isn't a thing for some reason so do this instead
        File[] files = folder.listFiles();
        return files != null && files.length > 0;
    }


    private static String getSavePath(String slotName)
    {
        return SAVE_FOLDER + "/" + slotName + ".json";
    }

    public static void saveGame(String slotName) {

        FileWriter writer = null;
        Player player = Player.currentPlayer();
        SaveData data = new SaveData();

        // PLAYER STATS
        data.playerName = player.getName();
        data.vigor = player.getVigor();
        data.strength = player.getStrength();
        data.defense = player.getDefense();
        data.intelligence = player.getIntelligence();
        data.currentHealth = player.getCurrentHealth();
        data.maxHealth = player.getMaxHealth();
        data.level = player.getLevel();
        data.coins = player.getCurrentCoins();
        data.playerClass = player.getPlayerClass();

        // INVENTORY
        data.inventory = player.getInventory().getItems();

        // EQUIPPED WEAPON
        data.equippedWeaponName = player.getEquippedWeapon().getName();
        data.equippedWeaponDamage = player.getEquippedWeapon().getDamage();
        data.equippedWeaponCharge = player.getEquippedWeapon().getChargeCounter();

        // DIFFICULTY
        data.difficulty = DifficultyManager.getCurrentDifficulty();

        // Account for errors like no storage, no perms, invalid slotName (which shouldn't happen), etc...
        // Then, write the actual data onto the file
        try
        {
            writer = new FileWriter(getSavePath(slotName));
            gson.toJson(data, writer);
            System.out.println("Game saved to " + getSavePath(slotName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close writer
            if (writer != null)
            {
                try
                {
                writer.close();
                }
                catch(Exception e)
                {
                   
                }
            }
        }
    }


    public static void loadGame(String slotName)
    {
         FileReader reader = null;
        try 
        {
            reader = new FileReader(getSavePath(slotName));
            SaveData data = gson.fromJson(reader, SaveData.class);


            Player loaded = new Player(data.playerName, data.playerClass);


            loaded.vigor = data.vigor;
            loaded.strength = data.strength;
            loaded.defense = data.defense;
            loaded.intelligence = data.intelligence;

            loaded.level = data.level;
            loaded.maxHealth = data.maxHealth;
            loaded.currentHealth = data.currentHealth;

            loaded.setCoins(data.coins);


            //  INVENTORY
            loaded.getInventory().getItems().clear();
            loaded.getInventory().getItems().addAll(data.inventory);

            //  WEAPON
            Item weapon = new Item(
                    data.equippedWeaponName,
                    Item.ItemType.WEAPON,
                    data.equippedWeaponDamage,
                    0,
                    data.equippedWeaponCharge
            );
            loaded.autoEquipWeapon(weapon);

            DifficultyManager.setDifficulty(data.difficulty);
            Player.setCurrentPlayer(loaded);


            System.out.println("Loaded save slot: " + slotName);

        }
        catch (Exception e)
        {
            System.out.println("Save file not found: " + slotName);
        }
        finally
        {
            // close reader
            if (reader != null)
            {
                try
                {
                reader.close();
                }
                catch(Exception e)
                {
    
                }
            }
        }
    }

    public static void listSaves() {
        File folder = new File(SAVE_FOLDER);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0)
        {
            System.out.println("No save files found.");
            return;
        }

        System.out.println("=== SAVE FILES ===");
        for (File f : files)
        {
            System.out.println("• " + f.getName().replace(".json", ""));
        }
    }
    public static void deleteSave(String slotName)
    {
        File saveFile = new File(getSavePath(slotName));

        if (saveFile.exists())
        {
            if (saveFile.delete())
            {
                System.out.println("Save slot '" + slotName + "' deleted successfully.");
            }
            else
            {
                System.out.println("Failed to delete save slot '" + slotName + "'.");
            }
        }
        else
        {
            System.out.println("Save slot '" + slotName + "' does not exist.");
        }
    }
}
