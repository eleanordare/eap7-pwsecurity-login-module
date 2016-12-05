# eap7-pwsecurity-login-module

- Export as a JAR file and copy to ${JBOSS_HOME}/standalone/deployments/business-central.war/WEB-INF/lib/
- Make sure ${JBOSS_HOME}/standalone/configuration/standalone.xml references CustomLoginModule class in security domains
- Restart EAP to see changes
