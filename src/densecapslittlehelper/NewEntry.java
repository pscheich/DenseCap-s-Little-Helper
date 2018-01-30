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
public class NewEntry extends Entry{
        private ArrayList<NewFile> files;

    /**
     *
     * @return
     */
    public ArrayList<NewFile> getNewFiles() {
        return files;
    }   

    /**
     *
     * @param files
     */
    public void setNewFiles(ArrayList<NewFile> files) {
        this.files = files;
    }

    /**
     *
     * @param files
     */
    public void setNewFilesWithOld(ArrayList<File> files) {
        this.files=new ArrayList<>();
            for(File f: files){
                this.files.add(new NewFile(f));                
            }
    }

    /**
     *
     * @param e
     */
    public NewEntry(Entry e) {
                this.setText1(e.getText1()); 
                this.setNewFilesWithOld(e.getFiles());

        this.files = files;
    }    
}
