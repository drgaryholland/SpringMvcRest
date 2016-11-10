package com.holland.spring.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.holland.spring.hibernate.BaseHibernateDAO;
import com.holland.spring.model.Customer;

@Component
public class CustomerDAO extends BaseHibernateDAO {

	private static final Log log = LogFactory.getLog(CustomerDAO.class);
	
	private static List<Customer> customers;
	
	public List<Customer> list(){
		List<Customer> list = null;
		Session session = getSession();
		
		Query queryObject = session.createQuery("from Customer");
		
		try {
			list = queryObject.list();
		} catch (Exception e) {
			if (null == list){System.out.println("List is null");}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (session.isOpen() && session != null){
				session.close();
			}
		}
		return list;
	}

	public Customer get(BigDecimal id){
		Customer customer = new Customer();
		Session session = getSession();

		try {
			customer = (Customer) session.get(Customer.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (session.isOpen() && session != null){
				session.close();
			}
		}
		
		
		
		return customer;
	}
	
	public BigDecimal delete(BigDecimal id){
		if (id == null){
			return id;
		}
		Session session = getSession();
		Query queryObject = session.createQuery("delete Customer where id = :id").setBigDecimal("id", id);
		try {
			queryObject.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (session.isOpen() && session != null){
				session.close();
			}
		}
		
		return id;
		
	}
	
	public void create(Customer customer){
		if (customer == null){
			return;
		}
		Session session = getSession();
		
		try {
			session.save(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (session.isOpen() && session != null){
				session.close();
			}
		}
	}

	public Customer update(BigDecimal id,Customer customer){
		if (customer == null){
			return customer;
		}
		Session session = getSession();
		
		try {
			session.update(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (session.isOpen() && session != null){
				session.close();
			}
		}
		return customer;
	}
}
