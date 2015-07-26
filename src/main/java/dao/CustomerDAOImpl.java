package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import Service.Impl.CustomerImplBuilder;
import model.Customer;

@Component
public class CustomerDAOImpl implements CustomerDAO {

	private Connection conn;
	
	public CustomerDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	private Connection getConn() {
		return conn;
	}

	private void setConn(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public String insert(Customer cust) throws Exception{		
		String sql = "INSERT INTO CUSTOMER_PROFILE(NAME,EMAIL)VALUES(?,?)";
		PreparedStatement  ps  = this.getConn().prepareStatement(sql);
		ps.setString(1, cust.getName());
		ps.setString(2, cust.getEmail());
		int status = ps.executeUpdate();
		
		return status == -1 ? "fail" : "success";
	}

	@Override
	public Customer query(int id) throws Exception{
		String sql = "SELECT * FROM CUSTOMER_PROFILE WHERE id =?";
		PreparedStatement  ps  = this.getConn().prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Customer aCustomer = null;
		String name = "";
		String email = "";
		
		if(rs.next()){
			name = rs.getString("Name");
			email = rs.getString("email");
			aCustomer = CustomerImplBuilder.newBuilder()
					.createName(name)
					.createEmail(email)
					.build();
		}
		return aCustomer;
	}
	@Override
	public String delete(int id) throws Exception{
		String sql = "DELETE  FROM CUSTOMER_PROFILE WHERE id =?";
		PreparedStatement  ps  = this.getConn().prepareStatement(sql);
		ps.setLong(1, id);
		int  status = ps.executeUpdate();
		return  status == -1 ? "fail" : "success";
	}
	
	@Override
	public String update(Customer aCustomer) throws Exception{
		String sql = "update CUSTOMER_PROFILE SET name = ? , email = ?  WHERE id =?";
		PreparedStatement  ps  = this.getConn().prepareStatement(sql);
		ps.setString(1, aCustomer.getName());
		ps.setString(2, aCustomer.getEmail());
		ps.setLong(3, aCustomer.getId());
		int  status = ps.executeUpdate();
		return  status == -1 ? "fail" : "success";
	}

}
