package robots;

import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.rules.InvalidRuleSessionException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatelessRuleSession;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

import org.jruleengine.RuleServiceProviderImpl;

public class RulesDispatcher {
	
	private StatelessRuleSession statelessRuleSession = createRuleSession();

	public List handelEvent(Object... inputObjects) {
			try {
				return statelessRuleSession.executeRules(Arrays.asList(inputObjects));
			} catch (InvalidRuleSessionException | RemoteException e) {
				throw new RuntimeException(e);
			}
	}

	private StatelessRuleSession createRuleSession()  {
		try(InputStream fileInputStream = new FileInputStream("C:\\projekty\\robocode-ai\\bin\\robots\\behaviour2.xml");){
			Class.forName(RuleServiceProviderImpl.class.getName());
			RuleServiceProvider ruleServiceProvider = RuleServiceProviderManager.getRuleServiceProvider("org.jruleengine");
			RuleAdministrator ruleAdministrator = ruleServiceProvider.getRuleAdministrator();		
			RuleExecutionSet ruleExecutionSet = ruleAdministrator.getLocalRuleExecutionSetProvider(null).createRuleExecutionSet(fileInputStream, null);
			ruleAdministrator.registerRuleExecutionSet(ruleExecutionSet.getName(), ruleExecutionSet, null);
			RuleRuntime ruleRuntime = ruleServiceProvider.getRuleRuntime();
			return (StatelessRuleSession) ruleRuntime.createRuleSession(ruleExecutionSet.getName(), new HashMap(), RuleRuntime.STATELESS_SESSION_TYPE);
		}catch(Exception exception){
			throw new RuntimeException(exception);
		}
	}
}
