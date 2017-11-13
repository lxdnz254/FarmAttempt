package calculatemaxt;

import java.util.*;

/**
 * MilkTaking objects represent the milk takings currently in the CalculateMaxT system.
 * This class may not be required, could ride in Cow class?
 */
public class MilkTaking 
{
    //attributes
    
    /**
     * the tag ID of the milk takings
     */
    private String milk;
    
    //links
    
    /**
     * the linked Herd object
     */
//    private Herd herd; 
    
    /**
     * the linked Farm object
     */
//    private Farm farm; 
    
    /**
     * the linked Cow object
     */
    private Cow cow; 
    
    
    //constructor
   
    /**
     * Initialises a new MilkTaking object with the given tag.
     *
     * @param aMilk the tag of the milk taking
     */
    MilkTaking(String aMilk)
    {
        milk = aMilk;
    }
   
   
    //public protocol
   
    /**
     * Returns the tag of this milk taking.
     *
     * @return milk
     */
    public String getMilk()
    {
        return milk;
    }
   
    //package protocol
    
    /**
     * Returns the cow this milk is from.
     *
     * @return the linked Cow object
     */
    Cow getCow()
    {
        return cow;
    }       
    
}