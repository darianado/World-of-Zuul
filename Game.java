import java.util.Random;
import java.util.HashMap;
/**
 *  This class is the main class of the Lockdown application. 
 *  Lockdown is a very simple, text based adventure game.  Users 
 *  can walk around some scenery and play around with
 *  different commnads on items and other people. 
 *  They need to find and execute a mission
 *  in a time limit in order to win.
 *  
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, the people, creates the parser, creates the player and the mission
 *  and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes and Dariana Dorin
 * @version november 2020
 */

public class Game 
{
    Random random = new Random();

    private Parser parser;
    private Player player;
    private Mission mission;
    private CommandWords allCommands;

    private Rooms rooms;
    private People people;

    /**
     * The main method that starts the aplication
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }

    /**
     * Create the game, initialise its internal map and the commands.
     */
    public Game() 
    {
        parser = new Parser();
        allCommands = new CommandWords();
        createRooms();
        createPeople();
        mission= new Mission("fridge");
        createCommands();
    }

    /**
     * Create all the rooms, link their exits together and adds the items in the.
     */
    private void createRooms()
    {
        Room kitchen, bedroom, hall, localMarket, superMarket, entranceRoad;

        // create the rooms
        kitchen = new Room("in your kitchen");
        bedroom = new Room("in your bedroom");
        hall = new Room("in your home entrance hall");
        localMarket = new Room("at your town local market");
        superMarket = new Room("at the city Supermarket");
        entranceRoad = new Room("at the main entrance road in the city");

        // initialise room exits

        kitchen.setExit("hall", hall);

        bedroom.setExit("downstairs", hall);

        hall.setExit("kitchen", kitchen);
        hall.setExit("shopping", localMarket);
        hall.setExit("outside", entranceRoad);
        hall.setExit("upstairs", bedroom);

        entranceRoad.setExit("shopping", superMarket);
        entranceRoad.setControlRoom();

        superMarket.setExit("home", hall);

        localMarket.setExit("home", hall);

        //initialise the items in the rooms

        kitchen.putItem("fridge",300);
        for(Food food : Food.values())
            kitchen.getItem("fridge").putInside(food.toString().toLowerCase(),20); 

        kitchen.putItem("cabinet",300);
        kitchen.getItem("cabinet").putInside("forks",30);
        kitchen.getItem("cabinet").putInside("spoons",30);
        kitchen.getItem("cabinet").putInside("pan",10);

        hall.putItem("hanger",150);
        hall.putItem("drawer",111);
        hall.getItem("drawer").putInside("affidavit",5);
        hall.getItem("drawer").putInside("id",5);
        hall.getItem("drawer").putInside("gloves",5);
        hall.getItem("drawer").putInside("bag",11);

        localMarket.putItem("door",300);
        localMarket.getItem("door").putInside("apple",3);
        localMarket.getItem("door").putInside("tomato",5);
        localMarket.getItem("door").putInside("strawberries",7);
        localMarket.getItem("door").putInside("pickles",5);

        superMarket.putItem("door",300);
        for(Food food : Food.values())
            superMarket.getItem("door").putInside(food.toString().toLowerCase(),20);

        bedroom.putItem("bed",300);
        bedroom.putItem("nightstand",111);

        //initialise the set of rooms of the house
        rooms= new Rooms();
        rooms.addRoom(kitchen);
        rooms.addRoom(hall);
        rooms.addRoom(bedroom);
        rooms.addRoom(localMarket);
        rooms.addRoom(superMarket);

        
        setPlayerIn(kitchen); // create the player in the kitchen
    }
    
    private void createPeople()
    {
        //initialise the people, all need love
        people = new People();
        people.addPerson("mom");
        people.addPerson("dad");
        people.addPerson("stranger");
        //player has love
        player.addItemToCarry(new Item("love",0));
    }

    /**
     * Creates the player with the name given by the user in the terminal and with the location
     * @param currentRoom The room the player is initialized in
     */
    private void setPlayerIn(Room room)
    {
        System.out.println("Hello! What is your name? \n");
        String name = parser.getInputLine();
        player = new Player(name, "",room);
    }

    /**
     * Initialize the correlation between command words and their execution
     */
    private void createCommands()
    {
        allCommands.put(CommandWord.GO, new CommandGo(player,mission, rooms, people));
        allCommands.put(CommandWord.BACK, new CommandBack(player,mission));
        allCommands.put(CommandWord.QUIT, new CommandQuit());
        allCommands.put(CommandWord.HELP, new CommandHelp(player,mission));
        allCommands.put(CommandWord.PICK, new CommandPick(player));
        allCommands.put(CommandWord.OPEN, new CommandOpen(player,mission));
        allCommands.put(CommandWord.DROP, new CommandDrop(player));
        allCommands.put(CommandWord.SHOW, new CommandShow(player,people));
        allCommands.put(CommandWord.SLEEP, new CommandSleep(player,mission));
        allCommands.put(CommandWord.UNKNOWN, new CommandUnknown());
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand(allCommands);
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing "+player.getName()+"!\nGood bye!");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        if(mission.timeToQuit()) 
        {
            return true;
        }
        
        command.run();
        
        if(!people.hasPeople()) //love was shown to all people
            {mission.printHints();createPeople();}
            
        return command.getQuit();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Lockdown "+ player.getName()+ "!\n");
        System.out.println("Lockdown is a new game simulating an ordinary day in a pandemic.");
        System.out.println("It's COVID-19 time and you are tired!");
        System.out.println("Start exploring and do your mission for today!\nYou need to go to sleep before your bedtime!\nNow it is 8 in the morning!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

}
