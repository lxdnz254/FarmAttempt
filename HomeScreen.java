package gui;

import calculatemaxt.*;
import java.util.*;

/**
 *
 * @author GW
 */
public class HomeScreen extends javax.swing.JFrame
{
    private CalculateMaxTCoord calculateMaxTCoord;
    private Cow cow;
    private Herd herd;
    private Farm farm;
    /**
     * Creates new form HomeScreen
     */
    public HomeScreen()
    {
        initComponents();
        calculateMaxTCoord = new CalculateMaxTCoord();
        Collection<Farm> farms = calculateMaxTCoord.getFarms();
        farmList1.setListData(farms);
        farmList2.setListData(farms);
        farmList3.setListData(farms);
        farmList4.setListData(farms);
        farmList5.setListData(farms);
        farmList1.setSelectedIndex(0);
        farmList2.setSelectedIndex(0);
        farmList3.setSelectedIndex(0);
        farmList4.setSelectedIndex(0);
        farmList5.setSelectedIndex(0);
        outcomeArea1.setText("Outcome:");
        outcomeArea2.setText("Outcome:");
        outcomeArea3.setText("Outcome:");
        outcomeArea4.setText("Outcome:");
        outcomeArea5.setText("Outcome:");
        outcomeArea6.setText("Outcome:");
    }

    //Helper methods

    private void resetFields()
    {
        //Farm Tab
        nameField.setText("");
        locationField.setText("");
        farmIDField.setText("");
        //Herd Tab
        titleField.setText("");
        UniqueIDField.setText("");
        //Cow Tab
        cowTagIDField.setText("");
}

    //The methods in this section are concerned with the Edit Cow tab

    private void addNewCow()
    {
        // Create a cow tag ID for the cow from the entered details
//        Cow cow = new Cow(cowTagIDField.getText());
        
        String theCowTagID = cowTagIDField.getText();
        if (theCowTagID.equals(""))
        {
            outcomeArea5.setText("Error: You need to enter the cow's tag ID.");
            return;
        }

        Herd theHerd = (Herd)herdList4.getSelectedValue();
        calculateMaxTCoord.birth(theCowTagID, theHerd);
        outcomeArea5.setText("Outcome: Cow " + theCowTagID + " has been birthed in "
            + theHerd + " herd.");

    }

    /**
     * Get all the cows from the coordinating object and reset the cow lists
     */
    private void updateCowLists()
    {
//        cowList1.setListData(getCows());
        cowList2.setListData(calculateMaxTCoord.getCows(herd));
    }

    private void displayCows()
    {
        Herd theHerd = (Herd) herdList1.getSelectedValue();
        Collection<Cow> cows = calculateMaxTCoord.getCows(theHerd);
        List<String> cowData = new ArrayList<String>();
        for (Cow eachCow : cows)
        {
            cowData.add(eachCow.getTagID());
        }
        cowList2.setListData(cowData);
    }

    private void removeCow()
    {
        // Remember the cow's tagID
//        TagID tagID = cow.getTagID();

        // Initiate the Delete Cow use case
//        calculateMaxTCoord.death(cow);

        // A cow has been removed so need to update the cow map and lists
        updateCowLists();

        // Update the currently selected cow
//       if (calculateMaxTCoord.getCows().isEmpty())
        {
            cow = null;
        }
//        else  // select the first cow in alpha order
        {
            cowList2.setSelectedIndex(0);
            cow = (Cow)cowList2.getSelectedValue();
        }
    }

    //The methods in this section are concerned with the Edit Herd tab

    private void doCreate()
    {
        String theTitle = titleField.getText();
        if (theTitle.equals(""))
        {
            outcomeArea4.setText("Error: You need to enter a herd name.");
            return;
        }

        String theUniqueID = UniqueIDField.getText();
        if (theUniqueID.equals(""))
        {
            outcomeArea4.setText("Error: You need to enter a unique ID.");
            return;
        }

        Farm theFarm = (Farm)farmList4.getSelectedValue();
        calculateMaxTCoord.create(theTitle, theUniqueID, theFarm);
        outcomeArea4.setText("Outcome: Herd " + theTitle + " has been created in "
            + theFarm + " farm.");
    }

