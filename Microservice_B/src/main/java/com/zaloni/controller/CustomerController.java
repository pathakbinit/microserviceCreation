package com.zaloni.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zaloni.Conn.AccountClient;
import com.zaloni.microservice.vo.Account;
import com.zaloni.microservice.vo.Customer;
import com.zaloni.microservice.vo.CustomerType;

@RestController
public class CustomerController {
	@Autowired
	private AccountClient accountClient;

	protected Logger logger = Logger.getLogger(CustomerController.class.getName());

	private List<Customer> customers;

	public CustomerController() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
		customers.add(new Customer(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
	}

	@RequestMapping(value = "/a", method = RequestMethod.GET)
	public String h() {
		return "hi";
	}

	@RequestMapping("/customers/pesel/{pesel}")
	public Customer findByPesel(@PathVariable("pesel") String pesel) {
		logger.info(String.format("Customer.findByPesel(%s)", pesel));
		return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();
	}

	@RequestMapping("/customers")
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		return customers;
	}

	@RequestMapping("/customers/{id}")
	public Customer findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> it.getId().intValue() == id.intValue()).findFirst().get();
		List<Account> accounts = accountClient.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}

}
