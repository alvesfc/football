package br.com.football.player.skill.listener;

import org.springframework.test.context.TestContext;



public class CustomCassandraUnitTestExecutionListener extends CustomAbstractCassandraUnitTestExecutionListener {

    @Override
    public void beforeTestClass (TestContext testContext) throws Exception {
        startServer(testContext);
    }
}
