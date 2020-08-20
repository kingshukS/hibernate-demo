package com.kingshuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate-config.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("begining transaction...");
			session.beginTransaction();
			Student student = session.get(Student.class, 2);
			session.getTransaction().commit();
			System.out.println("ending the transaction..."+student);
			
		}finally {
			session.close();
			factory.close();
		}
		

	}

}
