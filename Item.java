import java.util.HashSet;
import java.util.Random;
/**
 * Class Item - an item in a room in an adventure game
 *
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * An "Item" represents one object in the game. It is represented
 * by a name and a weight, can carry other items inside and can be
 * open or close during the game.
 * 
 * @author  Michael Kölling, David J. Barnes, Dariana Dorin
 * @version november 2020
 */
public class Item
{
    private Random random = new Random();

    private String name;
    private int weight;
    
    private HashSet<Item> inside; // list of other distinct items that the item can have inside
    private boolean opened;     //keeps track if the item was opened

    /**
     * Creates an item with a name and a weight.
     * At the begining the item is not opened and 
     * has nothing inside.
     * 
     *@param name The name of the item
     *@param weight The weight of the item
     * 
     */
    public Item(String name, int weight)
    {
        this.name = name;
        this.weight = weight;
        opened = false;
        inside = new HashSet<>();
    }
    
    //mutator methods
    /**
     * Opens the item
     */
    public void open()
    {
        opened = true;
    }
    
    /**
     * Closes the item
     */
    public void close()
    {
        opened = false;
    }
    
     /**
     * Puts an object (another item) inside the item
     * 
     * @param object The name of the object to be put inside
     * @param weight The weight of the item to be put inside
     * 
     */
    public void putInside(String object,int weight)
    {
        inside.add(new Item(object,weight));
    }

    /**
     * Puts an object (another item) inside the item
     * 
     * @param item The item to be put inside
     */
    public void putInside(Item item)
    {
        inside.add(item);
    }
    
    /**
     * Removes an item by its name from inside the item
     * 
     * @param name The name of the item to be removed
     */
    public void removeObject(String name)
    {
        inside.remove(getObject(name));
    }
    
    
    //accessor methods
    
    /**
     * Get if the item is opened or close
     * 
     * @return if the item is opened or not
     */
    public boolean isOpen()
    {
        return opened;
    }
    
    /**
     * Get the name of the item
     * @return  the name of the item
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the weight of the item
     * @return  the weight of the item
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Get the number of items inside
     * @return  the number of items inside
     */
    public int getNumberObjectsInside()
    {
        return inside.size();
    }
    
    /**
     * Get if the item can be picked up based on the power of the player.
     * Print message if not.
     * 
     * @retun true if the item can be picked up or false if not
     */
    public boolean getIfCanPick()
    {
        if(weight<=Player.MAX_WEIGHT)
            return true;
        else{
        System.out.println("You can't pick the " + getName()+".");
        return false;}
    }
    
     /**
     * Get if the item can be opened considering whether it has other items inside it or not.
     * Print message if not.
     * 
     * @retun true if the item can be opened or false if not
     */
    public boolean getIfCanOpen()
    {
        if(!inside.isEmpty())
            return true;
            
        System.out.println("You can't open the " + getName()+".");
        return false;
    }
    
    /**
     * Get if the item has another item inside by its name
     * 
     * @param itemInside The name of the item we are looking for inside
     * @return true if an item with that name is found or false if not
     */
    public boolean hasObject(String itemInside)
    {
        for(Item object : inside)
        {
            if(object.getName().equals(itemInside)) 
                return true;
        }
        return false;
    }
    
    /**
     * Get the item from inside by its name
     * 
     * @param itemInside The name of the item we are looking for inside
     * @return the item or null if there is no item inside with that name
     */
    public Item getObject(String itemInside)
    {
        for(Item object : inside)
        {
            if(object.getName().equals(itemInside)) 
                return object;
        }
        return null;
    }

    /**
     * Get a string with all the items from inside the item or a message
     * telling that there is nothing inside
     * 
     * @return a string with what is inside
     */
    public String stringInside()
    {
        if(inside.isEmpty()) 
            return "Nothing inside!";
        else
        {
            String insideString = "Inside there are:";
            for(Item object : inside)
                insideString += " " + object.getName();
            return insideString;
        }
    }
    
    /**
     * Get a random item from inside and remove it
     * 
     * @return one random item out from inside
     */
    public Item getRandomObjectOut()
    {
        int rn = random.nextInt(inside.size());
        int index=0;
        for(Item object : inside)
        {
            if(index==rn) {
                inside.remove(object); 
                return object;
            }
            index++;
        }

        return null;

    }

}
