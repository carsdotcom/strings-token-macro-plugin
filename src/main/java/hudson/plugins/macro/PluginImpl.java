package hudson.plugins.macro;

import java.util.logging.Logger;

import hudson.Plugin;



public class PluginImpl extends Plugin {
	private final static Logger LOG = Logger.getLogger(PluginImpl.class.getName());
	
	public void start() throws Exception{
		LOG.info("SubstringTokenMacro: here to apply simple string utilities to macros");
		System.out.println("KEITH DO SOMETHING");
	}

}
