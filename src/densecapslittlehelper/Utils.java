/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author scheich
 */
public class Utils {

    /**
     *
     * @param dlm
     * @param arr
     */
    public static void setModel(DefaultListModel dlm, ArrayList arr) {
        dlm.removeAllElements();
        arr.forEach((e) -> {
            dlm.addElement(e);
        });
    }

    /**
     *
     * @return
     */
    public static String getExport() {
        String ret = "[\n";
        for (Entry e : GlobVars.outputList) {
            ret += e.getJson();
        }
        ret = ret.substring(0, ret.length() - 3);
        ret += " \n";
        ret += "]";
        return ret;
    }

    /**
     *
     * @param list
     * @return
     */
    public static int getCount(ArrayList<Entry> list) {
        int count = 0;
        for (Entry e : list) {
            count += e.getCount();
        }
        return count;
    }

    /**
     *
     * @param entry
     */
    public static void i2o(Entry entry) {
        if (!containsName(GlobVars.outputList, entry.getText1())) {
            GlobVars.outputList.add(entry);
            GlobVars.inputList.remove(entry);
        } else {
            GlobVars.outputList.stream().filter(o -> o.getText1().equals(entry.getText1())).findFirst().get().getFiles().addAll(entry.getFiles());
            GlobVars.inputList.remove(entry);
        }

    }

    /**
     *
     * @param entry
     */
    public static void o2i(Entry entry) {
        if (!containsName(GlobVars.inputList, entry.getText1())) {
            GlobVars.inputList.add(entry);
            GlobVars.outputList.remove(entry);
        } else {
            GlobVars.inputList.stream().filter(o -> o.getText1().equals(entry.getText1())).findFirst().get().getFiles().addAll(entry.getFiles());
            GlobVars.outputList.remove(entry);
        }

    }

    /**
     *
     * @param list
     * @param name
     * @return
     */
    public static boolean containsName(final ArrayList<Entry> list, final String name) {
        return list.stream().filter(o -> o.getText1().equals(name)).findFirst().isPresent();
    }

