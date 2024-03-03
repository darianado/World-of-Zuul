import java.util.HashSet;
import java.util.Stack;
/**
 * Class Player - a person that is the player in an adventure game.
 * 
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 * 
 * A "Player" represents one person of subclass Person in the scenery of the game,
 * that is represented by a name, a need in this game, a string of items that he carries
 * and his power (maximum weight that he can carry). A Player also keeps
 * the current room he is in and a stack of the last rooms he hass been through
 *
 * @author Dariana Dorin
 * @version november 2020
 */
public class Player extends Person
{
    static final int MAX_WEIGHT = 50;
    
    private Room currentRoom;
    private Stack<Room> itinerary;
    private HashSet<Item> itemsCarried;   //list of all the items he carries
    private int weightPower = MAX_WEIGHT;  //keeps track of how much is left of the carrying power
    
    /**
     * Constructor for objects of class Player, subclass of class Person.
     * Creates a player with a name and with the name of something he needs.
     * At the beginning he doesn't carries anything.
     * @param name The name of the player
     * @param needs The name of the thing he needs
     */
    public Player(String name, String needs, Room room)
    {
        super(name,needs);
        currentRoom = room;
        itemsCarried = new HashSet<>();
        itinerary= new Stack<>();
    }
    
    //accessor methods
    /**
     * Get if the player is in his first room
     * @return true if there are no rooms he has been before, or false otherwise
     */
     public boolean firstRoom() 
    {
        return itinerary.empty();
    }
    
    /**
     * Get the room he is currently in
     * @return the room the player is in now
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * Get how much more weight the player can carry
     * @return the remaining power
     */
    public int getWeightPower()
    {
        return weightPower;
    }
    
    /**
     * Get a carried item by its name or null if the player dosn't carry any item with that name
     * @param name The name of the sought item 
     * @return the item with that name 
     */
    public Item itemCarried(String itemName)
    {
        for(Item item : itemsCarried)
            if(item.getName().equals(itemName)) return item;
        return null;
    }
    
    /**
     * Get a string with all the items carried or a string saying he dosn't have any
     * @return items carried
     */
    public String itemsCarriedString()
    {
       if(itemsCarried.isEmpty()) return "You are not carrying anything!";
       else{ 
            String string = "You are now carrying: ";
            for(Item item : itemsCarried)
            {
                string = string + item.getName()+ " ";
            }
            return string;
        }
    }
    
    
    //mutator methods
    
    
    /**
     * Decrement the power by a given number or print error message
     * @param number How much to decremet the power
     */
    public void decrementWeightPower(int number)
    {
        if(weightPower - number >= 0)
            weightPower -= number;
        else System.out.println("ERROR the player can't have negative power!");
    }
    
    /**
     * Increment the power by a given number or print error message
     * @param number How much to increment the power
     */
    public void incrementWeightPower(int number)
    {
        if(weightPower + number <= MAX_WEIGHT)
            weightPower += number;
        else System.out.println("ERROR the player gets to carry more than he can!");
    }
    
    /**
     * Give the player an item to carry
     * @param item The item to be carried
     */
    public void addItemToCarry(Item item)
    {
        itemsCarried.add(item);
    }
    
    /**
     * Remove an item with a specific name from the carried ones
     * @param name The name of the item to be removed
     */
    public void removeItemNamed(String name)
    {
       itemsCarried.remove(itemCarried(name));
    }
    
    /**
     * Changes the room the player is in, adding the current one as the last visited
     * @param nextRoom The room to become the new current room of the player
     */
    public void setNextRoom(Room nextRoom)
    {
       itinerary.push(currentRoom);
       currentRoom = nextRoom;
    }
    
    /**
     * Moves the player to one room before the current one
     */
    public void goBack()
    {
       currentRoom = itinerary.pop() ;
    }
    
}
