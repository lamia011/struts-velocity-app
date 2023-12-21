package struts_example;

import javax.servlet.ServletContextEvent;


public class ServletContextListener implements javax.servlet.ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
        System.out.println("app is starting...");
      	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Web application is shutting down...");

		
	}

}
