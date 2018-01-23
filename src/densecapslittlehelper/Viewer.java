/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author scheich
 */
public class Viewer {

//    public static void main(String[] args) {
//        String[] items = {"One", "Two", "Three", "Four", "Five"};
//        JComboBox combo = new JComboBox(items);
//        JTextField field1 = new JTextField("1234.56");
//        JTextField field2 = new JTextField("9876.54");
//        JPanel panel = new JPanel(new GridLayout(0, 1));
//        panel.add(combo);
//        panel.add(new JLabel("Field 1:"));
//        panel.add(field1);
//        panel.add(new JLabel("Field 2:"));
//        panel.add(field2);
//        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//        if (result == JOptionPane.OK_OPTION) {
//            System.out.println(combo.getSelectedItem()
//                    + " " + field1.getText()
//                    + " " + field2.getText());
//        } else {
//            System.out.println("Cancelled");
//        }
//    }
    public static void show(Entry e, boolean input) {

        JFrame F = new JFrame();
        JDialog d = new JDialog(F, "Viewer", true);
        JPanel panel = new JPanel(new GridLayout((e.getFiles().size() / 8) + 1, 8, 0, 0));
        JScrollPane scrollPane = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        String file = GlobVars.inputPathIMG;
        for (File f : e.getFiles()) {
            ImageIcon icon = new ImageIcon(file + "/" + f.getfName());
            Image image = icon.getImage(); // transform it 
            int x = f.getBox().getValues()[0];
            int y = f.getBox().getValues()[2];
            int w = f.getBox().getValues()[1] - f.getBox().getValues()[0];
            int h = f.getBox().getValues()[3] - f.getBox().getValues()[2];
//    xMin - min x value of the bounding box
//    xMax - max x value of the bounding box
//    yMin - min y value of the bounding box
//    yMax - max y value of the bounding box

            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dst.getGraphics().drawImage(image, 0, 0, w, h, x, y, x + w, y + h, null);

            icon = new ImageIcon(resizeImage((Image) dst, 120, 120, true));
            //  icon = new ImageIcon(dst.getScaledInstance(xx, yy, java.awt.Image.SCALE_SMOOTH));
            // JLabel l = new JLabel(icon);
            JButton b = new JButton(icon);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int reply = JOptionPane.showConfirmDialog(null,
                            "Do you want to delete File " + f.getfName() + "\n"
                            + e.getText1() + "\n",
                            "Delete file?",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        if (input) {
                            Utils.deleteFileFromInput(f);
                        } else {
                            Utils.deleteFileFromOutput(f);
                        };
                        d.dispose();
                    }
                }
            });

            panel.add(b);
            //  if (++count >= 53) {
            //       break;
            //    }
        }
        d.add(scrollPane, BorderLayout.CENTER);
        d.pack();
        // F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        d.setVisible(true);

    }

    /**
     * This method resizes the given image using Image.SCALE_SMOOTH.
     *
     * @param image the image to be resized
     * @param width the desired width of the new image. Negative values force
     * the only constraint to be height.
     * @param height the desired height of the new image. Negative values force
     * the only constraint to be width.
     * @param max if true, sets the width and height as maximum heights and
     * widths, if false, they are minimums.
     * @return the resized image.
     */
    public static Image resizeImage(Image image, int width, int height, boolean max) { //https://stackoverflow.com/questions/10306335/resizing-image-to-fit-frame
        if (width < 0 && height > 0) {
            return resizeImageBy(image, height, false);
        } else if (width > 0 && height < 0) {
            return resizeImageBy(image, width, true);
        } else if (width < 0 && height < 0) {

            return image;
            //alternatively you can use System.err.println("");
            //or you could just ignore this case
        }
        int currentHeight = image.getHeight(null);
        int currentWidth = image.getWidth(null);
        int expectedWidth = (height * currentWidth) / currentHeight;
        //Size will be set to the height
        //unless the expectedWidth is greater than the width and the constraint is maximum
        //or the expectedWidth is less than the width and the constraint is minimum
        int size = height;
        if (max && expectedWidth > width) {
            size = width;
        } else if (!max && expectedWidth < width) {
            size = width;
        }
        return resizeImageBy(image, size, (size == width));
    }

    /**
     * Resizes the given image using Image.SCALE_SMOOTH.
     *
     * @param image the image to be resized
     * @param size the size to resize the width/height by (see setWidth)
     * @param setWidth whether the size applies to the height or to the width
     * @return the resized image
     */
    public static Image resizeImageBy(Image image, int size, boolean setWidth) {
        if (setWidth) {
            return image.getScaledInstance(size, -1, Image.SCALE_SMOOTH);
        } else {
            return image.getScaledInstance(-1, size, Image.SCALE_SMOOTH);
        }
    }
}
