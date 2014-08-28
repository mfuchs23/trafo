package org.dbdoclet.herold;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Ignore;
import org.junit.Test;

public class DistributionTests extends AbstractTests {

	private static final String HTML_FILE = "src/test/resources/JSB.html";

	@Test
	@Ignore
	public void testOptionLicense() {
		execute(new String[] { "herold", "--license" });
		execute(new String[] { "herold", "-L" });
	}

	@Test
	@Ignore
	public void testOptionHelp() {
		execute(new String[] { "herold", "--help" });
		execute(new String[] { "herold", "-h" });
	}

	@Test
	@Ignore
	public void testOptionVersion() {
		execute(new String[] { "herold", "--version" });
		execute(new String[] { "herold", "-V" });
	}

	@Test
	@Ignore
	public void testOptionsInOut() {

		execute(new String[] { "herold", "-i", HTML_FILE, "-o",
				"build/test/out.xml" });
		execute(new String[] { "herold", "--in", HTML_FILE, "--out",
				"build/test/out.xml" });
	}

	private void execute(String[] args) {

		Runtime runtime = Runtime.getRuntime();

		try {

			Process p = runtime.exec(args);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();

			reader = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();

			p.waitFor();
			int exitCode = p.exitValue();
			assertEquals(0, exitCode);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
