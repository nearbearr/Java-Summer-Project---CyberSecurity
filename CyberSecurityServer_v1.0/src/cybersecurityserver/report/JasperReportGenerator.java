/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityserver.report;

import cybersecurityserver.alpha.DBConnection;
import java.io.InputStream;
import java.sql.Connection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author admin
 */
public class JasperReportGenerator {

   InputStream designFileStream;

    public JasperReportGenerator(InputStream designFileStream) {
        this.designFileStream = designFileStream;
        generateReport();
    }

    void generateReport() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(designFileStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Connection con = DBConnection.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println("Exception in generationg report, generateReport() of JasperReportGenerator: "+e);
        }

    }
}
