package br.com.football.transfers;


import br.com.football.transfers.listener.CustomCassandraUnitDependencyInjectionTestExecutionListener;
import br.com.football.transfers.listener.CustomCassandraUnitTestExecutionListener;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Classe abstrata que adiciona os Listners necessários para iniciar o cassandra Embedded
 * utilizando o springBoot.
 */
@CassandraUnit
@TestExecutionListeners({ CustomCassandraUnitTestExecutionListener.class,
        CustomCassandraUnitDependencyInjectionTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class})
@EmbeddedCassandra
public abstract class CassandraSpringBoot {

}