    private void displayHerds()
    {
        Farm theFarm = (Farm) farmList4.getSelectedValue();
        Collection<Herd> herds = calculateMaxTCoord.getHerds(theFarm);
        List<String> herdData = new ArrayList<String>();
        for (Herd eachHerd : herds)
        {
            herdData.add(eachHerd.getTitle() + "... "
                    + eachHerd.getUniqueID());
        }
        herdList1.setListData(herdData);
        herdList2.setListData(herdData);
        herdList4.setListData(herdData);
        herdList5.setListData(herdData);
    }

    private void updateHerdLists()
    {
        herdList1.setListData(calculateMaxTCoord.getHerds(farm));
        herdList2.setListData(calculateMaxTCoord.getHerds(farm));
        herdList4.setListData(calculateMaxTCoord.getHerds(farm));
        herdList5.setListData(calculateMaxTCoord.getHerds(farm));
    }

    private void removeHerd()
    {
        // Check that a herd has been selected
        if (herdList4.isSelectionEmpty())
        {
            outcomeArea4.setText("You must select a herd.");
            return;
        }

        // Remember the herd's title
//        Title title = herd.getTitle();
        Herd theHerd = (Herd)herdList4.getSelectedValue();

        // Initiate the Delete Herd use case
        String message = "";
        calculateMaxTCoord.remove(herd);

        // A herd has been removed so need to update the herd map and lists
        updateHerdLists();

        // Update the currently selected herd
        if (calculateMaxTCoord.getHerds(farm).isEmpty())
        {
            herd = null;
            message = "\nThere are now no herds in the system.";
        }
        else  // select the first herd in alpha order
        {
            herdList4.setSelectedIndex(0);
            herd = (Herd)herdList4.getSelectedValue();
        }

        // Report success
        outcomeArea4.setText("Removal of " + herd + " has been recorded" + message);
    }

        //The methods in this section are concerned with the Edit Farm tab

    private void doAddFarm()
    {
        String theFarmName = nameField.getText();
        if (theFarmName.equals(""))
        {
            outcomeArea3.setText("Error: You need to enter a farm name.");
            return;
        }

        String theLocation = locationField.getText();
        if (theLocation.equals(""))
        {
            outcomeArea3.setText("Error: You need to enter a farm location.");
            return;
        }

        String theFarmID = farmIDField.getText();
        if (theFarmID.equals(""))
        {
            outcomeArea3.setText("Error: You need to enter a farm ID.");
            return;
        }

        Farm theFarm = (Farm)farmList3.getSelectedValue();

        calculateMaxTCoord.addFarm(theFarmName, theLocation, theFarmID);
        outcomeArea3.setText("Outcome: Farm " + theFarmName + " has been added to the collection of farms.");
     }

    private void displayFarms()
    {
        Farm theFarm = (Farm) farmList3.getSelectedValue();
        Collection<Farm> farms = calculateMaxTCoord.getFarms();
        List<String> farmData = new ArrayList<String>();
        for (Farm eachFarm : farms)
        {
            farmData.add(eachFarm.getName() + ", "
                    + eachFarm.getLocation() + ", "
                    + eachFarm.getFarmID());
        }
        farmList1.setListData(farmData);
        farmList2.setListData(farmData);
        farmList3.setListData(farmData);
        farmList4.setListData(farmData);
        farmList5.setListData(farmData);
    }

    private void updateFarmLists()
    {
        farmList1.setListData(calculateMaxTCoord.getFarms());
        farmList2.setListData(calculateMaxTCoord.getFarms());
        farmList3.setListData(calculateMaxTCoord.getFarms());
        farmList4.setListData(calculateMaxTCoord.getFarms());
        farmList5.setListData(calculateMaxTCoord.getFarms());
    }

    private void removeFarm()
    {
        // Remember the farm's name
//        Name name = farm.getName();
        Farm theFarm = (Farm)farmList3.getSelectedValue();

        // Initiate the Delete Farm use case
        calculateMaxTCoord.remove(farm);

        // A farm has been removed so need to update the farm map and lists
        updateFarmLists();

        // Update the currently selected farm
        if (calculateMaxTCoord.getFarms().isEmpty())
        {
            farm = null;
        }
        else  // select the first farm in alpha order
        {
            farmList3.setSelectedIndex(0);
            farm = (Farm)farmList3.getSelectedValue();
        }
    }

