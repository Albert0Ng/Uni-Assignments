import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.math.*;

public class input{
    public static int counter = 0;
    public static int count = 0;
    
    public static String lVehicle;//instance of launch vehicle
    public static String Comment;//Instance of comment
    public static String ID;//string instance in parralel array
    public static String nitroC;//String for nitrogen choice
    
    public static double value;//Value of insurance number rounded to nearest number
    public static double nValue;//Value of nitrogen cannisters needed
    public static String PPP;//The allocated launch pad for first flight
    public static String PPP2;//The allocated launch pad for second flight
    public static double Hawk9;//The number of Hawk 9 vehicles needed
    public static double HawkH;//Number of Hawk heavy vehicles needed
    public static double BFR;//BFR vehicles needed in numbers
    public static double LOX;//Fuel for Hawk9,BFR and Hawk heavy
    public static double RP1;//Fuel for Hawk9 and Hawk heavy
    public static double Methane;//Fuel for BFR
    public static double Drako;//Number of Drako spacecrafts needed
    
    //The arrays for the file lines
    public static String[]dateArr = new String[30];
    public static String[]idArr = new String[30];
    public static String[]codeArr = new String[30];
    public static String[]launchArr = new String[30];
    public static String[]orbArr = new String[30];
    public static String[]nitroArr = new String[30];
    public static String[]insurArr = new String[30];
    public static String[]valueArr = new String[30];
    public static String[]commenArr = new String[30];
    public static String []vehArr = new String[30];
    
    public static String monS;//String for taking month value from array
    public static int mon = 0;//Integer for month value to be parsed from string
    public static String y;//String for taking year value
    public static String insurS;//String for taking in insurance value
    
    public static double insurValue;//Actual double value of insurance cost
    public static String fileName;//Actual file name
    
    //The main array for containing file lines
    public static String[]fileLines = new String[30];
    public input(){
        //Re-initialising variables for reuse
        counter = 0; insurValue = 0; count = 0; Hawk9 = 0;
        value = 0; nValue = 0;  HawkH = 0; Methane = 0; 
        y = "N"; insurS = "0"; BFR = 0; LOX = 0; RP1 = 0; 
        Drako = 0; PPP = "LC40"; 
        
        //This is for re-initializing the arrays 
        fileLines = new String[30];
        dateArr = new String[30];//Date array
        idArr = new String[30];//Client ID array
        codeArr = new String[30];//Service code array
        launchArr = new String[30];//Launch ID array
        orbArr = new String[30];//Orbit ID array
        nitroArr = new String[30];//Nitrogen choice arrray
        insurArr = new String[30];//Insurance choice array
        valueArr = new String[30];//Total value array
        commenArr = new String[30];//Comment array
        vehArr = new String[30];//Launch vehicle array; Not seen from text file
        
        Scanner inFile;
        
        boolean loop = true;//Loop for main program function
        boolean loop2 = true;//Loop for function within loop
        String input;//For inputting the number functions
        String fName;//For inputting text files
        Scanner s = new Scanner(System.in);
        
        
            System.out.println("\n1 - Produce an Order Form");
            System.out.println("2 - Produce an Action Plan");
            System.out.println("3 - Produce an Action Plan and File");
            System.out.println("4 - Exit");
        do{
            System.out.println("Enter one of the input numbers");
            input = s.nextLine();
            if(input.equals("1")
            ||input.equals("2")
            ||input.equals("3")){
                do{
                
                System.out.println("Input month file name: ");
                
                fName = s.nextLine();
                
                    try{
                        
                        inFile = new Scanner(new FileReader(fName));
                    }
                    catch(IOException e){
                        System.out.println("Could not find file");
                        return;
                    }
                    while(inFile.hasNextLine()){//Reads in file lines into array for processing
                        String temp = inFile.nextLine();
                        fileLines[count]= new String(temp);
                        count++;
                        counter++;
                    }
                    
                    assignment();
                    String split[] = dateArr[0].split("/");
                    monS = split[1];
                    mon = Integer.parseInt(monS);
                    monS = Month.of(mon).name();
                    //Gets month name from date number
                    y = String.format("20%s",split[2]);
                    //This outputs date format
                    
                    if(input.equals("1")){
                        orderPlan();//Prints order plan
                    }
                    else if(input.equals("2")){
                        actionPlan();//Prints action plan
                    }
                    else if(input.equals("3")){
                        System.out.println("Please input valid file name with .txt extension: ");
                        fileName = s.nextLine();
                        writeActionPlan();//Write action plan
                    }
                    loop = false;//Stops the instance of the class for the main class to re-initialise
                    loop2 = false;//Stops secondary loop
                
            }while(loop2);
            }
            else if(input.equals("4")){
                loop = false;//This stops the main for loop
                Main.looper = false;//This stops the program altogether
                System.out.println("Thank you for using SpaceY Monthly Planner");
            }
            else{
                System.out.println("Invalid input\nPlease try again");
            }
        }while(loop);
    }//End of main class object
    
    
    //Methods for action plan/order plan and writing to a file
    private static void assignment(){//Method called when valid file is input
        for(int e = 0; e<counter; e++){
        String split[] = fileLines[e].split(",");  
        
        dateArr[e] = new String(split[0].trim());
        idArr[e] = new String(split[1].trim());
        codeArr[e] = new String(split[2].trim());
        launchArr[e] = new String(split[3].trim());
        orbArr[e] = new String(split[4].trim());
        nitroArr[e] = new String(split[5].trim());
        insurArr[e] = new String(split[6].trim());
        valueArr[e] = new String(split[7].trim());
        try{
            commenArr[e]= new String(split[8].trim());
        }catch(IndexOutOfBoundsException i){
            commenArr[e] = new String(" ");
        }//For catching empty arrays indexes in comments
        
        if(codeArr[e].equals("ORB1")
        ||codeArr[e].equals("ISS5")){
           vehArr[e] = new String("hawk-9");
        }
        else if(codeArr[e].equals("ORBH")
        ||codeArr[e].equals("MOON2")){
            vehArr[e] = new String ("hawk heavy");
        }
        else if(codeArr[e].equals("ORBVH")
        ||codeArr[e].equals("MARS1")
        ||codeArr[e].equals("MARS2")){
            vehArr[e] = new String ("BFR");
        }//For deciding the launch vehicle array values
        
        }//End of main for loop function
    }//End of assignment method
    
