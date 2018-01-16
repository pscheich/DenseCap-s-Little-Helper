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
    
    public static String getExport(){
        String ret = "[\n";
        for (Entry e : GlobVars.outputList){
            ret+=e.getJson();
        }
        ret+="]";
        return ret;
    }
    
    public static int getCount(ArrayList<Entry> list){
        int count=0;
        for(Entry e : list){
            count+=e.getCount();
        }
        return count;
    }
}
