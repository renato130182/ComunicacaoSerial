/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacaoserial;

import view.SerialComunication;


/**
 *
 * @author renato.soares
 */
public class ComunicacaoSerial extends Serial.SerialTxRx  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SerialComunication jf = new SerialComunication();
        jf.setVisible(true);

    }

}
