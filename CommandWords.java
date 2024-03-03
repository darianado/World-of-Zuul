import java.util.HashMap;
/**
 * This class is part of the "Lockdown" application. 
 * "Lockdown" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes and Dariana Dorin
 * @version november2020
 */

public class CommandWords
{
    private HashMap< CommandWord, Command> commands;   // A mapping between a command word and the CommandWord
    
    /**

     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        commands = new HashMap<>();
    }
    
    public void put(CommandWord commandWord, Command command)
    {
        commands.put(commandWord,command);
    }
    
    public Command getCommand(CommandWord commandWord)
    {
        return commands.get(commandWord);
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord correspondng to commandWord, or UNKNOWN if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        for(CommandWord cw : CommandWord.values())
            if(cw.toString().equals(commandWord))
                return cw;
        return CommandWord.UNKNOWN;
    }
}
