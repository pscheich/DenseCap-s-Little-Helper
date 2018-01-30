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
public class NewFile extends File{
    
    /**
     *
     * @param fPath
     * @param fName
     * @param box
     * @param nr
     */
    public NewFile(String fPath, String fName, Box box,String nr) {
        super(fPath,fName,box,nr);
    }

    /**
     *
     * @param f
     */
    public NewFile(File f) {
        super(f.getfPath(),f.getfName(),f.getBox(),f.getNr());
    }
    
    /**
     *
     * @return
     */
    public String getHash() {
        return super.getfName()+super.getNr()+getBox().toString();
    }
}
