package com.csi.dao;

import com.csi.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDaoImpl implements CustomerDao {

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void saveData(Customer customer) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(customer);

        transaction.commit();
    }

    @Override
    public Customer getDataById(int custId) {

    Session session = factory.openSession();
    return (Customer) session.get(Customer.class,custId);


    }

    @Override
    public List<Customer> getAllData() {

        Session session = factory.openSession();
        return session.createQuery("from Customer").list();
    }

    @Override
    public void updateData(int custId,Customer customer) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

       Customer customer1= getDataById(custId);

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustAccountBalance(customer.getCustAccountBalance());
        customer1.setCustDOB(customer.getCustDOB());

        session.update(customer);

        transaction.commit();
    }

    @Override
    public void deleteDataById(int custId) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        Customer customer1 = getDataById(custId);

        session.delete(customer1);

        transaction.commit();

    }
}
