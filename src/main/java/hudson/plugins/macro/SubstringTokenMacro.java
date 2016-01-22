package hudson.plugins.macro;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;

@Extension(optional = true)
public class SubstringTokenMacro extends DataBoundTokenMacro {
	private static final Logger LOG = Logger.getLogger(SubstringTokenMacro.class.getName());
	
	@Parameter(required = true)
	public String value = "";
	
	@Parameter
	public int start = 0;
	
	@Parameter(required = true)
	public int end = 0;
	

	@Override
	public String evaluate(AbstractBuild<?, ?> context, TaskListener listener, String macroName)
			throws MacroEvaluationException, IOException, InterruptedException {
		String v = value;
		
		if (StringUtils.contains(value, "env:")){
			String key = StringUtils.substringAfter(value, "env:");
			EnvVars vars = context.getEnvironment(listener);
			v = vars.get(key);
		}
		String sub = StringUtils.substring(v, start, end);
		return StringUtils.isNotEmpty(sub)? sub:StringUtils.EMPTY;
	}

	@Override
	public boolean acceptsMacroName(String name) {
		return "SUBSTRING".equals(name);
	}

}
