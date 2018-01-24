/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package densecapslittlehelper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BoxLayout;

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

    private static JScrollPane scrollPane;
    private static ArrayList<File> delList;
public static void main(String[] args){
    show(new Entry(),false);
}
    public static void show(Entry e, boolean input) {
        delList = new ArrayList<>();
        JFrame F = new JFrame();
        JDialog d = new JDialog(F, "Viewer", true);

        Panel all = new Panel();
        setscrollPane(e);
        all.add(scrollPane);
        Panel footer = new Panel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        JButton bdel = new JButton("delete");
        bdel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (File filedel : delList) {
                    if (input) {
                        Utils.deleteFileFromInput(filedel);
                    } else {
                        Utils.deleteFileFromOutput(filedel);
                    }
                }
                d.dispose();
            }
        });
        JButton bclose = new JButton("close");
        bclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                d.dispose();
            }
        });
        footer.add(bdel);
        footer.add(bclose);

        all.add(footer);
        d.add(all, BorderLayout.NORTH);
        d.pack();
        d.setVisible(true);

    }

    private static void setscrollPane(Entry e) {

        JPanel panel = new JPanel(new GridLayout((e.getFiles().size() / 8) + 1, 8, 0, 0));
        scrollPane = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(720, 720));
        for (File f : e.getFiles()) {
            JButton b = new JButton(getIcon(f));
            b.setPreferredSize(new Dimension(120, 120));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (delList.contains(f)) {
                        delList.remove(f);
                        b.setIcon(getIcon(f));
                    } else {
                        delList.add(f);
                        b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/densecapslittlehelper/trash.png")));
                    }
                }
            });
            panel.add(b);
        }

    }

    private static ImageIcon getIcon(File f) {
        String file = GlobVars.inputPathIMG;
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
        return icon;
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
