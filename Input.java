import java.util.*;
import java.io.*;
public class Input{
    public static String id;//The ID of the user
    public static String name;//The name of the user
    public static int credit;//Credit in user's possession
    public static double bPrice = 0;//Base price before calculations
    public static String sCode;//Service code
    public static int launches = 0;//Number of launches
    public static String launchValue;//String launch value to be put in launches
    public static String orbit;//Orbit choice (LEO,GTO,CSO)
    public static String nitroC = "N";//Choice whether to have nitrogen
    public static double lCost;//Base cost multiplied by launches
    public static double nitrogen = 0;//Total cost of nitrogen flushes
    public static String insurance = "NA";//Choice whether to have insurance
    public static double insurC = 0;//Cost of insurnace times payload
    public static String payLoadValue;//Parses payload value into payload double
    public static double payLoad = 0;//Payload value if necessary
    public static double tax = 0;//VAT tax for unmanned flights
    public static double nesa = 0;//Tax for GTO flights
    public static boolean pack;//Checks for space pack
    public static boolean manned;//Checks to see if manned or not
    public static double gross = 0;//Total Cost before tax
    public static double nett = 0;//Cost after tax
    public static double CSOnesa = 0;//Specialized tax for CSO flights
    public static double total = 0;//Final total cost
    
