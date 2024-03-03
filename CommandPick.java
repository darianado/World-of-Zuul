/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type pick
 * in the game. It processes a player.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandPick extends Command
{
    private Player player;
    /**
     *  Constructor for objects of class CommandPick
      * Initialise the player on which the command is executed
     */
    public CommandPick(Player player)
    {
        this.player = player;
    }
    
    /**
     * Try to pick up an object either from the room or from inside 
     * an opened item in the room and print some result 
     */
    public void run()
    {
        if(!hasSecondWord()) // we dont know what to pick
        {
            System.out.println("Pick what?");
            return;
        }

        String item = getSecondWord();

        if(player.getRoom().openedItemThatContains(item) != null) //try to find the item inside the opened items in the room
        {
            if(pickItem(player.getRoom().openedItemThatContains(item).getObject(item)))    // pick the item or print message
                player.getRoom().openedItemThatContains(item).removeObject(item);         //remove the item from where it was picked up
        }

        else if(player.getRoom().getItem(item)!=null)  //try find the item in the room 
        {
            if(pickItem(player.getRoom().getItem(item)))     // pick the item or print message
                player.getRoom().removeItem(item);           //remove the item from where it was picked up
        }
        else System.out.println("There is no " + item +" here! Try again!");

    }

    /**
     * Pick an item considering its weight and the power of the player.
     * @param toPick the item to be picked up
     */
    private boolean pickItem(Item toPick)
    {
        if(toPick.getIfCanPick()) //also prints if it can't be picked
        {
            if(toPick.getWeight()<=player.getWeightPower())
            {
                player.addItemToCarry(toPick);

                player.decrementWeightPower(toPick.getWeight()) ;  //the player has less power after picking something up

                System.out.println("You picked up the " + toPick.getName()+ "!" );
               
                return true;
            }
            else 
            {
                System.out.println("You have no more power!"+player.itemsCarriedString()); 
                return false;
            }
        }
        return false;
    }
}