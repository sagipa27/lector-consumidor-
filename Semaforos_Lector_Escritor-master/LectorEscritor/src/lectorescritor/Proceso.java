/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lectorescritor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Proceso extends Thread{

    public enum Tipo{LECTOR, ESCRITOR};
    private Semaphore semaforo;
    private Tipo tipo;

    public Proceso(String name, Tipo tipo, Semaphore semaforo) {
        super(name);
        this.semaforo = semaforo;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        if(tipo == tipo.LECTOR){
            leer();
        }else{
            escribir();
        }
    }
    
    private void leer() {
        System.out.println(getName() + " intentando leer");
        try {
            semaforo.acquire();
            
            System.out.println(getName() + " leyendo");
            
            Thread.sleep((long) (Math.random() * 50));            
            
            semaforo.release();
            
            System.out.println(getName() + " Ya he leido");
        } catch (InterruptedException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void escribir() {
        System.out.println(getName() + " intentado escribir");
        
        try {
            semaforo.acquire(5);
            
            System.out.println(getName() + " escribiendo");
            
            Thread.sleep((long) (Math.random() * 50));            
            
            semaforo.release(5);
            
            System.out.println(getName() + " Ya he escrito");
        } catch (InterruptedException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
