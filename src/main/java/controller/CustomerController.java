package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.Impl.CustomerImplBuilder;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    
    private Connection conn = null;
    
    @RequestMapping( method=RequestMethod.GET)
    public Customer getCustomer(@RequestParam(value="id") int id) throws Exception{	
    	Customer aCustomer = null;
    	log.info("QUERY ID:"+String.valueOf(id));
    	try {
    		Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
    		CustomerDAOImpl d = new CustomerDAOImpl(conn);
	    	aCustomer = d.query(id);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}

    	return aCustomer;
    }
   
    @RequestMapping( method=RequestMethod.POST)
    public String addCustomer(@RequestParam(value="name") String name,@RequestParam(value="email") String email) throws Exception{
    	log.info("INSERT NAME:"+name);
    	String status = "";
    	
    	try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
			CustomerDAOImpl d = new CustomerDAOImpl(conn);
	    	Customer aCustomer = CustomerImplBuilder.newBuilder()
	    							.createName(name)
	    							.createEmail(email)
	    							.build();
	    	status = d.insert(aCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}

        return status;
    }
    
    
    @RequestMapping( method=RequestMethod.DELETE)
    public String deleteCustomer(@RequestParam(value="id") int id) throws Exception{	
    	Customer aCustomer = null;
    	log.info("Delete id:"+String.valueOf(id));
    	
    	String status = "";    	
    	try {
    		Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
    		CustomerDAOImpl d = new CustomerDAOImpl(conn);
	    	status= d.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
    	return status;
    }
   
    @RequestMapping( method=RequestMethod.PUT)
    public String updateCustomer(@RequestParam(value="name") String name,@RequestParam(value="email") String email,@RequestParam(value="id") int id) throws Exception{	
    	Customer aCustomer = null;
    	log.info("update Customer:"+String.valueOf(name));
    	 
    	String status = "";
    	try {
    		Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
    		CustomerDAOImpl d = new CustomerDAOImpl(conn);
    		aCustomer = CustomerImplBuilder.newBuilder()
					.createName(name)
					.createEmail(email)
					.createId(id)
					.build();
    		
    		status = d.update(aCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
    	return status;
    }
}
