import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.InputMismatchException;

public class MainServicios{   

    public static void main(String[] args){

        int    maquinasPc   []=new int    [6];
        double tiempoPc     []=new double [6];
        int    maquinasXbox []=new int    [14];
        double tiempoXbox   []=new double [14];
        double[] saldo={ 100, 40,  60};

        boolean aux=true;
        do{

            for(int p=0; p<tiempoPc.length; p++){
                if(tiempoPc[p]>0){
                    tiempoPc[p]=tiempoPc[p]-.5;
                }
            }
    
            for(int x=0; x<tiempoXbox.length; x++){
                if(tiempoXbox[x]>0){
                    tiempoXbox[x]=tiempoXbox[x]-.5;       
                }  
            }
        verServicios();
        seleccionarServicio(maquinasPc,tiempoPc, maquinasXbox, tiempoXbox, saldo);
        }while(aux==true);
    }

    static private Scanner lectura;
    public static void verServicios(){
        String l;
  
        try{
            lectura = new Scanner( new File( "Menu.txt" ) );
            }
            catch ( FileNotFoundException fileNotFoundException ){
                System.err.println( "Error al abrir el archivo." );
                System.exit( 1 );
            }
        try{
            while(lectura.hasNext()){
                l=lectura.nextLine();
                System.out.println(l);
            }
        }
        catch(Exception e){
            System.out.println("Error de lectura");

        }   
    }

    public static void seleccionarServicio(int[]maquinasPc,double[] tiempoPc, int[] maquinasXbox,double[] tiempoXbox, double[] saldo ){
        Scanner leer=new Scanner(System.in);
        ServicioPc opcPc=new ServicioPc();
        ServicioXbox opcXbox= new ServicioXbox();

        int opc=0;
        do{ 
            boolean rep=true;
            do{
                try{
            System.out.println(" Seleccione servicio: 1) renta PC  2) renta Xbox One");   
            opc=leer.nextInt();                        
                    rep=false;              
                }catch(InputMismatchException e){
                    leer.nextLine();
                }
            }while(rep);
        }while(opc!=1 && opc!=2);

        if(opc==1){
            System.out.println("____Renta PC_____");
                opcPc.servicioParaPc(maquinasPc, tiempoPc, saldo);
        }
        else{
            System.out.println("____Xbox One_____");
                opcXbox.servicioParaXbox(maquinasXbox, tiempoXbox, saldo);
        }
    }
}