package cybersecurity.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUnzipCapturedImages extends Thread {

    String folderToZip;

    public ZipUnzipCapturedImages(String folderToZip) {
        this.folderToZip = folderToZip;
    }

    public void zipFile() {
        try {


            String zipFileName = folderToZip + ".zip";


            File rootFolderFile = new File(folderToZip);
            File[] fileList = rootFolderFile.listFiles();

            ZipOutputStream outStream = new ZipOutputStream(new FileOutputStream(zipFileName));

            for (int i = 0; i < fileList.length; i++) {
                File inputFileName = fileList[i];

                FileInputStream inStream = new FileInputStream(inputFileName);
                ZipEntry entry = new ZipEntry(inputFileName.getName());

                // Add a zip entry to the output stream
                outStream.putNextEntry(entry);

                byte[] buffer = new byte[1024];
                int bytesRead;
                //Each chunk of data read from the input stream
                //is written to the output stream
                while ((bytesRead = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, bytesRead);
                }
                outStream.closeEntry();
                inStream.close();
            }
            outStream.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        zipFile();
    }
}
