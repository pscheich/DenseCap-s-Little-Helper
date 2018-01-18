/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

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

    public static String getExport() {
        String ret = "[\n";
        for (Entry e : GlobVars.outputList) {
            ret += e.getJson();
        }
        ret += "]";
        return ret;
    }

    public static int getCount(ArrayList<Entry> list) {
        int count = 0;
        for (Entry e : list) {
            count += e.getCount();
        }
        return count;
    }

    public static void save() {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            JFileChooser jfc = new JFileChooser();
            int foo = jfc.showSaveDialog(null);
            if (foo == JFileChooser.APPROVE_OPTION) {
            String path = jfc.getSelectedFile().getAbsolutePath();          
                fos = new FileOutputStream(path);
                oos = new ObjectOutputStream(fos);
                ArrayList<Object> outs = new ArrayList<>();
                outs.add(GlobVars.inputList);
                outs.add(GlobVars.outputList);
                oos.writeObject(outs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void open() {
        GlobVars.inputList.clear();
        GlobVars.outputList.clear();
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            JFileChooser jfc = new JFileChooser();
            int foo = jfc.showOpenDialog(null);
            if (foo == JFileChooser.APPROVE_OPTION) {
                String path = jfc.getSelectedFile().getAbsolutePath();
                fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
                Object obj = ois.readObject();
                if (obj.getClass() == ArrayList.class) {

                    GlobVars.inputList = ((ArrayList<Entry>) ((ArrayList<Object>) obj).get(0));
                    GlobVars.outputList = ((ArrayList<Entry>) ((ArrayList<Object>) obj).get(1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
