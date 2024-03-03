/**
 * Representations for all the valid command words for the game.
 * 
 * @author  Michael Kölling and David J. Barnes and Dariana Dorin
 * @version november 2020
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognized commands.
    GO("go"), 

    QUIT("quit"), 

    HELP("help"), 

    UNKNOWN(""), 

    PICK("pick"), 

    BACK("back"), 

    OPEN("open"), 

    SLEEP("sleep"), 

    DROP("drop"),

    SHOW("show");

    // The command string.
    private String commandString;

    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
    
    /**
     * Print out all the valid commands
     */
    static void printAll()
    {
        for(CommandWord command : CommandWord.values())
            if(command != CommandWord.UNKNOWN) 
                System.out.print(command.toString() + "  ");
        System.out.println();
    }
    
}