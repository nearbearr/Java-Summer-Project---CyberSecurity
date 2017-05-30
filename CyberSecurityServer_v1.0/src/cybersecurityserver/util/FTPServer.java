package cybersecurityserver.util;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FTPServer extends Thread {

    public void run() {
        try {
            
            ReadFromPropertyFile rp = new ReadFromPropertyFile();
            String portNo = rp.getPortToConnect();

            ServerSocket soc = new ServerSocket(Integer.parseInt(portNo));
            System.out.println("FTP Server Started on Port Number " + portNo + " ");
            while (true) {
                System.out.println("Waiting for Connection ...");
                transferfile t = new transferfile(soc.accept());
                continue;
            }
        } catch (Exception e) {
            System.out.println("FTPServer : " + e);
        }
    }
}

class transferfile extends Thread {

    Socket ClientSoc;
    DataInputStream din;
    DataOutputStream dout;
    ReadFromPropertyFile rp = new ReadFromPropertyFile();
    String filePath = rp.getServerFolder();

    transferfile(Socket soc) {
        try {
            ClientSoc = soc;
            din = new DataInputStream(ClientSoc.getInputStream());
            dout = new DataOutputStream(ClientSoc.getOutputStream());
            System.out.println("FTP Client Connected ...");
            start();

        } catch (Exception ex) {
        }
    }

    void ReceiveFile() throws Exception {
        String filename = din.readUTF();
        if (filename.compareTo("File not found") == 0) {
            return;
        }
        System.out.println("File Path   :" + filePath);
        System.out.println("File Path   :" + filename);
        File f = new File(filePath);
        f.mkdirs();
        f = new File(filePath + File.separator + filename);
        String option;

        if (f.exists()) {
            dout.writeUTF("File Already Exists");
            option = din.readUTF();
        } else {
            dout.writeUTF("SendFile");
            option = "Y";
        }

        if (option.compareTo("Y") == 0) {
            FileOutputStream fout = new FileOutputStream(f);
            int ch;
            String temp;
            do {
                temp = din.readUTF();

                ch = Integer.parseInt(temp);
                if (ch != -1) {
                    fout.write(ch);
                }
            } while (ch != -1);
            fout.close();
            dout.writeUTF("File Send Successfully");
            ZipExtracter obj = new ZipExtracter(filePath + File.separator + filename, filePath + File.separator + filename.substring(0, filename.lastIndexOf(".")));
            //  f.delete();
        } else {
            return;
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for Command ...");
                String Command = din.readUTF();
                if (Command.compareTo("SEND") == 0) {
                    System.out.println("\tSEND Command Receiced ... from " + ClientSoc.getInetAddress().getHostAddress() + " at " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    ReceiveFile();
                    continue;
                } else if (Command.compareTo("DISCONNECT") == 0) {
                    System.out.println("\tDisconnect Command Receiced ... from " + ClientSoc.getInetAddress().getHostAddress() + " at " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    break;
                }
                sleep(20000);
            } catch (Exception ex) {
                System.out.println("Exeption in run :" + ex);
            }
        }
    }
}
