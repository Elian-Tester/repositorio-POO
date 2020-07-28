//import java.util.Scanner;

public class ServicioPc extends DispositivosDeRenta {

    public void servicioParaPc(int[]maquinasPc,double[] tiempoPc, double[] saldo){
        double[] precioPc ={  4, 8,  10, 13,  17, 21,  25, 29 };

        super.mostrarDisponibles(maquinasPc,tiempoPc);
        CuentaDePago cuenta=new CuentaDePago();
        
        System.out.println("\nTiempos:" );
        for(int j=0; j<tiempoPc.length; j++){
            System.out.print((j+1)+"["+ tiempoPc[j]+ "]  ");
        }
        int numMaquina=super.ingresarDatosDeServicio(maquinasPc,tiempoPc, 0); 
        
        //cuenta.calcularCosto(tiempo);
        double costoTotal=cuenta.calcularCosto(super.getTiempo(), precioPc);

        System.out.println("Total a pagar: "+costoTotal); 

        double cambio=cuenta.seleccionarFormaDePago(costoTotal, tiempoPc, numMaquina, saldo);
        if(cambio==-1){
            System.out.println("Cuenta cancelada!! ");
            super.setTiempo(0);
            super.ingresarDatosDeServicio(maquinasPc, tiempoPc, super.getNumMaquina());
            cuenta.mostrarServicioComprado(0, 0, 0);
        }
        else{
            cuenta.setPromo(super.getTiempo(), numMaquina, tiempoPc);
            cuenta.mostrarServicioComprado(numMaquina, costoTotal, cambio);  
        }
     
    }
}