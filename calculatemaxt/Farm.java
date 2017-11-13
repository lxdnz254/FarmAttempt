package calculatemaxt;

import java.util.*;

/**
 * Farm objects represent the farms in the CalculateMaxT system.
 */
public class Farm
{
    //attributes
    
    /**
     * the name, location, and ID of the farm
     */
    private String name; 
    private String location; 
    private String farmID; 

    /**
     * all the linked Herd objects
     */
    private Collection<Herd> herds; 
//    private ArrayList<Herd> herds;
   
   
    //constructor
    
   /**
    * Initialises a new Farm object with the given name, location, and ID.
    *
    * @param aName the name of the farm
    * @param aLocation the location of the farm
    * @param aFarmID the unique ID of the farm
    */
   Farm(String aName, String aLocation, String aFarmID)
   {
      name = aName;
      location = aLocation;
      farmID = aFarmID;
      herds = new HashSet<Herd>();
//      herds = new ArrayList<Herd>();
   }
   
    
    //public protocol
   
    /**
     * Returns the name of this farm.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }
   
    /**
     * Returns the location of this farm.
     *
     * @return location
     */
    public String getLocation()
    {
        return location;
    }
       
    /**
     * Returns the ID of this farm.
     *
     * @return farmID
     */
    public String getFarmID()
    {
        return farmID;
    }
       
    /**
     * Returns a string representation of this farm's name, location, and ID.
     *
     * @return a String object representing the receiver
     */
    public String toString()
    {
        return name + ": " + location + ": " + farmID;
    }
    
   
    //package protocol
   
    /**
      * Returns all the herds on this farm.
      *
      * @return a collection of all the linked Herd objects
      */
    Collection<Herd> getHerds()
//    ArrayList<Herd> getHerds()
    {
        return herds;
    }
   
   
    /**
     * Adds the herd to those on this farm. 
     * A reference to aHerd is recorded.
     *
     * @param aHerd a herd
     */
    void add(Herd aHerd)
    {
        herds.add(aHerd);
    }

    /**
     * Removes the herd from this farm.
     * The reference to aHerd is removed.
     *
     * @param aHerd a herd
     */
    void remove(Herd aHerd)
    {
        herds.remove(aHerd);
    }
}