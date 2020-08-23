package com.kingshuk.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// Required variables for operation
		SessionFactory factory = new Configuration().configure("hibernate-config.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		String lastNameSubstring = "Paul";
		String desiredEmail = "abc@xyz.com";
		String emailSubString = "@gmail.com";
		String desiredEmailSubstring = "official@xyz.com";
		
		//Calling methods from try-finally, in-case, we need to close the factory during exceptions
		try {
			updateAStudentWithLastName(factory, lastNameSubstring, desiredEmail);
			updateAllStudentsWithEmail(factory,emailSubString, desiredEmailSubstring);

		} finally {
			factory.close();
		}

	}

	private static void updateAllStudentsWithEmail(SessionFactory factory, String emailSubString,
			String desiredEmailSubstring) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			int updatedRows = session.createQuery("Update Student s set s.email = '"+desiredEmailSubstring+"' where s.email LIKE '%"+emailSubString+"'").executeUpdate();
			System.out.println("Updated Rows : "+updatedRows);
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	private static void updateAStudentWithLastName(SessionFactory factory, String lastNameSubstring,
			String desiredEmail) {

		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			List<Student> studentList = session
					.createQuery("from Student s where s.lastName like '%" + lastNameSubstring + "'").getResultList();
			Student student = studentList.get(0);
			student.setEmail(desiredEmail);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

}
