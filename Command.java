/**
 * This class is part of the Lockdown application. 
 * Lockdown is a very simple, text based adventure game. 
 *
 * This class holds information about a command that was issued by the user.
 * A command can consist of two strings: a second
 * word and a third word of the users input and a boolean that
 * keeps track is the game needs to end or not.
 * 
 * The way this is used is: Command is an abstract superclas for each 
 * of the valid commands executions in this game. 
 * It runs a main method of a specific subclass for every command
 * word identified in the parser (a list is initialised in the Game class)
 * 
 * @author  Michael Kölling and David J. Barnes and Dariana Dorin
 * @version november 2020
 */

public abstract class Command
{
    private String secondWord;
    private String thirdWord;
    private boolean wantToQuit;

    /**
     * Set that the game should keep running and not quit
     */
    public void setNotQuit()
    {
        wantToQuit=false;
    }
    
    /**
     * Set that the game should end
     */
    public void setQuit()
    {
        wantToQuit = true;
    }
    
    /**
     * Get if the game shoul be ended
     * @return true if the game sould be quited or false otherwise
     */
    public boolean getQuit()
    {
        return wantToQuit;
    }
    
    /**
     * Get second word of the command or null
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * Declare the second word of this command
     * @param word The word to be set as the second word of the command
     */
    public void setSecondWord(String word)
    {
        secondWord = word;
    }
    
     /**
     * Declare the third word of this command
     * @param word The word to be set as the third word of the command
     */
    public void setThirdWord(String word)
    {
        thirdWord = word;
    }
    
    /**
     * Get third word of the command or null
     * @return The third word of this command. Returns null if there was no
     * third word.
     */
    public String getThirdWord()
    {
        return thirdWord;
    }
   
    /**
     * Get if the command has a second word
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    /**
     * Get if the command has a third word
     * @return true if the command has a third word.
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
    
    public abstract void run();
}

