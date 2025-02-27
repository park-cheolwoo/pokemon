package kr.co.pokemon.data.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class DataServiceImpl implements DataService {
	
	@Value("${poketmon.run-init-sql}")
	private boolean isRunInitSql; 
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	@PostConstruct
	public void initTable() throws SQLException {
		if (isRunInitSql) {
			Connection conn = dataSource.getConnection();
			ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/createTable.sql"));
		}
	}

}
