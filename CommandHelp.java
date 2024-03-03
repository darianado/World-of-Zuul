/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type help
 * in the game. It processes a player and a mission.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandHelp extends Command
{
    private Player player;
    private Mission mission;
    /**
     * Constructor for objects of class CommandHelp
     *  Initialise the player on which the command is executed and a mission
     */
    public CommandHelp(Player player, Mission mission)
    {
        this.player=player;
        this.mission = mission;
    }
    
    /**
     * Print out some help information.
     */
    public void run() 
    {
        
        System.out.println("You need to sleep but something is not in its place");

        if(mission.getStarted() && !mission.getFinished()) 
            mission.print(player);
        else 
            System.out.println("Figure out what to do!\n");

        System.out.println("It is " + mission.getTime() + "o'clock!");
        System.out.println(player.getRoom().getLongDescription());
        System.out.println(player.itemsCarriedString());
        System.out.println();
        System.out.println("Your command words are:");
        CommandWord.printAll();
    }

    
}
