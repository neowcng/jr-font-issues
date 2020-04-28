package demo;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.jaspersoft.jasperreports.export.pdf.JRPdfExporter;

import demo.datasource.LabelDs;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.type.PdfVersionEnum;

public class DemoReportService {
	
	
	public byte[] getReportFile(String text) throws JRException {
		
		final String jasperFilename = "demo/jasper/remark.jrxml";
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		JRBeanCollectionDataSource dsList = new JRBeanCollectionDataSource(Arrays.asList(new LabelDs(text)));
		
		JasperReport jasperReport = JasperCompileManager.compileReport(Thread.currentThread().getContextClassLoader().getResourceAsStream(jasperFilename));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, dsList);
		
		ByteArrayOutputStream sout = new ByteArrayOutputStream();
		Exporter exporter = createJRPdfExporterForExport();
	    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(sout));
		exporter.exportReport();
		return sout.toByteArray();
	}
	
	
    private Exporter createJRPdfExporterForExport(){
        JRPdfExporter exporter = new JRPdfExporter();
        JRPropertiesUtil ui = exporter.getPropertiesUtil();
        ui.setProperty(net.sf.jasperreports.engine.JRFont.DEFAULT_PDF_ENCODING, "UTF-8");
        
        SimplePdfReportConfiguration reportConfg = new SimplePdfReportConfiguration();
        reportConfg.setForceLineBreakPolicy(Boolean.TRUE);
        
        SimplePdfExporterConfiguration exportConfg = new SimplePdfExporterConfiguration();
        exportConfg.setPdfVersion(PdfVersionEnum.VERSION_1_7);
        exporter.setConfiguration(exportConfg);
        exporter.setConfiguration(reportConfg);
        
        return exporter;
    }
	
}
