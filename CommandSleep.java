
/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type sleep
 * in the game. It processes a player and a mission
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandSleep extends Command
{
    private Player player;
    private Mission mission;
    
    /**
     * Constructor for objects of class CommandSleep
     * Initialise the player and the mission on which the command is executed
     * 
     */
    public CommandSleep(Player player, Mission mission)
    {
        this.player=player;
        this.mission = mission;
    }
    
    /**
     * Try to sleep or print an error messange
     * Ends the game is the mission is complete
     */
    public void run()
    {
        if(player.getRoom().getItem("bed")!=null)
            if(mission.getFinished())
            { 
                System.out.print("yay all good");
                setQuit();
            }
            else {
                if(mission.getStarted()) 
                    mission.print(player);
            }
        else System.out.println("No bed in the room to sleep on!");
    }

}
