package com.railway.controller.listener;

import com.railway.model.pool.CustomConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * The Servlet context listener. It initializes the connection pool when the application starts and destroys the connection pool when the application shuts down.
 */
public class ServletContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CustomConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomConnectionPool.getInstance().destroyPull();
    }

}
