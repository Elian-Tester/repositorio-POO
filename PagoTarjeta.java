import java.util.InputMismatchException;
import java.util.Scanner;


public class PagoTarjeta {

    Scanner leer=new Scanner(System.in);

    public double IngresarDatosTarjetas(double total, double[] tiempo, int maquina, double[] saldo){

        int verif, conta=0;
        boolean aux=true;;
        double cambio=0;

        do{
            System.out.println("__________________Pago con tarjeta_____________");
            System.out.println("Ingresar numero de tarjeta: ");
            String numTarj=leer.next();
            System.out.println("Ingresar PIN: ");
            String PIN=leer.next();
            verif=verificarDatos(numTarj, PIN);
            System.out.println("intento: "+conta+"/4");
        //-------------------------------------------------------------------------------
        int saldoAux=-1;
        do{
            if(conta==4 || saldoAux==0){
                if(saldoAux!=0){
                    System.out.println(":: Intentos terminados ::");                    
                }

                int opc=0;
                boolean rep=true;
                do{
                    do{
                        try{
                            System.out.println("Opciones: 1) pagar en efectivo  2)cancelar cuenta");
                            opc=leer.nextInt();
                            rep=false;
                        }catch(InputMismatchException e){
                            leer.nextLine();
                        }
                    }while(rep);
                }while(opc<1 || opc>2);
                if(opc==1){
                    cambio=-2;
                    aux=false;
                    saldoAux=-1;
                }
                else{
                    System.out.println(" >>> Compra cancelada <<<");
                    aux= false;
                    cambio=-1; 
                    saldoAux=-1;
                }

            }
            else{
                if(verif<0 && conta<5){
                    System.out.println(":::: Datos invalidos :::::");
                    saldoAux=-1;
                        aux=true;
                }
                else{
                    saldoAux=pagarCuenta(verif, total, saldo);
                        aux=false;
                }
            }
        }while(saldoAux==0);
            conta=conta+1;
        }while(aux==true);
        return cambio;
    }

    public int pagarCuenta(int verif, double total, double[] saldo){
        
        //double cobro= saldo[verif]-total;
        if(saldo[verif]>=total){
            saldo[verif]-=total;
        System.out.println("____Compra realizada______");
        return 1;            
        }
        else{
            System.out.println("Saldo insuficiente");
            System.out.println("Saldo: "+saldo[verif]);
            return 0;
        }        

    }

    public int verificarDatos(String numTarj, String PIN){

        String[] cuentasDeTarjetas={"12345", "54321", "98765"};
        String[] contraseñas=      { "1234", "4321",  "9876"};

        int i;
        for(i=0 ; i<cuentasDeTarjetas.length; i++){
            if(numTarj.equals(cuentasDeTarjetas[i]) && PIN.equals(contraseñas[i]) ){
                return i;
            }
        }
        return -1;
    }
    
}