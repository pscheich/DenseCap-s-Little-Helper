/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.util.ArrayList;

/**
 *
 * @author scheich
 */
public class Entry   implements Comparable<Entry> {

    private String text1;
    private String text2;
    private ArrayList<File> files;

    public Entry() {
        this.text1 = "";
        this.text2 = "";
        this.files = new ArrayList<File>();
    }

    public Entry(String text1, String text2, ArrayList<File> files) {
        this.text1 = text1;
        this.text2 = text2;
        this.files = files;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return text1 + " (" + files.size() + ")";
    }
    public int compareTo(Entry cE) {

        int foo = ((Entry) cE).files.size();

        //ascending order
        //return this.files.size() - foo;

        //descending order
        return foo - this.files.size();
    }

//    @Override
//    public int compareTo(Object t) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
