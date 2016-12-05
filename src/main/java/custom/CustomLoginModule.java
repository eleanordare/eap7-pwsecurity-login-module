package custom;

import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class CustomLoginModule extends UsernamePasswordLoginModule {

	@Override
	public boolean login(){
		return PWSecurityImpl.login(getUsername(), getUsersPassword());
	}
	
	@SuppressWarnings("rawtypes")
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {

		super.initialize(subject, callbackHandler, sharedState, options);
	}

	/**
	 * (required) The groups of the user. Must be at least one group called
	 * "Roles" for user.
	 */
	@Override
	protected Group[] getRoleSets() throws LoginException {

		SimpleGroup group = new SimpleGroup("Roles");
		
		PWPermission[] permissions = getAllPermissions(getUsername(), getApplicationId());
		for (PWPermission i : permissions) {
			group.addMember(new SimplePrincipal(i));
		}
		

		System.out.println("Role for user " + getUsername() + ": "
				+ group.members().nextElement().toString());

		return new Group[] { group };
	}

	@Override
	protected String getUsersPassword() throws LoginException {
		return null;
	}

}
