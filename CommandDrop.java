/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type drop
 * in the game. It processes a player.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandDrop extends Command
{
    private Player player;

    /**
     * Initialise the player on which the command is executed
     */
    public CommandDrop(Player player)
    {
        this.player=player;
    }

    /**
     * Try to drop an item, either in the room or inside another object
     * or prin a message for any error thet may occur
     * @param command The commnad startin with 
     *              the command word "drop"
     */
    public void run()
    {
        if(hasSecondWord())
        {
            if(player.itemCarried(getSecondWord())!=null)
            {
                if(hasThirdWord()) //player wants to drop the item inside another item
                {
                    if(player.getRoom().getItem(getThirdWord())!=null)
                    {
                        if(player.getRoom().getItem(getThirdWord()).isOpen())
                        {
                            dropInside();
                        }
                        else
                            System.out.println("The " + getThirdWord() + " is not opened to drop the " 
                                + getSecondWord() + " inside.");
                    } 
                    else System.out.println("The " + getThirdWord() + " is not in the room to drop the " 
                            + getSecondWord() + " inside.");
                }
                else //drop the item in the room
                {
                    dropInRoom();
                }
            }
            else 
                System.out.println("You are not carrying the " + getSecondWord() +"!\n"+ player.itemsCarriedString());
        }
        else System.out.println("Drop what?");
    }

    /**
     * Drop an item inside anoter item
     * @param command The command in wich the second word 
     *                  is the item to be droped and 
     *                  the third represents inside of what
     */
    private void dropInside()
    {
        player.getRoom().getItem(getThirdWord()).putInside(player.itemCarried(getSecondWord())); //drop the item

        player.removeItemNamed(getSecondWord());  //dont carry the item no more

        System.out.println("You droped the "+getSecondWord()+ " in the "
            +getThirdWord()+".");

        player.incrementWeightPower(player.getRoom().getItem(getThirdWord()).getObject(getSecondWord()).getWeight()) ;
                                // after dropping something, the player has more power

    }

    /**
     * Drop an intem inside the current room
     * @param command The command that has as 
     *                  a second word the item to be droped
     */
    private void dropInRoom()
    {
        player.getRoom().putItem(player.itemCarried(getSecondWord())); //drop item

        player.removeItemNamed(getSecondWord()); //dont carry the item no more

        System.out.println("You droped the "+getSecondWord()+ " in here!");

        player.incrementWeightPower(player.getRoom().getItem(getSecondWord()).getWeight()) ; // after droping something, the player has more power
    }
}
