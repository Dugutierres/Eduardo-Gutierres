package edu.projeto_barbearia.estrutura.persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.projeto_barbearia.estrutura.util.VariaveisProjeto;

public class ConexaoBancoDados {

	
	
	private static ConexaoBancoDados CONEXAO_BANCO_DADOS;
	private static EntityManagerFactory FABRICA_CONEXAO;
	
	private ConexaoBancoDados() {
		
		if (FABRICA_CONEXAO == null) {
			FABRICA_CONEXAO = getCriarFabricaconexao();
		}
	}

	public static ConexaoBancoDados getConexaoBancodados() {
		
		if (CONEXAO_BANCO_DADOS == null) {
			CONEXAO_BANCO_DADOS = new ConexaoBancoDados();
		}
		
		return CONEXAO_BANCO_DADOS;
	}
	
	public EntityManager getEntityManager() {
		
		return FABRICA_CONEXAO.createEntityManager();
	}
	
	
	private EntityManagerFactory getCriarFabricaconexao() {
		
		Map<String, String> properties = new HashMap<String, String>();
		
		properties.put("javax.persistence.schema-generation.database.action","drop-and-create");
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
		properties.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/indicadores?createDatabaseIfNotExist=true&useSSL=false");
		properties.put("hibernate.connection.username", "root");
		properties.put("hibernate.connection.password", "root");
		properties.put("hibernate.c3p0.min_size", "10");
		properties.put("hibernate.c3p0.max_size" ,"20" );
		properties.put("hibernate.c3p0.acquire_increment","1");
		properties.put("hibernate.c3p0.idle_test_period" ,"30000");
		properties.put("hibernate.c3p0.max_statements","50" );
		properties.put("hibernate.c3p0.timeout","1800" );
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("useUnicode", "true");
		properties.put("characterEncoding", "UTF-8");
		properties.put("hibernate.default_schema", "projeto_barbearia");
		
		return Persistence.createEntityManagerFactory(VariaveisProjeto.PERSISTENCE_UNIT_NAME, properties);
	}
}