    private void calculateMT()
    {
        //calculate MaxT - as yet no data
        displayMaxT.setText("0");
        outcomeArea1.setText("There is not enough data to calculate MaxT");
    }
    
    private void calculateNC()
    {
        //calculate number of cows - as yet no cows
//        int count = cows.size();
        numberCows.setText("0");
        outcomeArea1.setText("There are no cows to count");
    }
    
    private void calculateAY()
    {
        //calculate average yield - as yet no data
        avgYield.setText("23");
        outcomeArea1.setText("This is temporary dummy data");
    }
    
    private void saveMilkTaking()
    {
        //incomplete as Cow function not working
        outcomeArea2.setText("There are no cows to milk!");
    }
    
    private void saveYieldValues()
    {
        //incomplete as Cow function not working
        outcomeArea6.setText("This function is non functional");
    }
    
private void resetOutcomeMessage()
    {
        outcomeArea1.setText("Outcome:");
        outcomeArea2.setText("Outcome:");
        outcomeArea3.setText("Outcome:");
        outcomeArea4.setText("Outcome:");
        outcomeArea5.setText("Outcome:");
        outcomeArea6.setText("Outcome:");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CalculateMaxTSystem = new javax.swing.JTabbedPane();
        homeTab = new javax.swing.JPanel();
        farmList1 = new m256gui.M256JList();
        herdList1 = new m256gui.M256JList();
        DisplayMaxTButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        displayMaxT = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        numberCows = new javax.swing.JTextPane();
        NumberCowsButton = new javax.swing.JButton();
        AverageYieldButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        avgYield = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        outcomeArea1 = new javax.swing.JTextArea();
        milkTab = new javax.swing.JPanel();
        farmList2 = new m256gui.M256JList();
        herdList2 = new m256gui.M256JList();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        cowList1 = new m256gui.M256JList();
        saveMilkTakingButton = new javax.swing.JButton();
        outcomeArea2 = new javax.swing.JTextArea();
        farmTab = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        farmList3 = new m256gui.M256JList();
        DeleteFarmButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        nameField = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        locationField = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        farmIDField = new javax.swing.JTextPane();
        addFarmButton = new javax.swing.JButton();
        outcomeArea3 = new javax.swing.JTextArea();
        herdTab = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        farmList4 = new m256gui.M256JList();
        herdList4 = new m256gui.M256JList();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        titleField = new javax.swing.JTextPane();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        UniqueIDField = new javax.swing.JTextPane();
        AddHerdButton = new javax.swing.JButton();
        DeleteHerdButton = new javax.swing.JButton();
        outcomeArea4 = new javax.swing.JTextArea();
        cowTab = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        farmList5 = new m256gui.M256JList();
        jLabel23 = new javax.swing.JLabel();
        herdList5 = new m256gui.M256JList();
        jLabel24 = new javax.swing.JLabel();
        cowList2 = new m256gui.M256JList();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        cowTagIDField = new javax.swing.JTextPane();
        AddCowButton = new javax.swing.JButton();
        DeleteCowButton = new javax.swing.JButton();
        outcomeArea5 = new javax.swing.JTextArea();
        yieldValuesTab = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        saveYieldValuesButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        outcomeArea6 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculate MaxT System");
        setBounds(new java.awt.Rectangle(300, 300, 0, 0));
        setLocation(new java.awt.Point(330, 330));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        farmList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        farmList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                farmList1ValueChanged(evt);
            }
        });

        herdList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        herdList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                herdList1ValueChanged(evt);
            }
        });

        DisplayMaxTButton.setText("Display MaxT");
        DisplayMaxTButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayMaxTButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(displayMaxT);

        jScrollPane4.setViewportView(numberCows);

        NumberCowsButton.setText("Number of Cows");
        NumberCowsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumberCowsButtonActionPerformed(evt);
            }
        });

        AverageYieldButton.setText("Average Yield");
        AverageYieldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AverageYieldButtonActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(avgYield);

        jLabel1.setText("Farm Name, Location, ID");

        jLabel2.setText("Herd Name, ID");

        jPanel6.setBackground(new java.awt.Color(255, 153, 51));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel3.setText("MaxT");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Home Screen");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        outcomeArea1.setEditable(false);
        outcomeArea1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout homeTabLayout = new javax.swing.GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(farmList1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(homeTabLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(homeTabLayout.createSequentialGroup()
                                .addComponent(herdList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homeTabLayout.createSequentialGroup()
                                .addComponent(DisplayMaxTButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(homeTabLayout.createSequentialGroup()
                                .addComponent(NumberCowsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(homeTabLayout.createSequentialGroup()
                                .addComponent(AverageYieldButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addComponent(outcomeArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(farmList1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(herdList1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(homeTabLayout.createSequentialGroup()
                                .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DisplayMaxTButton)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NumberCowsButton)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(AverageYieldButton)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(outcomeArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        CalculateMaxTSystem.addTab("HOME", homeTab);

        farmList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        farmList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                farmList2ValueChanged(evt);
            }
        });

        herdList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        herdList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                herdList2ValueChanged(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 153, 51));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Milk Takings");

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel29.setText("MaxT");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(24, 24, 24))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel13.setText("Farm Name, Location, ID");

        jLabel14.setText("Herd Name, ID");

        jLabel15.setText("Cow");

        jLabel16.setText("AM:");

        jLabel17.setText("Load or Alter Milk Takings");

        jLabel18.setText("PM:");

        jScrollPane6.setViewportView(jTextPane4);

        jScrollPane7.setViewportView(jTextPane5);

        cowList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cowList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cowList1ValueChanged(evt);
            }
        });

        saveMilkTakingButton.setText("Save");
        saveMilkTakingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMilkTakingButtonActionPerformed(evt);
            }
        });

        outcomeArea2.setEditable(false);
        outcomeArea2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout milkTabLayout = new javax.swing.GroupLayout(milkTab);
        milkTab.setLayout(milkTabLayout);
        milkTabLayout.setHorizontalGroup(
            milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(milkTabLayout.createSequentialGroup()
                        .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(farmList2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(herdList2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(milkTabLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(cowList1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(milkTabLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(saveMilkTakingButton)
                                    .addGroup(milkTabLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(35, 35, 35))
                    .addGroup(milkTabLayout.createSequentialGroup()
                        .addComponent(outcomeArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        milkTabLayout.setVerticalGroup(
            milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, milkTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(milkTabLayout.createSequentialGroup()
                        .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(milkTabLayout.createSequentialGroup()
                                .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(milkTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveMilkTakingButton))
                            .addComponent(farmList2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(milkTabLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cowList1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(milkTabLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(herdList2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outcomeArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        CalculateMaxTSystem.addTab("MILK", milkTab);

        farmTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                farmTabComponentShown(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 153, 51));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Edit Farm");

        jLabel32.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel32.setText("MaxT");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(25, 25, 25))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel19.setText("Farm Name, Location, ID");

        farmList3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        farmList3.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                farmList3ValueChanged(evt);
            }
        });

        DeleteFarmButton.setText("Delete Highlighted Farm");
        DeleteFarmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteFarmButtonActionPerformed(evt);
            }
        });

        jLabel25.setText("Farm Name");

        jLabel26.setText("Farm Location");

        jLabel27.setText("Farm ID");

        jScrollPane8.setViewportView(nameField);

        jScrollPane9.setViewportView(locationField);

        jScrollPane10.setViewportView(farmIDField);

        addFarmButton.setText("Add Farm");
        addFarmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFarmButtonActionPerformed(evt);
            }
        });

        outcomeArea3.setEditable(false);
        outcomeArea3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout farmTabLayout = new javax.swing.GroupLayout(farmTab);
        farmTab.setLayout(farmTabLayout);
        farmTabLayout.setHorizontalGroup(
            farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(farmTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(farmTabLayout.createSequentialGroup()
                        .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(farmList3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, farmTabLayout.createSequentialGroup()
                                    .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel27)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(addFarmButton))
                                .addGroup(farmTabLayout.createSequentialGroup()
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26))
                                    .addGap(46, 46, 46)))
                            .addComponent(jLabel25)
                            .addComponent(DeleteFarmButton))
                        .addContainerGap())
                    .addGroup(farmTabLayout.createSequentialGroup()
                        .addComponent(outcomeArea3, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        farmTabLayout.setVerticalGroup(
            farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(farmTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(farmTabLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(farmList3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(farmTabLayout.createSequentialGroup()
                        .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(farmTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(farmTabLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addFarmButton))
                        .addGap(18, 18, 18)
                        .addComponent(DeleteFarmButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(outcomeArea3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        CalculateMaxTSystem.addTab("FARM", farmTab);

        herdTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                herdTabComponentShown(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 153, 51));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("Edit Herd");

        jLabel33.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel33.setText("MaxT");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(22, 22, 22))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel20.setText("Farm Name, Location, ID");

        jLabel21.setText("Herd Name, ID");

        farmList4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        farmList4.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                farmList4ValueChanged(evt);
            }
        });

        herdList4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        herdList4.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                herdList4ValueChanged(evt);
            }
        });

        jLabel28.setText("Herd Name");

        jScrollPane11.setViewportView(titleField);

        jLabel30.setText("Herd ID");

        jScrollPane13.setViewportView(UniqueIDField);

        AddHerdButton.setText("Add Herd");
        AddHerdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddHerdButtonActionPerformed(evt);
            }
        });

        DeleteHerdButton.setText("Delete Highlighted Herd");
        DeleteHerdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteHerdButtonActionPerformed(evt);
            }
        });

        outcomeArea4.setEditable(false);
        outcomeArea4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout herdTabLayout = new javax.swing.GroupLayout(herdTab);
        herdTab.setLayout(herdTabLayout);
        herdTabLayout.setHorizontalGroup(
            herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(herdTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(herdTabLayout.createSequentialGroup()
                        .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(farmList4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(herdList4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, herdTabLayout.createSequentialGroup()
                                .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28))
                                .addGap(60, 60, 60))
                            .addGroup(herdTabLayout.createSequentialGroup()
                                .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(herdTabLayout.createSequentialGroup()
                                        .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AddHerdButton))
                                    .addComponent(DeleteHerdButton))
                                .addContainerGap())))
                    .addGroup(herdTabLayout.createSequentialGroup()
                        .addComponent(outcomeArea4, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        herdTabLayout.setVerticalGroup(
            herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(herdTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(herdTabLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(farmList4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(herdTabLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(herdTabLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, herdTabLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(herdTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddHerdButton))
                                .addGap(18, 18, 18)
                                .addComponent(DeleteHerdButton))
                            .addComponent(herdList4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(outcomeArea4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        CalculateMaxTSystem.addTab("HERD", herdTab);

        cowTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                cowTabComponentShown(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 153, 51));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Edit Cow");

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel34.setText("MaxT");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(27, 27, 27))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel22.setText("Farm Name, Location, ID");

        farmList5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        farmList5.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                farmList5ValueChanged(evt);
            }
        });

        jLabel23.setText("Herd Name, ID");

        herdList5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        herdList5.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                herdList5ValueChanged(evt);
            }
        });

        jLabel24.setText("Cow");

        cowList2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cowList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                cowList2ValueChanged(evt);
            }
        });

        jLabel31.setText("Cow Tag ID");

        jScrollPane14.setViewportView(cowTagIDField);

        AddCowButton.setText("Add Cow");
        AddCowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCowButtonActionPerformed(evt);
            }
        });

        DeleteCowButton.setText("Delete Highlighted Cow");
        DeleteCowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteCowButtonActionPerformed(evt);
            }
        });

        outcomeArea5.setEditable(false);
        outcomeArea5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout cowTabLayout = new javax.swing.GroupLayout(cowTab);
        cowTab.setLayout(cowTabLayout);
        cowTabLayout.setHorizontalGroup(
            cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cowTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(cowTabLayout.createSequentialGroup()
                        .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(farmList5, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(herdList5, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(cowList2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cowTabLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddCowButton)
                                    .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(cowTabLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DeleteCowButton)))
                        .addGap(14, 14, 14))
                    .addGroup(cowTabLayout.createSequentialGroup()
                        .addComponent(outcomeArea5, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        cowTabLayout.setVerticalGroup(
            cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cowTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cowTabLayout.createSequentialGroup()
                        .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(farmList5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(herdList5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cowList2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(cowTabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddCowButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DeleteCowButton)
                        .addGap(26, 26, 26)))
                .addComponent(outcomeArea5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        CalculateMaxTSystem.addTab("COW", cowTab);

        yieldValuesTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                yieldValuesTabComponentShown(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 153, 51));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel61.setText("Enter Yield Values");

        jLabel62.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel62.setText("MaxT");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addGap(27, 27, 27))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );

        saveYieldValuesButton.setText("Save");
        saveYieldValuesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveYieldValuesButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Evening 8", "Morning 16", "Evening 9", "Morning 15"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        outcomeArea6.setEditable(false);
        outcomeArea6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout yieldValuesTabLayout = new javax.swing.GroupLayout(yieldValuesTab);
        yieldValuesTab.setLayout(yieldValuesTabLayout);
        yieldValuesTabLayout.setHorizontalGroup(
            yieldValuesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yieldValuesTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(yieldValuesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(yieldValuesTabLayout.createSequentialGroup()
                        .addGroup(yieldValuesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(yieldValuesTabLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveYieldValuesButton))
                            .addComponent(outcomeArea6, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        yieldValuesTabLayout.setVerticalGroup(
            yieldValuesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yieldValuesTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(yieldValuesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveYieldValuesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outcomeArea6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        CalculateMaxTSystem.addTab("VALUES", yieldValuesTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CalculateMaxTSystem)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CalculateMaxTSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBounds(0, 0, 517, 389);
    }// </editor-fold>//GEN-END:initComponents

    private void DisplayMaxTButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayMaxTButtonActionPerformed
        calculateMT();
    }//GEN-LAST:event_DisplayMaxTButtonActionPerformed

    private void AverageYieldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AverageYieldButtonActionPerformed
        calculateAY();
    }//GEN-LAST:event_AverageYieldButtonActionPerformed

    private void farmList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_farmList1ValueChanged
        Farm theFarm = (Farm)farmList1.getSelectedValue();
        Collection<Herd> herds = calculateMaxTCoord.getHerds(theFarm);
        List<String> herdData = new ArrayList<String>();
        for (Herd eachHerd : herds)
        {
            herdData.add(eachHerd.getTitle());
        }
        herdList1.setListData(herdData);
    }//GEN-LAST:event_farmList1ValueChanged

    private void herdList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_herdList1ValueChanged
    }//GEN-LAST:event_herdList1ValueChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void farmList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_farmList2ValueChanged
        Farm theFarm = (Farm)farmList2.getSelectedValue();
        Collection<Herd> herds = calculateMaxTCoord.getHerds(theFarm);
        List<String> herdData = new ArrayList<String>();
        for (Herd eachHerd : herds)
        {
            herdData.add(eachHerd.getTitle());
        }
        herdList2.setListData(herdData);
    }//GEN-LAST:event_farmList2ValueChanged

    private void herdList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_herdList2ValueChanged
        displayHerds();
    }//GEN-LAST:event_herdList2ValueChanged

    private void farmList3ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_farmList3ValueChanged
//        displayFarms();
    }//GEN-LAST:event_farmList3ValueChanged

    private void farmList4ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_farmList4ValueChanged
        Farm theFarm = (Farm)farmList4.getSelectedValue();
        Collection<Herd> herds = calculateMaxTCoord.getHerds(theFarm);
        List<String> herdData = new ArrayList<String>();
        for (Herd eachHerd : herds)
        {
            herdData.add(eachHerd.getTitle());
        }
        herdList4.setListData(herdData);
    }//GEN-LAST:event_farmList4ValueChanged

    private void herdList4ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_herdList4ValueChanged
        displayHerds();
    }//GEN-LAST:event_herdList4ValueChanged

    private void farmList5ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_farmList5ValueChanged
        Farm theFarm = (Farm)farmList5.getSelectedValue();
        Collection<Herd> herds = calculateMaxTCoord.getHerds(theFarm);
        List<String> herdData = new ArrayList<String>();
        for (Herd eachHerd : herds)
        {
            herdData.add(eachHerd.getTitle());
        }
        herdList5.setListData(herdData);
    }//GEN-LAST:event_farmList5ValueChanged

    private void herdList5ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_herdList5ValueChanged
        displayHerds();
    }//GEN-LAST:event_herdList5ValueChanged

    private void cowList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_cowList1ValueChanged
        displayCows();
    }//GEN-LAST:event_cowList1ValueChanged

    private void cowList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_cowList2ValueChanged
        displayCows();
    }//GEN-LAST:event_cowList2ValueChanged

    private void DeleteFarmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteFarmButtonActionPerformed
       removeFarm();
    }//GEN-LAST:event_DeleteFarmButtonActionPerformed

    private void addFarmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFarmButtonActionPerformed
       doAddFarm();
       resetFields();
    }//GEN-LAST:event_addFarmButtonActionPerformed

    private void AddHerdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddHerdButtonActionPerformed
       doCreate();
       resetFields();
    }//GEN-LAST:event_AddHerdButtonActionPerformed

    private void DeleteHerdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteHerdButtonActionPerformed
       removeHerd();
    }//GEN-LAST:event_DeleteHerdButtonActionPerformed

    private void AddCowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCowButtonActionPerformed
       addNewCow();
       resetFields();
    }//GEN-LAST:event_AddCowButtonActionPerformed

    private void DeleteCowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteCowButtonActionPerformed
       removeCow();
    }//GEN-LAST:event_DeleteCowButtonActionPerformed

    private void cowTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cowTabComponentShown
