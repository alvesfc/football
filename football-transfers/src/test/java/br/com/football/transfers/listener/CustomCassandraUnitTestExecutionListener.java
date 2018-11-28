package br.com.football.transfers.listener;

import org.springframework.test.context.TestContext;

public class CustomCassandraUnitTestExecutionListener extends CustomAbstractCassandraUnitTestExecutionListener {

    @Override
    public void beforeTestClass (TestContext testContext) throws Exception {
        startServer(testContext);
    }
}
