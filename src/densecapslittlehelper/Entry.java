/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author scheich
 */
public class Entry implements Comparable<Entry>, Serializable {

    private String text1;

    private ArrayList<File> files;

    /**
     *
     */
    public Entry() {
        this.text1 = "";

        this.files = new ArrayList<File>();
    }

    /**
     *
     * @param text1
     * @param files
     */
    public Entry(String text1, ArrayList<File> files) {
        this.text1 = text1;

        this.files = files;
    }

    /**
     *
     * @return
     */
    public String getText1() {
        return text1;
    }

    /**
     *
     * @param text1
     */
    public void setText1(String text1) {
        this.text1 = text1;
    }

    /**
     *
     * @return
     */
    public ArrayList<File> getFiles() {
        return files;
    }

    /**
     *
     * @param files
     */
    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return text1 + " (" + files.size() + ")";
    }

    /**
     *
     * @param cE
     * @return
     */
    public int compareTo(Entry cE) {

        int foo = ((Entry) cE).files.size();

        //ascending order
        //return this.files.size() - foo;
        //descending order
        return foo - this.files.size();
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

    /**
     *
     * @return
     */
    public String getJson() {
        String ret = "";
        for (File f : files) {
            ret += "\t{\"region_id\": " + f.getNr() + f.getfName()  + ","
                    + " \"width\": " + (f.getBox().getValues()[1]-f.getBox().getValues()[0]) + ","
                    + " \"height\": " + (f.getBox().getValues()[3]-f.getBox().getValues()[2]) + ","
                    + " \"image_id\": " + f.getfName() + ","
                    + " \"phrase\": \"" + this.text1 + "\","
                    + " \"y\": " + f.getBox().getValues()[2] + ","
                    + " \"x\": " + f.getBox().getValues()[0] + "} \n";
        }
        return ret;
    }

    /**
     *
     * @return
     */
    public int getCount() {
        return files.size();
    }

//    @Override
//    public int compareTo(Object t) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
