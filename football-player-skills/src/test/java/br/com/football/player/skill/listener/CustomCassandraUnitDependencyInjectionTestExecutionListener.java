package br.com.football.player.skill.listener;

import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


public class CustomCassandraUnitDependencyInjectionTestExecutionListener extends
        CustomAbstractCassandraUnitTestExecutionListener {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CassandraUnitDependencyInjectionTestExecutionListener.class);

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        startServer(testContext);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (Boolean.TRUE.equals(testContext
                .getAttribute(DependencyInjectionTestExecutionListener.REINJECT_DEPENDENCIES_ATTRIBUTE))) {
            LOGGER.debug("Cleaning and reloading server for test context [{}]", testContext);
            cleanServer();
            startServer(testContext);
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) {
    }
}
