

public class ServicioXbox extends DispositivosDeRenta {

    public void servicioParaXbox(int[] maquinasXbox, double[] tiempoXbox, double[] saldo){
        double[] precioXbox={7, 10, 14, 20, 25, 30, 34, 40};

        super.mostrarDisponibles(maquinasXbox,tiempoXbox);
        CuentaDePago cuenta=new CuentaDePago();
        
        System.out.println("\nTiempos:" );
        for(int j=0; j<tiempoXbox.length; j++){
            System.out.print((j+1)+"["+ tiempoXbox[j]+ "]  ");
        }
        int numMaquina=super.ingresarDatosDeServicio(maquinasXbox,tiempoXbox, 0);
        
        //cuenta.calcularCosto(tiempo);
        double costoTotal=cuenta.calcularCosto(super.getTiempo(), precioXbox);

        System.out.println("Total a pagar: "+costoTotal); 

        double cambio=cuenta.seleccionarFormaDePago(costoTotal, tiempoXbox, numMaquina, saldo);   
        if(cambio==-1){
            System.out.println("Cuenta cancelada!! ");
            super.setTiempo(0);
            super.ingresarDatosDeServicio(maquinasXbox, tiempoXbox, super.getNumMaquina());
            cuenta.mostrarServicioComprado(0, 0, 0);
        }
        else{
            cuenta.setPromo(super.getTiempo(), numMaquina, tiempoXbox);     
            cuenta.mostrarServicioComprado(numMaquina, costoTotal, cambio);             
        }

    } 
}