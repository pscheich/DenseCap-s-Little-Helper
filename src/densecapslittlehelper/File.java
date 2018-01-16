/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

/**
 *
 * @author scheich
 */
public class File {
    private String fPath;
    private String fName;
    private Box box;
    private String nr;

    public File(String fPath, String fName, Box box,String nr) {
        this.fPath = fPath;
        this.fName = fName;
        this.box = box;
        this.nr = nr;
    }

    public File() {
        this.fPath = "";
        this.fName = "";
        this.box = new Box();
        this.nr="";
    }

    public String getfPath() {
        return fPath;
    }

    public void setfPath(String fPath) {
        this.fPath = fPath;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
    
    @Override
    public String toString(){
        return fName;
    }
    
    
            
}
