package br.com.football.players;

import org.springframework.test.context.jdbc.Sql;

@Sql(value = "classpath:shouldDeletePlayer.sql")
public abstract class SuccessDeleteBase extends SuccessBase {


}
