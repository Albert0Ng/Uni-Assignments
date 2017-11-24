import java.util.*;
public class Main{
    public Main()
    {
        int loop = 1;
        int looper = 1;
        String yn;
        Scanner sc = new Scanner(System.in);
        do{
            Input i = new Input();
            do{
            System.out.println("Enter another quote? (Y or N): ");
            yn = sc.nextLine();
            yn = yn.toUpperCase();
            yn = yn.trim();
            if(yn.equals("Y")){
                looper = 0;
            }
            else if(yn.equals("N")){
                looper = 0;
                loop = 0;
                System.out.println("Thank you for choosing SpaceY");
            }
            else{
                looper = 1;
                System.out.println("Invalid input\nPlease try again");
            }
        }while(looper==1);
        }while(loop==1);
    }
}