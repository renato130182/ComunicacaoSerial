/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systeminfoorangepi;


import Control.NetworkConnection;
import com.pi4j.platform.PlatformAlreadyAssignedException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renato.soares
 */
public class SystemInfoOrangePi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
            NetworkConnection conn = new NetworkConnection();
        } catch (PlatformAlreadyAssignedException ex) {
            Logger.getLogger(SystemInfoOrangePi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
