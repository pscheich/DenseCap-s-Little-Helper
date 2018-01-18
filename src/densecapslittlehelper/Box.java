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
public class Box implements Serializable{

    private int[] values = {0, 0, 0, 0};

    public Box() {
    }

    public Box(int[] box) {
        values = box;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "[" + values[0] + "," + values[1] + "," + values[2] + "," + values[3] + "]";
    }

}
