import java.io.*;
import java.text.*;
import java.util.*;
import java.awt.Desktop;
import java.net.URI;
public class html{
    public static int no;
    public html() {
        PrintWriter outFile = null;
        Date dd = new Date();
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        String locate = "quote.html";
        try{//This is for finding a place to put the html file
        outFile = new PrintWriter(locate);    
        }
        catch (IOException e)
        {
        System.out.println("Error Creating HTML file");    
        }
        
        //Prints the html structure into the text
        outFile.print("<!DOCTYPE html>");
        outFile.print("<html>");
        outFile.print("<head>");
        outFile.print("<style>");
        
        //Extra sizing for title size
        outFile.print("h1{font-size:170%;}");
        outFile.print("body{font-family:Arial;font-size:150%;}");
        outFile.print("</style>");
        outFile.print("</head>");
        outFile.print("<body>");
        outFile.print("<div align=\"center\">");
        
        //The Title for the quote page
        outFile.print("<h1><p>SpaceY Quotation System V2</p></h1>");
        //Line separating title from quote detail
        outFile.print("<p>-----------------------------------------</p>");
        
        //Prints the Quote itself
        outFile.print("<p>Client: "+Input.id+" - "+Input.name+"</p>");
        outFile.print("<p>Service Code: "+Input.sCode+"</p>");
        outFile.print("<p>Number of Luanches: "+Input.launches+"</p>");
        outFile.print("<p>Orbit type: "+Input.orbit+"</p>");
        outFile.print("<p>Nitrogen Flush: "+Input.nitroC+"</p>");
        outFile.print("<p>Insurnace: "+Input.insurance+"</p>");
        if(Input.insurance.equals("Y")){
        outFile.printf("<p>Payload Value: %,12.0f</p>",Input.payLoad); 
        }
        if(Input.pack){
        outFile.print("<p>Souvenir Space Suit Pack: Y</p>");    
        }
        outFile.printf("<p>Credit Checked: Date %s</p>",df.format(dd));
        if(Input.credit<Input.total){
        outFile.print("<p>*****************************************</p>");
        outFile.print("<p>WARNING CLIENT HAS EXCEEDED CREDIT LIMIT</p>");
        }
        
        //Separates quote choices from price calculations
        outFile.print("<p>*****************************************</p>");
        outFile.printf("<p>Base Price: $%,12.0f</p>",Input.bPrice);
        outFile.printf("<p>Base Price * launches: $%,12.0f</p>",Input.lCost);
        if(Input.nitroC.equals("Y")){
        outFile.printf("<p>Nitrogen   * launches    :$%,12.0f</p>",Input.nitrogen);    
        }
        outFile.printf("<p>Gross Launch Cost        :$%,12.0f</p>",Input.gross);
        if(!Input.manned){
        outFile.printf("<p>Tax                      :$%,12.0f</p>",Input.tax);    
        }
        outFile.printf("<p>Nett Cost                :$%,12.0f</p>",Input.nett);
        if(Input.insurance.equals("Y")){
        outFile.printf("<p>Payload value            :$%,12.0f</p>",Input.payLoad);
        outFile.printf("<p>Insurance Cost           :$%,12.0f</p>",Input.insurC);
        }
        if(Input.orbit.equals("GTO")){
        outFile.printf("<p>Nesa Tracking            :$%,12.0f</p>",Input.nesa);
        }
        if(Input.orbit.equals("CSO")){
        outFile.printf("<p>Nesa Tracking            :$%,12.0f</p>",Input.CSOnesa);
        }
        outFile.print("<p>-----------------------------------------</p>");
        outFile.printf("<p>Total Cost: $%,12.0f</p>",Input.total);
        
        outFile.print("</div>");
        outFile.print("</body>");
        outFile.print("</html>");
        
        outFile.close();
        
        try{
            Desktop d = Desktop.getDesktop();
            d.browse(new URI(locate));
        }catch(Exception e){
         System.out.println("Could not find file");   
        }
        
    }
}