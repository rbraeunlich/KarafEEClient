package de.blogspot.wrongtracks;

import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import de.blogspot.wrongtracks.prost.ejb.api.ProcessEJBRemote;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.RemoteInitialContextFactory");
		props.put(Context.PROVIDER_URL, "ejbd://127.0.0.1:4201");
//		props.put("openejb.authentication.realmName", "PropertiesLogin");
		props.put(Context.SECURITY_PRINCIPAL, "user");
		props.put(Context.SECURITY_CREDENTIALS, "password");
		Context ctx = new InitialContext(props);
		Object ref = ctx.lookup("ProcessEJBRemote");
		ProcessEJBRemote h = (ProcessEJBRemote) PortableRemoteObject.narrow(ref, ProcessEJBRemote.class);
		Map<String, String> result = h.getDeployedProcesses();
		System.out.println(result);
	}

}
