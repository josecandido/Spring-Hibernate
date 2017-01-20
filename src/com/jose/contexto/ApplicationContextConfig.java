package com.jose.contexto;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jose.dao.PedidosDAO;
import com.jose.dao.PedidosDAOImple;
import com.jose.dao.ProductosDAO;
import com.jose.dao.ProductosDAOImple;
import com.jose.modelo.Producto;
import com.jose.modelo.Pedido;

@Configuration
@ComponentScan("com.jose")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContextConfig {

	 @Bean(name = "viewResolver")
	    public InternalResourceViewResolver getViewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;
	    }
	 
	    @Bean(name = "dataSource")
	    public DataSource getDataSource() {
	    	BasicDataSource dataSource = new BasicDataSource();
	    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    	dataSource.setUrl("jdbc:mysql://localhost:3306/amazon");
	    	dataSource.setUsername("root");
	    	dataSource.setPassword("");
	    	return dataSource;
	    }
	    
	    private Properties getHibernateProperties() {
	    	Properties properties = new Properties();
	    	properties.put("hibernate.show_sql", "true");
	    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    	return properties;
	    }
	    
	    @Autowired
	    @Bean(name = "sessionFactory")
	    public SessionFactory getSessionFactory(DataSource dataSource) {
	    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    	sessionBuilder.addProperties(getHibernateProperties());
	    	sessionBuilder.addAnnotatedClasses(Producto.class);
	    	sessionBuilder.addAnnotatedClasses(Pedido.class);
	    	return sessionBuilder.buildSessionFactory();
	    }
	    
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			return transactionManager;
		}
	    
	    @Autowired
	    @Bean(name = "productosDao")
	    public ProductosDAO getProductoDao(SessionFactory sessionFactory) {
	    	return new ProductosDAOImple(sessionFactory);
	    }
	    
	    @Autowired
	    @Bean(name = "pedidosDao")
	    public PedidosDAO getPedidoDao(SessionFactory sessionFactory) {
	    	return new PedidosDAOImple(sessionFactory);
	    }
	    
}