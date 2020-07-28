import java.util.Scanner;
import java.util.InputMismatchException;

public class DispositivosDeRenta {

        private int numMaquina;
        private double tiempo;
        public int numMaquinaTick;

    Scanner leer=new Scanner(System.in);

    //muestra lo que no esta ocupado tiempo y lugar
    public void mostrarDisponibles(int[] maquina, double[] tiempo){

        System.out.println("Todos los lugares: ");
        for(int i=0; i<maquina.length; i++){
                System.out.print("  ["+(i+1)+"] ");
        }        

        System.out.println("\nLibres: ");
        for(int i=0; i<maquina.length; i++){
            if(maquina[i]==0 && tiempo[i]==0){
                System.out.print("  ["+(i+1)+"] ");
            }
        }
    }

    public int ingresarDatosDeServicio(int []maquinas, double []tiempo, int auxLugar){
        boolean aux;
        do{ 
            do{

            if(auxLugar>0){
                setNumMaquina(auxLugar);
            }
            else{
                boolean rep=true;
                int numMaq=0;
                do{
                    System.out.println( "\nSeleccionar maquina:");
                    try{
                        numMaq=leer.nextInt();   
                            rep=false;              
                    }catch(InputMismatchException e){
                        leer.nextLine();
                    }
                }while(rep);
                setNumMaquina(numMaq);                
            }

            }while(getNumMaquina()<=0 || getNumMaquina()>maquinas.length); 
            
            if(auxLugar>0){
                maquinas[getNumMaquina()-1]=0;
                tiempo[getNumMaquina()-1]=getTiempo();
                aux=true;
            }
            else{
                if( verificarEspacio(maquinas,tiempo) ){
                    do{
                     boolean rep=true;     
                     double time=0;                  
                        do{
                            try{
                                System.out.println("\nIndique tiempo (no mayor a 4 hrs)");
                                time=leer.nextDouble(); 
                                    rep=false;              
                            }catch(InputMismatchException e){
                                leer.nextLine();
                            }
                        }while(rep); 
                        setTiempo(time);
                        maquinas[getNumMaquina()-1]=1;
                        tiempo[getNumMaquina()-1]=getTiempo();
                        aux=true; 
                    }while(verificarTiempo()==-1);
                    
                }
                else{
                    System.out.println(">> ocupado <<\n seleccione otra maquina__");
                    aux=false;
                }                
            }
                

            }while(aux==false);

            return getNumMaquina();
    }

    //Seleccionar maquina_________________________________________________________
    public void setNumMaquina(int numMaq){
        numMaquina=numMaq;
    }

    public int getNumMaquina(){
        return numMaquina;
    }

    public boolean verificarEspacio(int []maquinas, double []tiempo){ 
    
            if(maquinas[getNumMaquina()-1]==0 && tiempo[getNumMaquina()-1]==0){
                return true;
            }
            return false;        

    }

    public int verificarTiempo(){ //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Agregar a diagrama de clases
        double[] tiempos={0.5, 1, 1.5,  2, 2.5,  3, 3.5, 4};
        int aux=0;
        for(int i=0; i<tiempos.length;i++){    
            if(getTiempo()==tiempos[i]){
             return 1;  
            }           
           }
           return -1;          
    }

    //indicar tiempo_____________________________________________________________
    public void setTiempo(double time){
        tiempo=time;
    }

    public double getTiempo(){
        return tiempo;
    }

}