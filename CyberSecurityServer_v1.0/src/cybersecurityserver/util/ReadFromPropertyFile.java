package cybersecurityserver.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadFromPropertyFile {

    String rootFolder;
    String portToConnect;
    String serverIP;
    String serverFolder;
    
    Properties objProp;
    File f;

    public ReadFromPropertyFile() {
        objProp = new Properties();
        try {
            f = new File("Configuration.properties");
            objProp.load(new FileInputStream(f));

            rootFolder = objProp.getProperty("rootFolder");
            portToConnect = objProp.getProperty("portToConnect");
            serverIP = objProp.getProperty("serverIP");
            serverFolder = objProp.getProperty("serverFolder");


        } catch (Exception e) {
            System.out.println("Properties File" + e);
        }

    }

    public String getPortToConnect() {
        return portToConnect;
    }

    public void setPortToConnect(String portToConnect) {
        this.portToConnect = portToConnect;
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    public String getServerFolder() {
        return serverFolder;
    }

    public void setServerFolder(String serverFolder) {
        this.serverFolder = serverFolder;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }
}