//       resetOutcomeMessage();
        displayCows();
    }//GEN-LAST:event_cowTabComponentShown

    private void herdTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_herdTabComponentShown
//       resetOutcomeMessage();
        displayHerds();
    }//GEN-LAST:event_herdTabComponentShown

    private void farmTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_farmTabComponentShown
//       resetOutcomeMessage();
        displayFarms();
    }//GEN-LAST:event_farmTabComponentShown

    private void saveMilkTakingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMilkTakingButtonActionPerformed
        saveMilkTaking();
    }//GEN-LAST:event_saveMilkTakingButtonActionPerformed

    private void saveYieldValuesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveYieldValuesButtonActionPerformed
        saveYieldValues();
    }//GEN-LAST:event_saveYieldValuesButtonActionPerformed

    private void yieldValuesTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_yieldValuesTabComponentShown
//       resetOutcomeMessage();
    }//GEN-LAST:event_yieldValuesTabComponentShown

    private void NumberCowsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumberCowsButtonActionPerformed
        calculateNC();
    }//GEN-LAST:event_NumberCowsButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCowButton;
    private javax.swing.JButton AddHerdButton;
    private javax.swing.JButton AverageYieldButton;
    private javax.swing.JTabbedPane CalculateMaxTSystem;
    private javax.swing.JButton DeleteCowButton;
    private javax.swing.JButton DeleteFarmButton;
    private javax.swing.JButton DeleteHerdButton;
    private javax.swing.JButton DisplayMaxTButton;
    private javax.swing.JButton NumberCowsButton;
    private javax.swing.JTextPane UniqueIDField;
    private javax.swing.JButton addFarmButton;
    private javax.swing.JTextPane avgYield;
    private m256gui.M256JList cowList1;
    private m256gui.M256JList cowList2;
    private javax.swing.JPanel cowTab;
    private javax.swing.JTextPane cowTagIDField;
    private javax.swing.JTextPane displayMaxT;
    private javax.swing.JTextPane farmIDField;
    private m256gui.M256JList farmList1;
    private m256gui.M256JList farmList2;
    private m256gui.M256JList farmList3;
    private m256gui.M256JList farmList4;
    private m256gui.M256JList farmList5;
    private javax.swing.JPanel farmTab;
    private m256gui.M256JList herdList1;
    private m256gui.M256JList herdList2;
    private m256gui.M256JList herdList4;
    private m256gui.M256JList herdList5;
    private javax.swing.JPanel herdTab;
    private javax.swing.JPanel homeTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane locationField;
    private javax.swing.JPanel milkTab;
    private javax.swing.JTextPane nameField;
    private javax.swing.JTextPane numberCows;
    private javax.swing.JTextArea outcomeArea1;
    private javax.swing.JTextArea outcomeArea2;
    private javax.swing.JTextArea outcomeArea3;
    private javax.swing.JTextArea outcomeArea4;
    private javax.swing.JTextArea outcomeArea5;
    private javax.swing.JTextArea outcomeArea6;
    private javax.swing.JButton saveMilkTakingButton;
    private javax.swing.JButton saveYieldValuesButton;
    private javax.swing.JTextPane titleField;
    private javax.swing.JPanel yieldValuesTab;
    // End of variables declaration//GEN-END:variables
}
