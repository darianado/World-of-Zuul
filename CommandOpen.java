/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type open
 * in the game. It processes a player and a mission.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandOpen extends Command
{
    private Player player;
    private Mission mission;
    /**
     * Constructor for objects of class CommandOpen
     * Initialise the player and the mission on which the command is executed
     */
    public CommandOpen(Player player, Mission mission)
    {
        this.player = player;
        this.mission = mission;
    }
    
    /**
     * Try to open an item or print an error message
     * Can activate the mission
     */
    public void run()
    {
        if(!hasSecondWord())   //we dont know what to open
            System.out.println("Open what?");

        else{    
            String item = getSecondWord();

            if(player.getRoom().getItem(item)!=null)  
                if(!player.getRoom().getItem(item).isOpen())
                {
                    player.getRoom().getItem(item).open();
                    System.out.println("You opened the " + player.getRoom().getItem(item).getName()+ ".");
                    
                    if(item.equals(mission.getEssential())) 
                        if(!mission.getStarted()) //opening the fridge the first time starts the game purpose 
                            mission.gamePurpose(player);
                            
                    System.out.println(player.getRoom().getItem(item).stringInside());
                }
                else System.out.println("The " + item + " is already opened.\n" + player.getRoom().getItem(item).stringInside());
            else System.out.println("There is no " + item +" here! Try again!");
        }
    }
}
