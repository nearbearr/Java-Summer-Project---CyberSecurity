/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurity.util;

import cybersecurity.alpha.ReadFromPropertyFile;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class UploadThread extends Thread {

    String uploadFilePath;
    //String ftpFilePath;
    Socket clientSoc;
    DataInputStream din;
    DataOutputStream dout;
    BufferedReader br;
    Properties prop;

    public UploadThread(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
        int connectionFlag = 1;
        try {

            prop = new Properties();
            ReadFromPropertyFile objReadPropertyFile = new ReadFromPropertyFile();
            String port = objReadPropertyFile.getPortToConnect();
            String serverIP = objReadPropertyFile.getServerIP();
            
            clientSoc = new Socket(serverIP, Integer.parseInt(port));
            din = new DataInputStream(clientSoc.getInputStream());
            dout = new DataOutputStream(clientSoc.getOutputStream());
            
        } catch (IOException iOException) {
            connectionFlag = 0;

        } catch (Exception ex) {
            connectionFlag = 0;
        }

        if (connectionFlag == 1) {
            start();

        } else {
            System.out.println("exit");
        }
    }

    @Override
    public void run() {
        try 
        {
            dout.writeUTF("SEND");
            dout.writeUTF(uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1));
            din.readUTF();


            File f = new File(uploadFilePath);
            FileInputStream fin = new FileInputStream(f);
            int ch;
            do {
                ch = fin.read();
                dout.writeUTF(String.valueOf(ch));
            } while (ch != -1);
            fin.close();
            String serverReply = din.readUTF();

            if (serverReply.equalsIgnoreCase("File Send Successfully")) {
                System.out.println(serverReply);
                f = new File(uploadFilePath);
                f.delete();

            } else {
                //This output is being read by JpegImagesToMovie
            }
        } catch (Exception ex) {
            System.out.println("exit");

        } finally {
            try {

                dout.writeUTF("DISCONNECT");

            } catch (Exception e) {
            }
        }
    }
}
