package com.kingshuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
									.configure("hibernate-config.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("begining transaction...");
			Student student1 = new Student("Prashun", "Dey", "prasun.dey@gmail.com");
//			Student student2 = new Student("Ankur", "Bhartiya", "bhartiay.05@gmail.com");
//			Student student3 = new Student("Nehal", "Gupta", "nehalgupta2277@gmail.com");
			session.beginTransaction();
			System.out.println(student1.getId());
			session.save(student1);
			System.out.println(student1.getId());
//			session.save(student2);
//			session.save(student3);
			session.getTransaction().commit();
			System.out.println("ending the transaction..."+student1);
			
		}finally {
			session.close();
			factory.close();
		}
		

	}

}
