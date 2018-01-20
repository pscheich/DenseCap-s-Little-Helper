/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.io.Serializable;

/**
 *
 * @author scheich
 */
public class File implements Serializable {
    private String fPath;
    private String fName;
    private Box box;
    private String nr;

    /**
     *
     * @param fPath
     * @param fName
     * @param box
     * @param nr
     */
    public File(String fPath, String fName, Box box,String nr) {
        this.fPath = fPath;
        this.fName = fName;
        this.box = box;
        this.nr = nr;
    }

    /**
     *
     */
    public File() {
        this.fPath = "";
        this.fName = "";
        this.box = new Box();
        this.nr="";
    }

    /**
     *
     * @return
     */
    public String getfPath() {
        return fPath;
    }

    /**
     *
     * @param fPath
     */
    public void setfPath(String fPath) {
        this.fPath = fPath;
    }

    /**
     *
     * @return
     */
    public String getfName() {
        return fName;
    }

    /**
     *
     * @param fName
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     *
     * @return
     */
    public Box getBox() {
        return box;
    }

    /**
     *
     * @param box
     */
    public void setBox(Box box) {
        this.box = box;
    }

    /**
     *
     * @return
     */
    public String getNr() {
        return nr;
    }

    /**
     *
     * @param nr
     */
    public void setNr(String nr) {
        this.nr = nr;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return fName;
    }
    
    
            
}
