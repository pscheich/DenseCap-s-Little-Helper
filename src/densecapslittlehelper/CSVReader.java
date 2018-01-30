/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Thanks to https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
package densecapslittlehelper;

/**
 *
 * @author scheich
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author scheich
 */
public class CSVReader {

    /**
     *
     * @param csvFile
     * @param line
     * @param cvsSplitBy
     */
    
//    id - id of the bounding box within the image
//    name - name of the bounding box within the imagename - name of the bounding box within the image
//    image - image the bounding box is associated with
//    xMin - min x value of the bounding box
//    xMax - max x value of the bounding box
//    yMin - min y value of the bounding box
//    yMax - max y value of the bounding box
    public static void doIt(String csvFile, String line, String cvsSplitBy) {
        int counter = 0;
        ArrayList<Entry> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                counter++;
                // use comma as separator
                String[] elements = line.split(cvsSplitBy);
                if (elements[2] != "") {
                    int[] bs = {Integer.parseInt(elements[3]), Integer.parseInt(elements[4]), Integer.parseInt(elements[5]), Integer.parseInt(elements[6])};
                    Box b = new Box(bs);
                    File f = new File("", elements[0], b, elements[1]);
                    elements[2] = elements[2].toLowerCase();
                    int ii = -1;
                    boolean foo = false;
                    for (Entry e : GlobVars.inputList) {
                        ii++;
                        if (e.getText1().equals(elements[2])) {
                            foo = true;
                            break;
                        }
                    }
                    if (foo) {
                        GlobVars.inputList.get(ii).getFiles().add(f);
                    } else {
                        ArrayList<File> fs = new ArrayList<>();
                        fs.add(f);
                        Entry e = new Entry(elements[2], fs);
                        GlobVars.inputList.add(e);
                    }

                }
            }
            Collections.sort(GlobVars.inputList);

        } catch (Exception e) {
            System.out.println(counter);
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, e,"Error....",JOptionPane.ERROR_MESSAGE);

        }
    }

}
