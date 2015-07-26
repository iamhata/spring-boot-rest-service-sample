package Service.Impl;

import model.Customer;
import Service.CustomerBuilder;

public class CustomerImplBuilder implements CustomerBuilder {
	private static Customer _customer;
	
	
	public CustomerImplBuilder(){
		_customer = new Customer(); 
	}
	
	public static CustomerBuilder newBuilder(){
		return new CustomerImplBuilder();
	}
	
	@Override
	public CustomerImplBuilder createId(int id) {
		_customer.setId(id);
		return this;
	}

	@Override
	public CustomerImplBuilder createName(String name) {
		_customer.setName(name);
		return this;
		
	}

	@Override
	public CustomerImplBuilder createEmail(String email) {
		_customer.setEmail(email);
		return this;
	}
	
	@Override
	public Customer build(){
		return _customer;
	}
	
}
