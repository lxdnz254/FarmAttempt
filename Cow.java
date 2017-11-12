package calculatemaxt;

import java.util.*;

/**
 * Cow objects represent the cows currently in the CalculateMaxT system.
 */
public class Cow 
{
    //attributes
    
    /**
     * the tag ID of the cow
     */
    private String tagID;
    
    //links
    
    /**
     * the linked Herd object
     */
    private Herd herd; 
    
    /**
     * the linked Farm object
     */
//    private Farm farm; 
    
    /**
     * all the linked MilkTaking objects
     */
    private Collection<MilkTaking> milkTakings; 
    
    //constructor
   
    /**
     * Initialises a new Cow object with the given ID.
     *
     * @param aTagID the ID# of the cow
     */
    Cow(String aTagID)
    {
        tagID = aTagID;
    }
   
    //public protocol
   
    /**
     * Returns the ID of this cow.
     *
     * @return tagID
     */
    public String getTagID()
    {
        return tagID;
    }
   
    //package protocol
    
    /**
     * Returns the herd this cow is in.
     *
     * @return the linked Herd object
     */
    Herd getHerd()
    {
        return herd;
    }
    
       /**
     * Returns the milk takings for this cow.
     *
     * @return a collection of all the linked MilkTakings objects
     */
    Collection<MilkTaking> getMilkTakings()
    {
        return milkTakings;
    }

    /**
     * Adds the milk takings to those from this cow. 
     * A reference to aMilkTaking is recorded.
     *
     * @param aMilkTaking a milk taking
     */
    void add(MilkTaking aMilkTaking)
    {
        milkTakings.add(aMilkTaking);
    }

    /**
     * Removes the milk takings from this cow.
     * The reference to aMilkTaking is removed.
     *
     * @param aMilkTaking a milk taking
     */
    void remove(MilkTaking aMilkTaking)
    {
        milkTakings.remove(aMilkTaking);
    }

    /**
     * Records the birth of this cow.
     * A reference to aHerd is recorded, aHerd records a reference to the receiver.
     * ??A reference to aFarm is recorded, and aFarm records a reference to the receiver.??
     *
     * @param aHerd a herd
     * originally param aFarm a farm
     */
//    void birth(Herd aHerd) // originally (Herd aHerd, Farm aFarm)
//    {
//        herd = aHerd;
//        herd.add(this);
//        farm = aFarm;
//        farm.add(this);
//    }
    
    
    /**
     * Records the death of this cow.
     *
     * All links with the receiver are removed.
     */
//    void death()
//    {
//        herd.remove(this);
//        herd = null;
//        farm.remove(this);
//        farm = null;
//        milkTakings = new HashSet<MilkTakings>();
//    }

    
}