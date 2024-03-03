/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type quit
 * in the game. It can make the game end.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandQuit extends Command
{
    
   /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    public void run()
    {
        if(hasSecondWord()) {
            System.out.println("Quit what?");
        }
        else {
            setQuit();  // signal that we want to quit
        }
    }
}
