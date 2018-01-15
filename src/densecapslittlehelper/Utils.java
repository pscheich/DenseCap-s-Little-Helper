/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author scheich
 */
public class Utils {

    public static void setModel(DefaultListModel dlm, ArrayList arr) {
        dlm.removeAllElements();
        arr.forEach((e) -> {
            dlm.addElement(e);
        });
    }
}
