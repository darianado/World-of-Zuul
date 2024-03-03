
/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type back
 * in the game. It processes a player and a mission.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandBack extends Command
{
    private Player player;
    private Mission mission;
    /**
     * Constructor for objects of class CommandBack
     * Initialise the player on which the command is executed and a mission
     */
    public CommandBack(Player player,Mission mission)
    {
        this.player = player;
        this.mission = mission;
    }

    /**
     * Goes back to the room the player has been before the current one
     * and prints its details
     * or print message that he is still in the first room
     */

    public void run()
    {
        if(player.firstRoom()) 
            System.out.println("You are in the first room! No place to go back!");
        else 
        {
            mission.addTime();
            mission.verify(player);
            player.goBack();
            System.out.println(player.getRoom().getLongDescription());
        }

    }
}
