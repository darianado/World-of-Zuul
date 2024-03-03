import java.util.Scanner;
/**
 *  This class is part of the "Lockdown" application. 
 * "Lockdown" is a very simple, text based adventure game.
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a three word command. It returns the command
 * as an object of class Command (one of the specific subclass).
 *
 * @author  Michael Kölling and David J. Barnes and Dariana Dorin
 * @version november 2020
 */
public class Parser 
{
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        reader = new Scanner(System.in);
    }

    /**
     * Get the next command from the terminal
     * @return The next command from the user.
     */
    public Command getCommand(CommandWords allCommands) 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to three words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next().trim().toLowerCase();      // get first word in all lower case
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next().trim().toLowerCase();      // get second word in all lower case
                if(tokenizer.hasNext())
                    word3 = tokenizer.next().trim().toLowerCase();      // get second word in all lower case
                // note: we just ignore the rest of the input line.
            }
        }

        Command command = allCommands.getCommand(allCommands.getCommandWord(word1));
        command.setSecondWord(word2);
        command.setThirdWord(word3);
        command.setNotQuit();
        return command;

    }

    /**
     * Get the entire input of the user
     * @return the users input
     */
    public String getInputLine()
    {
        System.out.print("> ");     // print prompt
        return reader.nextLine();
    }
}
