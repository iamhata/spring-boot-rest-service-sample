package dao;

import model.Customer;

public interface CustomerDAO {
	public String insert(Customer aCust) throws Exception;
	public Customer query(int id)throws Exception;
	public String delete(int id)throws Exception;
	public String update(Customer aCust)throws Exception;
}
