package calculatemaxt;

import java.util.*;

/**
 * Herd objects represent the herds currently in the CalculateMaxT system.
 */
public class Herd 
{
    //attributes
    
    /**
     * the name and ID of the herd
     */
    private String title;
    private String uniqueID;
    
    /**
     * all the linked Cow objects
     */
    private Collection<Cow> cows; 

    //constructor
   
    /**
     * Initialises a new Herd object with the given title
     * and ID.
     *
     * @param aTitle the title of the herd
     * @param aUniqueID the ID# of the herd
     */
    Herd(String aTitle, String aUniqueID)
    {
        title = aTitle;
        uniqueID = aUniqueID;
        cows = new HashSet<>();
    }
   
   
    //public protocol
   
    /**
     * Returns the title of this herd.
     *
     * @return title
     */
    public String getTitle()
    {
        return title;
    }
   
    /**
     * Returns the unique ID of this herd.
     *
     * @return uniqueID
     */
    public String getUniqueID()
    {
        return uniqueID;
    }
   
    /**
     * Returns a string representation of this herd's title and ID.
     *
     * @return a String object representing the receiver
     */
    public String toString()
    {
        return title + "? " + uniqueID;
    }
       
    /**
      * Returns all the cows on this farm.
      *
      * @return a collection of all the linked Cow objects
      */
    Collection<Cow> getCows()
    {
        return cows;
    }
   
   
    /**
     * Adds the cow to those in this herd. 
     * A reference to aCow is recorded.
     *
     * @param aCow a cow
     */
    void add(Cow aCow)
    {
        cows.add(aCow);
    }

    /**
     * Removes the cow from this herd.
     * The reference to aCow is removed.
     *
     * @param aCow a cow
     */
    void remove(Cow aCow)
    {
        cows.remove(aCow);
    }
    
}