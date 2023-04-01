/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lectorescritor;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class LectorEscritor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int NUMERO_LECTORES = 2;
        final int NUMERO_ESCRITORES = 5;
        final int MAX_RECURSOS = 5;
        
        Semaphore semaforo = new Semaphore(MAX_RECURSOS);
        
        for (int i = 0; i < NUMERO_ESCRITORES; i++) {
            new Proceso("Escritor" + i, Proceso.Tipo.ESCRITOR ,semaforo).start();
        }
        
        for (int i = 0; i < NUMERO_LECTORES; i++) {
            new Proceso("Lector" + i, Proceso.Tipo.LECTOR, semaforo).start();
        }
    }
    
}
