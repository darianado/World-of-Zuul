import java.util.Random;
import java.util.ArrayList;
/**
 * 
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * An object of class Rooms stores a list of rooms.
 * It is used to get random rooms for the people
 * of the game.
 * 
 * 
 * @author  Dariana Dorin
 * @version november 2020
 */

public class Rooms
{
    private ArrayList<Room> rooms;
    Random random = new Random();
    
    /**
     * Initialise the list of rooms
     */
    public Rooms()
    {
        rooms= new ArrayList<>();
    }
    
    /**
     * Add a room to the list
     * @param room The room to be added
     */
    public void addRoom(Room room)
    {
        rooms.add(room);
    }
    
    /**
     * Removes a person from all the rooms of the list
     * @param person The person to be removed
     */
    private void removePerson(Person person)
    {
        for(Room room : rooms)
        {
            if(room.getPerson(person.getName())!=null)
                room.removePerson(person.getName());
        }
    }
    
    /**
     * Moves a person to a new random room from the list
     * @param person The person to change location
     */
    public void moveToRandomRoom(Person person)
    {
        removePerson(person);
        int rn= random.nextInt(rooms.size());
        rooms.get(rn).addPerson(person);
    }
}
