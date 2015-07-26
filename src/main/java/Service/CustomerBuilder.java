package Service;

import model.Customer;

public interface CustomerBuilder {
	CustomerBuilder createId(int id);
	CustomerBuilder createName(String name);
	CustomerBuilder createEmail(String email);
	Customer build();
}
