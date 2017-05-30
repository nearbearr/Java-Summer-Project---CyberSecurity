/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurity.alpha;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class ReadFromPropertyFile {

    Properties objProp;
    String rootFolder;
    String portToConnect;
    String serverIP;
    String serverFolder;
    File f;

    public String getServerFolder() {
        return objProp.getProperty("serverFolder");
    }

    public void setServerFolder(String serverFolder) {
        this.serverFolder = serverFolder;
    }

    public String getServerIP() {
        return objProp.getProperty("serverIP");
    }

    public String getPortToConnect() {
        return objProp.getProperty("portToConnect");
    }

    public ReadFromPropertyFile() {
        objProp = new Properties();
        try {
            f = new File("Configuration.properties");
            objProp.load(new FileInputStream(f));

        } catch (Exception e) {
            System.out.println("Properties File" + e);
        }

    }

    public String getRootFolder() {
        return objProp.getProperty("rootFolder");
    }
}
