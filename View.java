import java.text.*;
import java.util.*;
public class View{
    public View(){
        String line = "------------------------------------------";
        String warning = "  *******                   :WARNING CLIENT HAS EXCEEDED CREDIT LIMIT";
        Date dd = new Date();
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        System.out.println();
        System.out.println(line);
        System.out.println("SpaceY Quotation System");
        System.out.println(line);
        
        //This is the top section of the console printout
        System.out.printf("\nClient                   :%s - %s",Input.id,Input.name);
        System.out.printf("\nServiceCode              :%s",Input.sCode);
        System.out.printf("\nNumber of Launches       :%s",Input.launches);
        System.out.printf("\nOrbit type               :%s",Input.orbit);
        System.out.printf("\nNitrogen Flush           :%s",Input.nitroC);
        System.out.printf("\nInsurance                :%s",Input.insurance);
        if(Input.insurance.equals("Y")){
        System.out.printf("\nPayload Value            : %,12.0f",Input.payLoad);
        }
        System.out.printf("\nCredit Checked           :Date %s",df.format(dd));
        if(Input.pack){
        System.out.printf("\nSpace souvenir pack      :Y");    
        }
        if(Input.credit<Input.total){
        System.out.print("\n"+warning);    
        }
        
        //This is the Costing list
        System.out.print("\n\n"+line);
        System.out.printf("\nBase Price               :$%,12.0f",Input.bPrice);
        System.out.printf("\nBase Price * launches    :$%,12.0f",Input.lCost);
        if(Input.nitroC.equals("Y")){
        System.out.printf("\nNitrogen   * launches    :$%,12.0f",Input.nitrogen);    
        }
        System.out.printf("\nGross Launch Cost        :$%,12.0f",Input.gross);
        if(!Input.manned){
        System.out.printf("\nTax                      :$%,12.0f",Input.tax);    
        }
        System.out.printf("\nNett Cost                :$%,12.0f",Input.nett);
        if(Input.insurance.equals("Y")){
        System.out.printf("\nPayload value            :$%,12.0f",Input.payLoad);
        System.out.printf("\nInsurance Cost           :$%,12.0f",Input.insurC);
        }
        if(Input.orbit.equals("GTO")){
        System.out.printf("\nNesa Tracking            :$%,12.0f",Input.nesa);
        }
        if(Input.orbit.equals("CSO")){
        System.out.printf("\nNesa Tracking            :$%,12.0f",Input.CSOnesa);
        }
        
        System.out.println();
        System.out.println(line);
        System.out.printf("Total Amount Due         :$%,12.0f\n",Input.total);
        System.out.println(line);
    }
}