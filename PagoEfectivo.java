import java.util.InputMismatchException;
import java.util.Scanner;

public class PagoEfectivo {
    Scanner leer=new Scanner(System.in);

    public double ingresarCantidad(){
        
        boolean rep=true;
        double efectivo=0;
        do{
            try{
                System.out.println("Ingrese cantidad: ");
                efectivo=leer.nextDouble();    
                rep=false;              
            }catch(InputMismatchException e){
                leer.nextLine();
            }
        }while(rep);

        return efectivo;      
    }
    public double pagarEnEfectivo(double total, double efectivo){
        
        double cambio=0;

        //hacer resta

            cambio=efectivo-total;
            if(cambio<0){
                System.out.println("____El efectivo es menor que el total a pagar______");
            }
            return cambio;
    }
}