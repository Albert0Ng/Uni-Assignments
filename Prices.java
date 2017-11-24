import java.math.*;
public class Prices{
    public Prices(){
        
        Input.nett = Input.gross;
        //Inserts tax if unmanned flight
        if(!Input.manned){
        Input.tax = Input.gross * 0.03;  
        Input.nett += Input.tax;
        }
        
        //Computes NESA fee if launch is GTO
        if(Input.orbit.equals("GTO")){
        Input.nesa = Input.lCost * .004;  
        Input.total += Input.nesa;
        }
        
        //Computes NESA fee if launch is CSO
        if(Input.orbit.equals("CSO")){
        Input.CSOnesa = Input.lCost * .006;
        Input.total += Input.CSOnesa;
        }
        
        //Computers insurnace if necessary
        if(Input.insurance.equals("Y")){
        Input.insurC = 97100 * Math.ceil(Input.payLoad/1000000.0);
        Input.total += Input.insurC;
        }
        
        Input.total += Input.nett;
    }
}