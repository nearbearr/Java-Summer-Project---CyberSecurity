/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurity.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author student
 */
public class CaptureScreenshots implements Runnable {

    public static boolean stop = false;
    String grabFolder;
    int i = 0;
    //  DBOperations objDb;

    public CaptureScreenshots() {
    }

    public CaptureScreenshots(int userActivityID, String grabFolder) {
        this.grabFolder = grabFolder;
    }

    public void getScreenShot() {
        i++;
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("Exception in getScreenShot : " + ex);
        }

        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        robot.delay(3000);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        String fileNameToSaveTo = grabFolder + File.separator + i + ".PNG";
        writeImage(bi, fileNameToSaveTo, "PNG");

        System.out.println("Screen Captured Successfully and Saved to:\n" + fileNameToSaveTo);

    }

    public static void writeImage(BufferedImage img, String fileLocation,
            String extension) {
        try {
            BufferedImage bi = img;
            File outputfile = new File(fileLocation);
            ImageIO.write(bi, extension, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (stop == false) {
            getScreenShot();
        }
    }
}
