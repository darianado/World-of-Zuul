/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type show
 * in the game. It processes a player and a mission.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandShow extends Command
{
    private Player player;
    private People people;
    
    /**
     * Initialise the player and the mission on which the command is executed
     */
    public CommandShow(Player player,People people)
    {
        this.player=player;
        this.people = people;
    }
    
     /**
     * Try to give an item to a person or print a message
     * for any mistake that may occur
     * @param command The command with the second word the 
     *                  name of the item and the third the person
     */
    public void run()
    {
        if(hasSecondWord())
        {
            if(player.itemCarried(getSecondWord())!=null)
            {
                if(hasThirdWord())
                {
                    if(player.getRoom().getPerson(getThirdWord())!=null)
                    {
                        if(player.getRoom().getPerson(getThirdWord()).getNeeds().equals(getSecondWord())) //the object given its wht he needs
                        {   
                            System.out.println("Well done! The " + getThirdWord() + " is satified!"); 
                            // if(getThirdWord().equals(mission.getGod().getName()))  //satisfying the god of the mission prints the hints
                                // mission.printHints();
                            // else
                                people.removePerson(player.getRoom().getPerson(getThirdWord()));
                                player.getRoom().removePerson(getThirdWord());  //people leave after being satified
                                
                        }
                        else System.out.println("The " + getThirdWord() + 
                                " doesn't need your " +getSecondWord() + ". He won't take it");
                    }

                    else
                        System.out.println("The " + getThirdWord() + " is not here!" );
                }
                else 
                    System.out.println("Show " + getSecondWord() + " to who?");
            }
            else 
                System.out.println("You are not carrying the " + getSecondWord() +"!\n"+ player.itemsCarriedString());
        }
        else System.out.println("Show what?");
    }

}
