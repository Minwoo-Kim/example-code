package com.example.code.cassandra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.cql.jdbc.CassandraDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SuppressWarnings("all")
public class CassandraJdbcMain {
	public static void main(String[] args) {

	}

	public void connectByBasicDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.apache.cassandra.cql.jdbc.CassandraDriver");
		ds.setUrl("jdbc:cassandra://10.211.55.6:9160/cycling");
		ds.setUsername("cassandra");
		ds.setPassword("cassandra");
		ds.setMaxActive(30);
		ds.setMinIdle(10);
		ds.setMaxIdle(10);
		ds.setMaxWait(-1);
		ds.setTimeBetweenEvictionRunsMillis(1800000L);

		NamedParameterJdbcTemplate jdbcTempl = new NamedParameterJdbcTemplate(ds);
		List<Map<String, Object>> list = jdbcTempl.queryForList("select * from emp", new HashMap<String, Object>());

		System.out.println("END");
	}

	public void connectByCassandraDataSource() {
		String host = "10.211.55.6";
		int port = 9160;

		String keyspace = "cycling";
		String user = "cassandra";
		String password = "cassandra";
		String version = null;
		String consistency = null;
		//
		CassandraDataSource dataSource = new CassandraDataSource(host, port, keyspace, user, password, version, consistency);
		NamedParameterJdbcTemplate jdbcTempl = new NamedParameterJdbcTemplate(dataSource);

		List<Map<String, Object>> list = jdbcTempl.queryForList("select * from emp", new HashMap<String, Object>());

		System.out.println("END");
	}
}