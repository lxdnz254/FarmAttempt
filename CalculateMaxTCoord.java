package calculatemaxt;

import java.util.*;

/**
 * The coordinating class for the CalculateMaxT system.
 */
public class CalculateMaxTCoord
{
    //links

    /**
     * a collection of all Farm objects
     */
    private Collection<Farm> farms;
    /**
     * a collection of all Herd objects
     */
    private Collection<Herd> herds;
//    private ArrayList<ArrayList<Herd>> herds;
    /**
     * a collection of all Cow objects
     */
    private Collection<Cow> cows;

    //constructor

    /**
     * Initialises a new CalculateMaxTCoord object with default farms, herds, and cows.
     */
    public CalculateMaxTCoord()
    {
        //herds = new HashSet<>();
        //Herd herd1 = new Herd("Red","121G");
        //Herd herd2 = new Herd("Blue");
        //Herd herd3 = new Herd("Green");
        //Herd herd4 = new Herd("Indigo");
        
        //herd1 = new HashSet<>();
        //herd1.add(new Cow(9449));
        //herd1.add(new Cow(9450));
        //herd2 = new HashSet<>();
        //herd2.add(new );
        //herd2.add(new );
        //herd2.add(new );
        //herd3 = new HashSet<>();
        //herd3.add(new );
        //herd3.add(new );
        //herd3.add(new );
        
        //farm1 = new HashSet<Set<>>();
        //farm1.add(herd1);
        //farm1.add(herd2);
        //farm2 = new HashSet<Set<>>();
        //farm2.add(herd1);
        //farm2.add(herd2);
        //farm2.add(herd3);

//        farms = new HashSet<Farm>();
//        Farm farm1 = new Farm("Aries", "Greece", "ZB");
//        Farm farm2 = new Farm("Boulder", "Colorado", "AG");
//        Farm farm3 = new Farm("Crumbs", "Boston", "GH");
//        farms.add(farm1);
//        farms.add(farm2);
//        farms.add(farm3);

        farms = new ArrayList<Farm>();
        Farm farm1 = new Farm("Aries", "Greece", "ZB");
        Farm farm2 = new Farm("Boulder", "Colorado", "AG");
        Farm farm3 = new Farm("Crumbs", "Boston", "GH");
        farms.add(farm1);
        farms.add(farm2);
        farms.add(farm3);
        
        //herds = new HashSet<>();
        //Herd herd1 = new Herd("Red", "121G");

//        herds = new ArrayList<ArrayList<Herd>>();
        create("Red", "121G", farm1);
        create("Blue", "121D", farm1);
          
        create("Black", "123B", farm2);
        create("Purple", "321G", farm2);
        create("White", "311G", farm2);

        create("Mauve", "221T", farm3);
        create("Yellow", "321E", farm3);
        create("Mellow", "321F", farm3);

        //birth("90459", herd1);
        //birth("90460", herd1);
          
        //birth("8143", herd2);
        //birth("8144", herd2);
        //birth("8145", herd2);

    }


    /**
     * Returns all the farms in the CalculateMaxT system.
     *
     * @return a collection of all the Farm objects
     */
    public Collection<Farm> getFarms()
    {
        return farms;
    }


    /**
     * Returns the herds that are on the farm.
     *
     * @param aFarm a farm
     *
     * @return a collection of all the Herd objects linked to aFarm
     */
    public Collection<Herd> getHerds(Farm aFarm)
//    public ArrayList<Herd> getHerds(Farm aFarm)
    {
        return aFarm.getHerds();
    }

    /**
     * Returns the cows that are in the herd.
     *
     * @param aHerd a herd
     *
     * @return a collection of all the Cow objects linked to aHerd
     */
    public Collection<Cow> getCows(Herd aHerd)
    {
        return aHerd.getCows();
    }

    /**
     * Records the creation of a farm.
     *
     * @param aName the name of the farm
     * @param aLocation the location of the farm
     * @param aFarmID the unique ID of the farm
     */
    public void addFarm(String aName, String aLocation, String aFarmID)
    {
//        farms = new ArrayList<>();
        Farm aFarm = new Farm(aName, aLocation, aFarmID);
        farms.add(aFarm);
    }

    /**
     * Records the addition of a herd with the given attributes to the farm.
     *
     * @param aTitle the title of the herd
     * @param aUniqueID a unique ID
     * @param aFarm a farm
     */
    public void create(String aTitle, String aUniqueID, Farm aFarm)
    {
        Herd aHerd = new Herd(aTitle, aUniqueID);
        aFarm.add(aHerd);
    }

    /**
     * Records the addition of a cow with the given attributes to the herd.
     *
     * @param aTagID a unique ID
     * @param aHerd a herd
     */
    public void birth(String aTagID, Herd aHerd)
    {
        Cow aCow = new Cow(aTagID);
        aHerd.add(aCow);
    }

    /**
    * Records the removal of the farm.
    * All links with aFarm are removed.
    *
    * @param aFarm a farm
    */
   public void remove(Farm aFarm)
   {
//      aFarm.remove();
   }

   /**
    * Records the removal of a herd.
    * All links with aHerd are removed.
    *
    * @param aHerd a herd
    */
   public void remove(Herd aHerd)
   {
//      aHerd.discharge();
   }
}