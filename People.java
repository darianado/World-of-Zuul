import java.util.HashSet;
/**
 * 
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * An object of class People stores a list of objects 
 * of type Person.
 * It is used to keep track of all the people that
 * move freely, in a random way, through the game.
 * 
 * 
 * @author  Dariana Dorin
 * @version november 2020
 */
public class People
{
    private HashSet<Person> people;

    /**
     * Constructor for objects of class People, initialise the list of people
     */
    public People()
    {
        people = new HashSet<>();
    }

    /**
     * Get if the list has any people or not
     * @return false if the list is empty
     */
    public boolean hasPeople()
    {
        return !people.isEmpty();
    }
    
    /**
     * Add a person to the list by a name and the automatic need of love
     * @param name The name of the person to be created and added to the list
     */
    public void addPerson(String name)
    {
        people.add(new Person(name,"love"));
    }
    
    /**
     * Move all the people randomply in a set of rooms
     * @param rooms The set of rooms in which the people are put
     */
    public void moveRandom(Rooms rooms)
    {
        for(Person person : people)
            rooms.moveToRandomRoom(person);
    }
    
    /**
     * Removes a person from the list
     */
    public void removePerson(Person person)
    {
        people.remove(person);
    }
    
}
