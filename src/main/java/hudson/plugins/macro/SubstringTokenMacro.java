package hudson.plugins.macro;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;

@Extension(optional = true)
public class SubstringTokenMacro extends DataBoundTokenMacro {
	
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
		System.out.println("I MADE IT IN HERE");
		if (StringUtils.contains(value, "env:")){
			String key = StringUtils.substringAfter(value, "env:");
			Map<String, String> env = context.getBuildVariables();
			v = env.get(key);
		}
		
		return StringUtils.substring(v, start, end);
	}

	@Override
	public boolean acceptsMacroName(String name) {
		System.out.println("I AM BEING CALLED");
		// TODO Auto-generated method stub
		return "SUBSTRING".equals(name);
	}

}