    private static void orderPlan(){
        System.out.printf("Order Plan for %s %s",monS,y);
        System.out.print("\n==========================");
        
        for(int c = 0; c < counter; c++){
        //For printing out insurance for selected spaceflights
            if(insurArr[c].equals("Y")){//If statement checks if two BFR flights on same day
            
            insurValue = 0;
            insurS = valueArr[c];
            value = Double.parseDouble(insurS);
            insurValue += Math.ceil(value/1000000.0);
            if(insurValue==0){
                insurValue = 1;
            }
            
            System.out.printf("\nArrange %.0f million insurance for launch %s on %s",
            insurValue,launchArr[c],dateArr[c]);
            
        }
        
        }//Loop asks if insurance costs are needed and outputs if necessary
        
        for(int n = 0; n<counter; n++){//Prints out nesa tracking if necessary
            if(orbArr[n].equals("CSO")||
            orbArr[n].equals("GTO")){
                
                System.out.printf("\nArrange for NESA tracking for %s on %s",
                launchArr[n],dateArr[n]);
            }
        }//Looks for requirements for NESA tracking
        
        for(int j = 0; j<counter; j++){
            //Prints out space vehicles
            if(codeArr[j].equals("ORB1")
            ||codeArr[j].equals("ISS5")){
                Hawk9++;
                LOX +=  275000;
                RP1 += 120000;
            }
            else if(codeArr[j].equals("ORBH")
            ||codeArr[j].equals("MOON2")){
                HawkH++;
                LOX += 810000;
                RP1 += 353450;
            }
            else if(codeArr[j].equals("ORBVH")
            ||codeArr[j].equals("MARS1")
            ||codeArr[j].equals("MARS2")){
                BFR++;
                LOX += 4280000;
                Methane += 1870000;
            }
            if(codeArr[j].equals("ISS5")
            ||codeArr[j].equals("MOON2")){
                Drako++;
            }//end of if statements 
        }//Looks for spaceship requirements 
        
        if(Hawk9==1){
            System.out.printf("\nOrder 1 Hawk-9 launch Vehicle");
        }
        else if(Hawk9>1){
            System.out.printf("\nOrder %.0f Hawk-9 launch Vehicles",Hawk9);
        }//Prints if Hawk9 has been ordered
        
        if(HawkH==1){
            System.out.printf("\nOrder 1 Hawk Heavy launch Vehicle");
        }
        else if(HawkH>1){
            System.out.printf("\nOrder %.0f Hawk Heavy launch Vehicles",HawkH);
        }//Prints if Hawk Heavy vehicles needed
        
        if(BFR==1){
            System.out.printf("\nOrder 1 BFR launch Vehicle");
        }
        else if(BFR>1){
            System.out.printf("\nOrder %.0f BFR launch Vehicles",BFR);
        }//Prints if BFR vehicle ordered
        
        if(Drako==1){
            System.out.printf("\nOrder 1 Drako spacecraft");
        }
        else if(Drako>1){
            System.out.printf("\nOrder %.0f Drako spacecrafts",Drako);
        }
        
        for(int b = 0; b < counter; b++){
            //Loop for nitrogen flushes
            if(nitroArr[b].equals("Y")){
                nValue += 3;
            }
            
        }//Asks if there is need for nitrogen
        
        if(nValue > 2){
            System.out.printf("\nOrder %.0f Cylinders of Nitrogen",nValue);
        }
        
        if(LOX>0){
            System.out.printf("\nOrder      %,.0f Kg of LOX",LOX);
        }
        
        if(RP1>0){
            System.out.printf("\nOrder      %,.0f Kg of RP1",RP1);
        }
        
        if(Methane>0){
            System.out.printf("\nOrder      %,.0f Kg of Methane",Methane);
        }
        
        
        System.out.print("\n");
        
    }//End of order plan method object
    