    /**
     *
     * @param mv
     */
    public static void open(Component mv) {
        GlobVars.inputList.clear();
        GlobVars.outputList.clear();
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            GlobVars.jfc = new JFileChooser();
            int foo = GlobVars.jfc.showOpenDialog(mv);
            if (foo == JFileChooser.APPROVE_OPTION) {
                String path = GlobVars.jfc.getSelectedFile().getAbsolutePath();
                fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
                Object obj = ois.readObject();
                if (obj.getClass() == ArrayList.class) {

                    GlobVars.inputList = ((ArrayList<Entry>) ((ArrayList<Object>) obj).get(0));
                    GlobVars.outputList = ((ArrayList<Entry>) ((ArrayList<Object>) obj).get(1));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error....", JOptionPane.ERROR_MESSAGE);
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

    /**
     *
     * @param mv
     */
    public static void save(Component mv) {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            GlobVars.jfc = new JFileChooser();
            int foo = GlobVars.jfc.showSaveDialog(mv);
            if (foo == JFileChooser.APPROVE_OPTION) {
                String path = GlobVars.jfc.getSelectedFile().getAbsolutePath();
                fos = new FileOutputStream(path);
                oos = new ObjectOutputStream(fos);
                ArrayList<Object> outs = new ArrayList<>();
                outs.add(GlobVars.inputList);
                outs.add(GlobVars.outputList);
                oos.writeObject(outs);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error....", JOptionPane.ERROR_MESSAGE);
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

    /**
     *
     * @param arr
     * @return
     */
    public static ArrayList<String> getListtoStrings(ArrayList<Entry> arr) {
        ArrayList<String> ret = new ArrayList<String>();
        arr.forEach(e -> ret.add(e.getText1()));
        return ret;
    }

    /**
     *
     * @param f
     */
    public static void deleteFileFromInput(File f) {
        Iterator<Entry> i = GlobVars.inputList.iterator();
        outerloop:
        while (i.hasNext()) {
            Entry e = i.next();
            Iterator<File> it = e.getFiles().iterator();
            while (it.hasNext()) {
                File fit = it.next();
                //if (fit == f) {
                if (fit.getfName().equals(f.getfName()) && fit.getNr().equals(f.getNr())) {
                    it.remove();
                    if (e.getFiles().size() == 0) {
                        i.remove();
                    }
                    break outerloop;

                }
            }
        }
    }

    /**
     *
     * @param f
     */
    public static void deleteFileFromOutput(File f) {
        Iterator<Entry> i = GlobVars.outputList.iterator();
        outerloop:
        while (i.hasNext()) {
            Entry e = i.next();
            Iterator<File> it = e.getFiles().iterator();
            while (it.hasNext()) {
                File fit = it.next();
                if (fit == f) {
                    it.remove();
                    if (e.getFiles().size() == 0) {
                        i.remove();
                    }
                    break outerloop;

                }
            }
        }
    }

    /**
     *
     * @param passedMap
     * @return
     */
    public static LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, Collections.reverseOrder());
        Collections.sort(mapKeys, Collections.reverseOrder());

        LinkedHashMap<String, Integer> sortedMap
                = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
    //str = str.replaceAll("\\D+","");
//{
//  "id": [int], Unique identifier for this image,
//  "regions": [
//    {
//      "id": [int] Unique identifier for this region,
//      "image": [int] ID of the image to which this region belongs,
//      "height": [int] Height of the region in pixels,
//      "width": [int] Width of the region in pixels,
//      "phrase": [string] Caption for this region,
//      "x": [int] x-coordinate of the upper-left corner of the region,
//      "y": [int] y-coordinate of the upper-left corner of the region,
//    },
//    ...
//  ]
//}
    //str.replaceAll("\\D+","");

    // [{"regions": [{"region_id": 1382, "width": 82, "height": 139, "image_id": 1, "phrase": "the clock is green in colour", "y": 57, "x": 421}, {"region_id": 1383, "width": 182, "height": 109, "image_id": 1, "phrase": "shade is along the street ", "y": 372, "x": 194}, {"region_id": 1384, "width": 61, "height": 30, "image_id": 1, "phrase": "man is wearing sneakers", "y": 491, "x": 241}, {"region_id": 1385, "width": 36, "height": 36, "image_id": 1, "phrase": "cars headlights are off", "y": 377, "x": 61
    /**
     *
     * @return
     */
    public static String getJsonExport() {
        String ret = "";
        ArrayList<String> fileList = new ArrayList<>();
        for (Entry e : GlobVars.outputList) {
            for (File f : e.getFiles()) {
                if (!fileList.contains(f.getfName())) {
                    fileList.add(f.getfName());
                }
            }
        }
        int counter = 0;
        ret += "[";

        for (String str : fileList) {
            ret += "{";
            ret += "\"regions\":[\n";
            //ret+="\"id\": "+str.replaceAll("\\D+","")+"\n";

            for (Entry e : GlobVars.outputList) {
                for (File f : e.getFiles()) {
                    if (f.getfName().equals(str)) {
                        counter++;
                        ret += "{";
                        ret += "\"id\": " + counter + ",";
                        ret += "\"image\": " + str.replaceAll("\\D+", "") + ",";
                        ret += "\"height\": " + (f.getBox().getValues()[3] - f.getBox().getValues()[2]) + ",";
                        ret += "\"width\": " + (f.getBox().getValues()[1] - f.getBox().getValues()[0]) + ",";
                        ret += "\"phrase\": \"" + e.getText1() + "\",";
                        ret += "\"x\": " + f.getBox().getValues()[0] + ",";
                        ret += "\"y\": " + f.getBox().getValues()[2] + "";
                        ret += "},\n";
                    }
                }
            }
            ret = ret.substring(0, ret.length() - 2);//Letztes Komma löschen
            ret += "\n],";
            ret += "\"id\": " + str.replaceAll("\\D+", "") + "";
            ret += "},\n";

        }
        ret = ret.substring(0, ret.length() - 2);//Letztes Komma löschen
        ret += "]";
        return ret;
    }

}
