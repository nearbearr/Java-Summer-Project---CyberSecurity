package cybersecurityserver.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipExtracter {

    public ZipExtracter(String inputPath, String outputPath) {
        System.out.println("Called");
        System.out.println("Path input : " + inputPath);
        System.out.println("Path output : " + outputPath);
        File f = new File(outputPath);
        f.mkdirs();
        Enumeration enumeration = null;

        ZipFile z = null;
        try {
            z = new ZipFile(inputPath);
            enumeration = z.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
                if (!zipEntry.isDirectory()) {
                    String zipFileName = zipEntry.getName();
                    System.out.println("ZipFileName : " + zipFileName);
                    if (zipFileName.lastIndexOf(File.separator) > -1) {
                        zipFileName = zipFileName.substring(zipFileName.lastIndexOf(File.separator) + 1);
                    }
                    System.out.println("");
                    boolean b = copy(z.getInputStream(zipEntry), new BufferedOutputStream(new FileOutputStream(outputPath + File.separator + zipFileName)));
                    if (b) {

                        System.out.println("DeletingFile");
                        new File(inputPath).delete();
                    }
                }
            }
        } catch (Exception e1) {
            System.out.println("in unZipFile : " + e1);
        } finally {
            try {
                z.close();

            } catch (Exception e) {
            }
        }
    }

    private boolean copy(InputStream in, OutputStream out) {
        try {
            byte b[] = new byte[1024];
            int l = 0;
            while ((l = in.read(b)) != -1) {
                out.write(b, 0, l);
            }
            return true;
        } catch (Exception e) {
            System.out.println("in copy " + e);
        } finally {
            try {
                in.close();
                out.close();
                //new File().delete();
            } catch (Exception e) {
            }
        }
        return false;
    }
}