    private static void actionPlan(){//Method for writing action plan
        for(int t = 0; t<counter; t++){
            
            if(vehArr[t].equals("BFR")){
                PPP = "KSC";
            }
            else{
                PPP = "LC40";
            }//Determines launch pad
                System.out.printf("\n%s 6:00       Start vehicle rollout of %s on pad %s for %s",
                dateArr[t],vehArr[t],PPP,launchArr[t]);
            
            if(vehArr[t].equals("hawk-9")
            ||vehArr[t].equals("hawk heavy")){
                System.out.printf("\n%s 7:00       Fuel rocket with LOX for %s",
                dateArr[t],launchArr[t]);
                System.out.printf("\n%s 8:00       Fuel rocket with RP1 for %s",
                dateArr[t],launchArr[t]);
            }
            else if(vehArr[t].equals("BFR")){
                System.out.printf("\n%s 7:00       Fuel rocket with LOX for %s",
                dateArr[t],launchArr[t]);
                System.out.printf("\n%s 8:00       Fuel rocket with methane for %s",
                dateArr[t],launchArr[t]);
            }//Prints out the scheduling for fuel supply
            
            if(nitroArr[t].equals("Y")){
                System.out.printf("\n%s 10:00      Nitrogen flush %s",
                dateArr[t],launchArr[t]);
            }
                System.out.printf("\n%s 10:50      GO/NOGO Decision %s",
                dateArr[t],launchArr[t]);
            
                System.out.printf("\n%s 11:00      Launch %s for %s %s",
                dateArr[t],launchArr[t],idArr[t],commenArr[t]);
            
            //If statement for printing out launches on same date
            if(dateArr[t].equals(dateArr[t+1])){
                if(PPP.equals("KSC")){
                    PPP = "LC40";
                }
                else{
                    PPP = "KSC";
                }
                t++;    
                System.out.printf("\n%s 11:30      Start vehicle rollout of %s on pad %s for %s",
                dateArr[t],vehArr[t],PPP,launchArr[t]);
            
            if(vehArr[t].equals("hawk-9")
            ||vehArr[t].equals("hawk heavy")){
                System.out.printf("\n%s 12:30      Fuel rocket with LOX for %s",
                dateArr[t],launchArr[t]);
                System.out.printf("\n%s 14:00      Fuel rocket with RP1 for %s",
                dateArr[t],launchArr[t]);
            }
            else if(vehArr[t].equals("BFR")){
                System.out.printf("\n%s 12:30      Fuel rocket with LOX for %s",
                dateArr[t],launchArr[t]);
                System.out.printf("\n%s 14:00      Fuel rocket with methane for %s",
                dateArr[t],launchArr[t]);
            }//Prints out the scheduling for fuel supply
            
            if(nitroArr[t].equals("Y")){
                    System.out.printf("\n%s 15:00      Nitrogen flush %s",
                    dateArr[t],launchArr[t]);
            }//For printing nitrogen flushes
            
                System.out.printf("\n%s 15:30      GO/NOGO Decision %s",
                dateArr[t],launchArr[t]);    
                
                System.out.printf("\n%s 15:40      Launch %s for %s %s",
                dateArr[t],launchArr[t],idArr[t],commenArr[t]);
            
            }//End of second day scheduling loop
        }//End of main for loop for method
        System.out.print("\n");
    }//End of action plan method
    
