
import java.util.Formatter;
import java.io.FileNotFoundException;
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CuentaDePago {
    public double efec;

    public double calcularCosto(double tiempo, double[] precio){
        double[] tiempos={0.5, 1, 1.5,  2, 2.5,  3, 3.5, 4};

        
        for(int i=0; i<tiempos.length;i++){    
            if(tiempo==tiempos[i]){  
                //System.out.println(" Total: "+precio[i]);
                return precio[i];
            }           
           }  
            return -1;
    }

    public void setPromo(double tiempo, int numMaquina, double[] maquinas){
        if(tiempo==4){ 
            maquinas[numMaquina-1]=maquinas[numMaquina-1]+1;
        }
        else{
            if(tiempo>=2){
                maquinas[numMaquina-1]=maquinas[numMaquina-1]+0.5;
            }
        }
    }
    
    public double seleccionarFormaDePago(double costoTotal,  double[] tiempo, int maquina, double[] saldo){

        PagoEfectivo efectivo=new PagoEfectivo();
        PagoTarjeta tarjeta= new PagoTarjeta();

        Scanner leer=new Scanner(System.in);
        boolean rep=true;
        int opc=0;
        do{             
            do{
                try{
                    System.out.println("Forma de pago: 1) Efectivo  2) Tarjeta ");
                    opc=leer.nextInt();                    
                    rep=false;              
                }catch(InputMismatchException e){
                    leer.nextLine();
                }
            }while(rep);
        }while(opc!=1 && opc!=2);
                
        double opcEfectivo;
        do{
            if(opc==1){
                double cambio;

                do{
                efec=efectivo.ingresarCantidad();
                cambio=efectivo.pagarEnEfectivo(costoTotal, efec);                
                }while(cambio<0);

                return cambio;
            }
            else{
                opcEfectivo=tarjeta.IngresarDatosTarjetas(costoTotal, tiempo, maquina, saldo);
                if(opcEfectivo==-2){
                    opc=1;
                }
                else{
                    opc=3;
                }
            }            
        }while(opc==1);
        return opcEfectivo;   
        
    }


    public static Formatter salida;
    public void mostrarServicioComprado(int numMaquina, double total, double cambio){
        PagoEfectivo pag=new PagoEfectivo();
        if(numMaquina!=0){
        
        System.out.println("_________________TICKET_________________________________________");
        System.out.println("");
        System.out.println("Dispositivo num:_____________"+"["+numMaquina+"]");
        System.out.println("Total: ______________________"+"$"+total);
        if(efec==0){
            System.out.println("\n> Pago con tarjeta realizado <");
        }
        else{
            System.out.println("Pago:___________________"+"$"+efec);
            System.out.println("Cambio: ________________"+"$"+cambio);
        }
        System.out.println("\n______________________________________________________________");
            }
            else{
                System.out.println("_________________TICKET_________________________________________");     
                System.out.println("");
                System.out.println("> Compra cancelada <");       

            }            
        try{
            salida = new Formatter( "Ticket.txt" );
        }
        catch (FileNotFoundException fileNotFoundException){
            System.err.println(" Error al crear el archivo.");
            System.exit(1);
        }
        try{
            salida.format("\n_________________TICKET_________________________________________");
            salida.format("\n");
            if(numMaquina!=0){
                salida.format("\nDispositivo num:_____________"+"["+numMaquina+"]");
                salida.format("\nTotal: ______________________"+"$"+total);
                if(efec==0){
                    salida.format("\n> Pago con tarjeta realizado <");
                }
                else{
                salida.format("\nPago:___________________"+"$"+efec);
                salida.format("\nCambio: ________________"+"$"+cambio);                
                }    
            }
            else{
                salida.format("> Compra cancelada <");
            }

            salida.format("\n______________________________________________________________");
        }
        catch ( FormatterClosedException formatterClosedException ) {
            System.err.println( "Error al escribir en el archivo. ");
        }
        if ( salida != null)
        salida.close();
        

    }
}