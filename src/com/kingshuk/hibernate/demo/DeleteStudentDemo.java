package com.kingshuk.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			if(student!=null)                                  //to avoid illegal-argument exception
				session.delete(student);
			session.getTransaction().commit();
			System.out.println("ending the transaction...");
			session.close();
			
			session = factory.getCurrentSession();
			System.out.println("begining transaction...2");
			session.beginTransaction();
			int updatedRows = session.createQuery("delete from Student s where s.firstName ='Prashun'").executeUpdate();
			System.out.println("Deleted rows : "+updatedRows);
			session.getTransaction().commit();
			System.out.println("ending the transaction...2 : ");
			
		}finally {
			session.close();
			factory.close();
		}
		

	}

}
