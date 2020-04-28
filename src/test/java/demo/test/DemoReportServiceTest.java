package demo.test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import demo.DemoReportService;

public class DemoReportServiceTest {
	
	@Test
	public void should_print_report() throws Exception {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("demo.txt");
		List<String> lines = IOUtils.readLines(is, "UTF-8");
		
		DemoReportService service = new DemoReportService();
		int i = 1;
		for (String line : lines) {
			byte[] ba = service.getReportFile(i + "_" + line);
			FileUtils.writeByteArrayToFile(new File(String.format("C:/temp/out_%s_%s.pdf", i, System.currentTimeMillis())), ba);
			++i;
		}
	}
	
}
