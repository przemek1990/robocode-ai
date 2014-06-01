package rule;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

public class RulesProcessor {
	private String file ="C:\\projekty\\robocode-ai\\bin\\robots\\behaviour2.xml";
	private StatelessRuleSession session = null;

	public RulesProcessor() {
		this.init();
	}

	private void init() {
		try {
			session = createRuleSession();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List process(Object... inputObjects) {
		try {
			// Execute the rules without a filter.
			List results = session.executeRules(Arrays.asList(inputObjects));
			// session.release();
			return results;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private StatelessRuleSession createRuleSession() throws Exception {
		// Load the rule service provider of the reference
		// implementation.
		// Loading this class will automatically register this
		// provider with the provider manager.
		Class.forName("org.jruleengine.RuleServiceProviderImpl");

		// Get the rule service provider from the provider manager.
		RuleServiceProvider serviceProvider = RuleServiceProviderManager.getRuleServiceProvider("org.jruleengine");

		// get the RuleAdministrator
		RuleAdministrator ruleAdministrator = serviceProvider.getRuleAdministrator();

		// get an input stream to a test XML ruleset
		// This rule execution set is part of the TCK.
		InputStream inStream = new FileInputStream(this.file);

		// parse the ruleset from the XML document
		RuleExecutionSet res1 = ruleAdministrator.getLocalRuleExecutionSetProvider(null).createRuleExecutionSet(inStream, null);
		inStream.close();

		// register the RuleExecutionSet
		String uri = res1.getName();
		ruleAdministrator.registerRuleExecutionSet(uri, res1, null);

		RuleRuntime ruleRuntime = serviceProvider.getRuleRuntime();

		// create a StatelessRuleSession
		StatelessRuleSession statelessRuleSession = (StatelessRuleSession) ruleRuntime
				.createRuleSession(uri, new HashMap(), RuleRuntime.STATELESS_SESSION_TYPE);
		return statelessRuleSession;
	}
}
