import java.util.Random;
/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class is responsible of executing a command of type go
 * in the game. It processes a player, a mission and a set of rooms.
 * 
 * @author  Dariana Dorin
 * @version november2020
 */
public class CommandGo extends Command
{
    private Player player;
    private Mission mission;
    private Rooms roomSet;
    private People people;
    /**
     * Constructor for objects of class CommandGo
     * Initialise the player on which the command is executed, a mission, a set of rooms and some people
     */
    public CommandGo(Player player,Mission mission, Rooms roomSet,People people)
    {
        this.player=player;
        this.mission = mission;
        this.roomSet = roomSet;
        this.people = people;
    }

    /** 
     * Try to go to one direction. If there is an exit and the 
     * control rooms are solved, go to the new
     * room, also ugrading the mission,
     * otherwise print some error message.
     */
    public void run() 
    {
        if(!hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = getSecondWord();     // Try to leave current room.

        if (player.getRoom().getExit(direction) == null) {
            System.out.println("You can't go "+direction+ " from here! Try again.");
        }
        else if(player.getRoom().getIfControlRoom() && player.getRoom().hasPeople())
            System.out.println("You can't go further until you satisfy the people here!");
        else {
            mission.verify(player);  //before leaving a room, we verify the mission status
            mission.addTime();

            wantToGo(player.getRoom().getExit(direction));
        }
    }

    /**
     * * Prints the detalis of the new room.
     * If it is the case prepears a control room.
     * Close all items in the cuurent room.
     * Moves the people randomly
     * @param nextRoom The room to go next
     */
    private void wantToGo( Room nextRoom )
    {
        player.getRoom().closeAllItems();       //after leaving a room, all items get closed

        people.moveRandom(roomSet);

        if(nextRoom.getIfControlRoom() && !nextRoom.hasPeople())  //set up the control room
        {
            putRandomPeopleControl(nextRoom);
        }

        player.setNextRoom(nextRoom);
        System.out.println(nextRoom.getLongDescription());
    }

    /**
     * Put some control people in a room, each with a 50% chance to be there
     * @param room The room to have the control people
     */
    private void putRandomPeopleControl(Room room)
    {
        Random random = new Random();
        int rn = random.nextInt(100)+1;
        if(rn%2==0)                                             //50% chance for an officer to be there
            room.addPerson("officer", "id");
        rn = random.nextInt(100)+1;
        if(rn%2==0)                                              //50% chance for an policeman to be there
            room.addPerson("policeman", "affidavit");
    }

}
