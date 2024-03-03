/**
 *  This class is part of the Lockdown application. 
 *  Lockdown is a very simple, text based adventure game.  
 *  
 * A Mission represents a task that a player has to do
 * in the game. It stores if the mission has started or if
 * it is finished, the time counter and the name of an item 
 * around of which the mission revolves. 
 * This class has the public methods that print
 * and verify a mission
 * 
 * @author  Dariana Dorin
 * @version november 2020
 */
public class Mission
{
    static final int START_TIME = 8;
    static final int MAX_TIME = 22;
    
    private int time;
    private boolean started;
    private boolean finished;
    
    private String essential;
    /**
     * Constructor for objects of class Mission
     * @param essential The name of the essential thing for this mission
     */
    public Mission(String essential)
    {
        time=START_TIME;
        started=false;
        finished=false;
        this.essential = essential;
    } 
    
    /**
     * Get the essential thing
     * @return The name of the essential thing in this mission
     */
    public String getEssential()
    {
        return essential;
    }

    /**
     * Increment to the mission time counter by one
     */
    public void addTime()
    {
        time++;
    }

    /**
     * Get if the mission is finished
     * @return true if the mission is finished, or false otherwise
     */
    public boolean getFinished()
    {
        return finished;
    }   

     /**
     * Get if the mission has started
     * @return true if the mission has started, or false otherwise
     */
    public boolean getStarted()
    {
        return started;
    }  

    /**
     * Marks the mission as started
     */
    public void setStarted()
    {
        started = true;
    }

    /**
     * Get the time counter
     * @return the number stored by the time counter
     */
    public int getTime()
    {
        return time;
    }

    /**
     * Print out the purpose message for a player/ the mission of the game to win.
     * @param player The player for which the mission is created
     */
    public void gamePurpose(Player player)
    {
        setStarted(); 

        player.setNeeds(player.getRoom().getItem(essential).getRandomObjectOut().getName());

        print(player);
    }

    /**
     * Prints the mission of a player
     * @param player The player attemting the mission
     */
    public void print(Player player)
    {
        System.out.println(">>>You need " + player.getNeeds() + " !");
        System.out.println(">>>Your fridge needs to be full so you can go to sleep!");
    }

    /**
     * Get if the time available has passed by checking the 
     * counter with the maximum time allowed
     * @return true if there is no more time or false otherwise
     */
    public boolean timeToQuit()
    {
        
        if(time>=MAX_TIME) 
        {
            System.out.println("It is past 22 o'clock and you are not sleeping! You lost the game!");
            return true;
        }
        return false;
    }
    
    /**
     * Verify if the mission was compleated 
     * and nothing else was changed from the begining 
     */
    public void verify(Player player)
    {
        if(player.getRoom().getItem(essential)!=null )
        {
            if(player.getRoom().getItem(essential).hasObject(player.getNeeds()))
            {
                player.setNeeds("to win the game");
                finished=true;;
            }
            if(finished)
                for(Food food : Food.values())
                    if(!player.getRoom().getItem(essential).hasObject(food.toString().toLowerCase()))
                    {
                        player.setNeeds(food.toString().toLowerCase());
                        System.out.println("Atention, there is no " + player.getNeeds() + " in the fridge no more!\n");
                        System.out.println("Even if you got what you needed, the fridge needs to remain full!\n");
                        finished=false;
                        return;
                    }
        }
    }
    /**
     * Print out the key steps in finishing this mission
     */
    public void printHints()
    {
        System.out.println("Well done! Because you showed love to everybody, here are some tips to win the game!");
        System.out.println("You need your fridge to be full but something is missing.");
        System.out.println("The local market has only vegetables so it is not helpful.");
        System.out.println("If you go in the city, chances are that some pandemic authorities will ask for your id or a affidavit.");
        System.out.println("Be prepeared, don't forget those! They are in the hall inside the drawer.");
        System.out.println("Pick what you need from the supermarket, come home and drop that in the fridge.");
        System.out.println("Time is precious so dont waste it on anything else.");
        System.out.println("Go to sleep as early as possible, but make sure you have a bed to sleep on in the room.");
    }
}
