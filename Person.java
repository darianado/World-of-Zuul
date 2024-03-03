/**
 * Class Person - a person in an adventure game
 * 
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 * 
 * A "Person" represents one person in the scenery of the game,
 * that is represented by a name and a need in this game.
 *
 * @author Dariana Dorin
 * @version november 2020
 */
public class Person
{
    private String name;
    private String needs;

    /**
     * Constructor for objects of class Person.
     * Creates a person with a name and with the name of something he needs.
     * @param name The name of the player
     * @param needs The name of the thing he needs
     */
    public Person(String name, String needs)
    {
        this.name = name;
        this.needs = needs;
    }
   
    /**
     * Get the name of the person
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the name of the thing needed by him
     * @return what he needs
     */
    public String getNeeds()
    {
        return needs;
    }
    
    /**
     * Set the name of the thing needed by him
     * @param needs What the person needs
     */
    public void setNeeds(String needs)
    {
        this.needs = needs;
    }
}
