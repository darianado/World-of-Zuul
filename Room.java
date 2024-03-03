import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * A "Room" represents one location in the scenery of the game and 
 * is represented by a description.  
 * It is connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * It can store multiple items as well as people.
 * A Room can be a control one, such that the player should do additional
 * stuff here so he can advance in the game.
 * 
 * @author  Michael Kölling, David J. Barnes, Dariana Dorin
 * @version november 2020
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;  // stores exits of this room.
    private HashSet<Item> items; // stores items in this room.
    private HashSet<Person> people; // stores people in this room.
    private boolean controlRoom;  //if the room has restrictions on moving forward

    /**
     * Create a room described "description". Initially, it has
     * no exits, no items and no people inside, and it 
     * is a normal room(not a control one). 
     * "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<>();
        people = new HashSet<>();
        controlRoom = false;
    }

    //mutator methods 
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Makes the room a control one
     */
    public void setControlRoom()
    {
        controlRoom = true;
    }
    
    /**
     *  Put an item in this room
     *  @param name The name of the item
     *  @param isCarriable True if the item can be picked up, false if not
     */
    public void putItem(String name, int weight)
    {
        items.add(new Item(name, weight));
    }

    /**
     *  Put an item in this room
     *  @param item The item to be added to this room
     */
    public void putItem(Item item)
    {
        items.add(item);
    }

    /**
     *  Remove an item from this room
     *  @param name The name of the item to be removed from this room
     */
    public void removeItem(String name)
    {
        items.remove(getItem(name));
    }

    /**
     * For all the items in the room, make them closed
     */
    public void closeAllItems()
    {
        for(Item item : items)
            item.close();
    }

    /**
     * Add a person in the room, with a name and a need
     * @param name The name of the person
     * @param needs What the person needs
     */
    public void addPerson(String name, String needs)
    {
        people.add(new Person(name,needs));
    }
    
    public void addPerson(Person person)
    {
        people.add(person);
    }
    
    /**
     * Remove a person from the room by her name
     * @param name The name of the person to be removed
     */
    public void removePerson(String name)
    {
        people.remove(getPerson(name));
    }

    //accessor methods
    /**
     * Get the description of the room
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return the description with all the information about the room
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String string = "You are " + description + ".\n";      
        string += getExitString() ;

        if(!items.isEmpty()) 
            string += "\n" + getItemsString();

        if(hasPeople()){
            if(controlRoom) 
                string += "\n"+"Atention! You can't advance before you give the people here what they need!";
            string += getPeopleString();
        }
        return string;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "From here you can go:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return a string describing the room's items, for example
     * "Items: fridge pen".
     * @return Details of the room's items.
     */
    private String getItemsString()
    {
        if(items.isEmpty()) 
            return "No items in here.";
        else{
            String returnString = "Items:";
            for(Item item : items) {
                returnString += " " + item.getName();
            }
            return returnString;
        }
    }

    /**
     * Return a string describing the people inside the room and what they need
     * @return Details of the people in the room.
     */
    private String getPeopleString()
    {
        String string = "";
        if(people.isEmpty()) string+="Nobody else here.";
        else  
            for(Person person : people)
                string+="\n"+"A " + person.getName() + " is here and needs " + person.getNeeds()+".";
        return string;
    }

     /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Get if the room is a control one or not
     * @return true if it is, false if not
     */
    public boolean getIfControlRoom()
    {
        return controlRoom;
    }
    
    /**
     * Get if the room has a specific person by his name
     * @param name The name of the person we are looking for
     * @return the person or null if there is no one with that name
     */
     public Person getPerson(String name)
    {
        for(Person person : people)
            if(person.getName().equals(name)) return person;
        return null;
    }
    
    /**
     * Get if the room has any people inside
     * @return false if there is no one ar tru if there are some people
     */
    public boolean hasPeople()
    {
        if(people.isEmpty()) return false;
        return true;
    }
    
    /**
     * Get the item with a specific name from this room, return null if there is no item called like that here
     * @param itemName The name of the item we are looking for
     * @retuns the item 
     */
    public Item getItem(String itemName)
    {
        for(Item item : items)
        {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }
    
    /**
     * Get an opened item from the room that contains another specific item, or null if there is none
     * @param itemName The name of the item we are looking for insede any opened item in the room
     * @return the opened item that contains that specific item
     */
    public Item openedItemThatContains(String itemName)
    {
        for(Item item : items)
        {
            if(item.isOpen() && item.hasObject(itemName)) return item;
        }
        return null;
    }
}