    private static void writeActionPlan(){//Method for writing action plan into file
        PrintWriter outFile = null;
        try{
            
            outFile = new PrintWriter(fileName);
            System.out.println("ActionPlan file created");
        }
        catch(IOException e)
        {
            System.out.println("ERROR: Could not write to file");
        }
        for(int a = 0; a<counter; a++){
            
            if(vehArr[a].equals("BFR")){
                PPP = "KSC";
            }
            else{
                PPP = "LC40";
            }//Determines launch pad
            
                outFile.printf("\n%s 6:00       Start vehicle rollout of %s on pad %s for %s",
                dateArr[a],vehArr[a],PPP,launchArr[a]);
                System.out.printf("\n%s 6:00       Start vehicle rollout of %s on pad %s for %s",
                dateArr[a],vehArr[a],PPP,launchArr[a]);
                outFile.println("");
            
            if(vehArr[a].equals("hawk-9")
            ||vehArr[a].equals("hawk heavy")){
                outFile.printf("\n%s 7:00       Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 7:00       Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
            
                outFile.printf("\n%s 8:00       Fuel rocket with RP1 for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 8:00       Fuel rocket with RP1 for %s",
                dateArr[a],launchArr[a]);
            }
            else if(vehArr[a].equals("BFR")){
                outFile.printf("\n%s 7:00       Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 7:00       Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
            
                outFile.printf("\n%s 8:00       Fuel rocket with methane for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 8:00       Fuel rocket with methane for %s",
                dateArr[a],launchArr[a]);
            }//Prints out the scheduling for fuel supply
            
            if(nitroArr[a].equals("Y")){
                outFile.printf("\n%s 10:00      Nitrogen flush %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 10:00      Nitrogen flush %s",
                dateArr[a],launchArr[a]);
            }
                outFile.printf("\n%s 10:50      GO/NOGO Decision %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 10:50      GO/NOGO Decision %s",
                dateArr[a],launchArr[a]);
            
                outFile.printf("\n%s 11:00      Launch %s for %s %s",
                dateArr[a],idArr[a],launchArr[a],commenArr[a]);
                outFile.println("");
                System.out.printf("\n%s 11:00      Launch %s for %s %s",
                dateArr[a],idArr[a],launchArr[a],commenArr[a]);
            
            //If statement for printing out launches on same date
            if(dateArr[a].equals(dateArr[a+1])){
                if(PPP.equals("KSC")){
                    PPP = "LC40";
                }
                else{
                    PPP = "KSC";
                }
                a++;    
                outFile.printf("\n%s 11:30      Start vehicle rollout of %s on pad %s for %s",
                dateArr[a],vehArr[a],PPP,launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 11:30      Start vehicle rollout of %s on pad %s for %s",
                dateArr[a],vehArr[a],PPP,launchArr[a]);
            
            if(vehArr[a].equals("hawk-9")
            ||vehArr[a].equals("hawk heavy")){
                outFile.printf("\n%s 12:30      Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 12:30      Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
                
                outFile.printf("\n%s 14:00      Fuel rocket with RP1 for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 14:00      Fuel rocket with RP1 for %s",
                dateArr[a],launchArr[a]);
            }
            else if(vehArr[a].equals("BFR")){
                outFile.printf("\n%s 12:30      Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 12:30      Fuel rocket with LOX for %s",
                dateArr[a],launchArr[a]);
            
                outFile.printf("\n%s 14:00      Fuel rocket with methane for %s",
                dateArr[a],launchArr[a]);
                outFile.println("");
                System.out.printf("\n%s 14:00      Fuel rocket with methane for %s",
                dateArr[a],launchArr[a]);
                }//Prints out the scheduling for fuel supply
            
                if(nitroArr[a].equals("Y")){
                    outFile.printf("\n%s 15:00      Nitrogen flush %s",
                    dateArr[a],launchArr[a]);
                    outFile.println("");
                    System.out.printf("\n%s 15:00      Nitrogen flush %s",
                    dateArr[a],launchArr[a]);
                }//For printing nitrogen flushes
            
                outFile.printf("\n%s 15:30      GO/NOGO Decision %s",
                dateArr[a],launchArr[a]);    
                outFile.println("");
                System.out.printf("\n%s 15:30      GO/NOGO Decision %s",
                dateArr[a],launchArr[a]); 
                
                outFile.printf("\n%s 15:40      Launch %s for %s %s",
                dateArr[a],idArr[a],launchArr[a],commenArr[a]);
                outFile.println("");
                System.out.printf("\n%s 15:40      Launch %s for %s %s",
                dateArr[a],idArr[a],launchArr[a],commenArr[a]);
            }
        
        }//End of main for loop for method
        outFile.close();
        System.out.print("\n");
    }//End of action plan writing method
}