    public Input()
    {//Class for inputting items
        //This is for re-initialising variables
        bPrice = 0; launches = 0; lCost = 0; nitrogen = 0; insurC = 0;
        tax = 0; nesa = 0; gross = 0; nett = 0; CSOnesa = 0; total = 0;
        nitroC = "N"; insurance = "NA"; pack = false; credit = 0;
        manned = false;
        
        boolean Input = false; //Checks to see if array value matches up with inputs of the user
        String man = "U"; //Determines whether flight is manned or not
        String trim;//For properly formatting the numbers in the text file
        
        Scanner inFile;
        Scanner inFile2;
        String temp = "";
        int loop = 1;//The main loop "boolean"
        int limit = 0;//counter for index within file array for service codes
        int limit2 = 0;//counter for index within customers file
        String[]fileLines = new String[30];//Array for holding service details
        String[]customers = new String[30];//Array for holding customer details
        String cusList = "Customers.txt"; //Customer file
        String file = "ServiceCodesT.txt"; //Service Code file
        
        try//For reading the service code
        {
            inFile = new Scanner(new FileReader(file));
        }
        catch(IOException e)
        {
            System.out.println("File IO Error - File not found:\n"+file);
            return;
        }
        while(inFile.hasNextLine()){//For reading in file lines and adding to array for processing
            temp = inFile.nextLine();
            fileLines[limit] = new String(temp);
            limit++;
        }
        
        try{//For reading the customer list
            inFile2 = new Scanner(new FileReader(cusList));
        }
        catch(IOException e)
        {
            System.out.println("File IO Error - Could not find File");
            return;
        }
        while(inFile2.hasNextLine()){
            temp = inFile2.nextLine();
            customers[limit2] = new String(temp);
            limit2++;
        }

        String choice;
        Scanner s = new Scanner(System.in);

        String lines = "====================================";//Deign lines
        String errorCheck = "Invalid input\nPlease try again";
        System.out.println(lines);
        System.out.println("     SpaceY Quotation System V2");
        System.out.println(lines);
        
        do{//Checks for the client ID
            System.out.println("Enter client ID: ");
            id = s.nextLine();
            id = id.toUpperCase();
            id = id.trim();
            for(int b = 0; b < limit2; b++){
                String split[] = customers[b].split(",");
                if(id.equals(split[0])){
                    System.out.println(split[0]);
                    loop = 0;
                    name = split[1].trim();
                    trim = split[3].trim();
                    credit = Integer.parseInt(trim);
                    Input = true;
                }
            }
            if(!Input){System.out.println(errorCheck);}
        }while(loop==1);
        
        loop = 1;
        Input = false;
        
        do{
            System.out.println("Please input service Code: ");
            sCode = s.nextLine();
            sCode = sCode.toUpperCase();
            sCode = sCode.trim();
            for(int e = 0; e < limit; e++){
                String ssplit[] = fileLines[e].split(",");
                if(sCode.equals(ssplit[0])){
                    System.out.println(ssplit[0]);
                    loop = 0;
                    Input = true;
                    trim = ssplit[2];
                    trim = trim.trim();
                    bPrice = Integer.parseInt(trim);
                    man = ssplit[4].trim();
                }
            }
            if(!Input){System.out.println(errorCheck);}
        }while(loop==1);
        
        if(man.equals("M")){manned = true;}
        
        loop = 1;//loop variable recharges for second use
        
        do{//Receives the number of launches requested
            
            System.out.println("Enter Number of Launches: ");
            launchValue = s.nextLine();
            if(launchValue.matches("^[0-9]*$")){
                /*
                 Uses if statements instead of try catches
                 for more efficency, codecompactness and less 
                 errors 
                 */
                launches = Integer.parseInt(launchValue);
                if(1 > launches || 20 < launches){
                System.out.println("Only 1 to 20 flights allowed in single booking\nPlease try again");
            }
                else{
                    loop = 0;
                }
            }
            else{
                System.out.println(errorCheck);
            }
        }while(loop==1);
        
        //Calculates Launches value
        lCost = bPrice * launches;
        loop = 1;
        
        if(sCode.equals("ORB1")
        ||sCode.equals("ORBH")){
            do{//Asks for orbit choice if necessary
            System.out.println("Enter Choice of Orbit: ");
            orbit = s.nextLine();
            orbit = orbit.toUpperCase();
            orbit = orbit.trim();
            if(orbit.equals("LEO")){
                loop = 0;
                System.out.println(orbit);
            }
            else if(orbit.equals("GTO")){
                loop = 0;
                System.out.println(orbit);
            }
            else{
                System.out.println(errorCheck);
            }
            }while(loop==1);
        }
        
        loop = 1;
        
        if(sCode.equals("ORBVH")){
            do
            {//Asks for orbit with three choices
            System.out.println("Enter Choice of Orbit: ");
            orbit = s.nextLine();
            orbit = orbit.toUpperCase();
            orbit = orbit.trim();
            if(orbit.equals("LEO")){
                loop = 0;
            }
            else if(orbit.equals("GTO")){
                loop = 0;
            }
            else if(orbit.equals("CSO")){
                loop = 0;
            }
            else{
                System.out.println(errorCheck);
            }
        }while(loop==1);
        }
        
        //Checks for pre-chosen orbit settings
        if(sCode.equals("NY2LON")||sCode.equals("ISS5")){
            orbit = "LEO";
        }
        else if(sCode.equals("MOON2")
        ||sCode.equals("MARS1")
        ||sCode.equals("MARS2")
        ||sCode.equals("VENFLY")){
            orbit = "CSO";
        }
        
        loop = 1;
        
        //After orbit checking, checks if pace pack is needed
        if(manned && !sCode.equals("LEO")){
        pack = true;
        }else{pack = false;}
        
        if(sCode.equals("ORB1")
        ||sCode.equals("ORBH")
        ||sCode.equals("ORBVH")
        ||sCode.equals("ISS5")){
            do{//Checks for nitrogen flush
                System.out.println("Is Nitrogen Fluh needed (Y or N): ");
                nitroC = s.nextLine();
                nitroC = nitroC.toUpperCase();
                nitroC = nitroC.trim();
                if(nitroC.equals("Y")){
                    nitrogen = 185000 * launches;
                    
                    loop = 0;
                    
                }
                else if(nitroC.equals("N")){
                    loop = 0;
                }
                else{
                    System.out.println(errorCheck);
                }
            }while(loop==1);
        }
        gross = lCost + nitrogen;
        loop = 1;
        
        
        if(!manned){
        do{//Checks for insurance claims
            System.out.println("Insurance Y/N ");
            insurance = s.nextLine();
            insurance = insurance.toUpperCase();
            if(insurance.equals("Y")){
                loop = 0;
            }
            else if(insurance.equals("N")){
                loop = 0;
            }
            else{
                System.out.println(errorCheck);
            }
        }while(loop==1);
        
        //One more loop charge
            loop = 1;
            
        if(insurance.equals("Y")){
            do{
                System.out.println("Enter Payload Value: ");
                payLoadValue = s.nextLine();
                if(payLoadValue.matches("^[0-9]*$")){
                    try{//catches if integer is too big
                    payLoad = Integer.parseInt(payLoadValue);
                    if(payLoad<1000000){//checks if integer is negative
                        System.out.println(errorCheck);
                    }
                    else{
                        loop = 0;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println(errorCheck+"\nPress any button to continue");
                    s.nextLine();
                }
                }
                else{
                    System.out.println(errorCheck);
                }
            }while(loop==1);}
        }
        
        Prices p = new Prices();
        View v = new View();    
            
        do{//loop for printing out HTML file
            System.out.println("\nDo you wish to view HTML Quote? (Input Y or N): ");
            choice = s.nextLine();
            choice = choice.toUpperCase();
            choice = choice.trim();
            if(choice.equals("Y")){
                loop = 0;
                html h = new html();
            }
            else if(choice.equals("N")){
                loop = 0;
            }
            else{
                System.out.println(errorCheck);
            }
        }while(loop==1);
        
    }
}