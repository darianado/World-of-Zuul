
/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing any 
 * unknown (not valid) command
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandUnknown extends Command
{
    /**
     * Constructor for objects of class CommandUnknown
     */
    public CommandUnknown()
    {
        
    }
    public void run()
    {
        System.out.println("I don't know what you mean, no command from the following was recognized...");
        CommandWord.printAll();
    }

}
