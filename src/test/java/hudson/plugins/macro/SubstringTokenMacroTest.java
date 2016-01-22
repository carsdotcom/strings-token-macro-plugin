package hudson.plugins.macro;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertFalse;

public class SubstringTokenMacroTest {
	
	@Test
	public void testAcceptsMacroName() throws Exception{
		SubstringTokenMacro macro = new SubstringTokenMacro();
		
		assertFalse(macro.acceptsMacroName("FOOBAR"));
		
		assertTrue(macro.acceptsMacroName("SUBSTRING"));
	}
	
	@Test
	public void testEvaluateNonEnv() throws Exception{
		SubstringTokenMacro macro = new SubstringTokenMacro();
		macro.value = "FOOBAR";
		macro.start = 1;
		macro.end = 4;
		
		assertEquals(StringUtils.substring("FOOBAR", 1, 4), macro.evaluate(null, null, "SUBSTRING"));
		
		macro.value = null;
		
		assertEquals("", macro.evaluate(null, null, "SUBSTRING"));
	}
	
	@Test
	public void testEvaluateEnv() throws Exception{
		SubstringTokenMacro macro = new SubstringTokenMacro();
		macro.value = "env:FOOBAR";
		macro.start = 1;
		macro.end = 4;
		
		AbstractBuild mab = mock(AbstractBuild.class);
		TaskListener ml = mock(TaskListener.class);
		EnvVars mev = mock(EnvVars.class);
		
		when(mab.getEnvironment(same(ml))).thenReturn(mev);
		
		when(mev.get(eq("FOOBAR"))).thenReturn("THISISVALUE");
		
		assertEquals(StringUtils.substring("THISISVALUE", 1, 4), macro.evaluate(mab, ml, "SUBSTRING"));		
		
		when(mev.get(eq("BLAHBLAH"))).thenReturn(null);
		
		macro.value="env:BLAHBLAH";
		assertEquals("", macro.evaluate(mab, ml, "SUBSTRING"));
		
	}
	
	

}
