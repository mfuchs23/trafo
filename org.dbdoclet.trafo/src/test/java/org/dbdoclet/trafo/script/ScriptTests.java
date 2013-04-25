package org.dbdoclet.trafo.script;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.dbdoclet.service.ResourceServices;
import org.dbdoclet.trafo.TrafoException;
import org.dbdoclet.trafo.TrafoScriptManager;
import org.dbdoclet.trafo.param.Param;
import org.junit.Test;

public class ScriptTests {

	@Test
	public void testParse() throws TrafoException, IOException {
		Script script = new Script();

		InputStream instr = ResourceServices
				.getResourceAsStream("/script/tab2ly.trn");
		assertNotNull(instr);
		TrafoScriptManager mgr = new TrafoScriptManager();
		mgr.parseScript(script, instr, "nsp1");

		instr = ResourceServices.getResourceAsStream("/script/tab2ly.trn");
		assertNotNull(instr);
		mgr.parseScript(script, instr, "nsp2");

		Param<?> param = script.getParameter("tab2ly", "key");
		assertNull(param);
		param = script.getParameter("nsp1", "tab2ly", "key");
		assertNotNull(param);
		// script.dump();
	}

	@Test
	public void testWrite() throws TrafoException, IOException {
		Script script = new Script();

		InputStream instr = ResourceServices
				.getResourceAsStream("/script/tab2ly.trn");
		assertNotNull(instr);

		TrafoScriptManager mgr = new TrafoScriptManager();
		mgr.parseScript(script, instr, "nsp1");
		mgr.writeScript("nsp1", script, new File("./build/testWrite.conf"));
	}
}
