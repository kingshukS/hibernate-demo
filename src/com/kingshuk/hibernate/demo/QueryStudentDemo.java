package com.kingshuk.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kingshuk.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate-config.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		String emailSubstring = "@gmail.com";
		String lastNameSubstring = "Paul";

		try {
			queryAllStudents(factory);
			queryStudentsWithEmail(factory, emailSubstring);
			queryStudentsWithLastName(factory, lastNameSubstring);
		} finally {
			factory.close();
		}

	}

	private static void queryStudentsWithLastName(SessionFactory factory, String lastNameSubstring) {

		Session session = factory.getCurrentSession();

		try {
			System.out.println("\nbegining transaction...");
			session.beginTransaction();
			List<Student> studentList = session
					.createQuery("from Student s where s.lastName LIKE '%" + lastNameSubstring + "'").getResultList();
			session.getTransaction().commit();
			System.out.println("ending the transaction...");
			for (Student s : studentList) {
				System.out.println(s.getId() + ": " + s);
			}

		} finally {
			session.close();
		}

	}

	private static void queryStudentsWithEmail(SessionFactory factory, String emailSubstring) {
		Session session = factory.getCurrentSession();

		try {
			System.out.println("\nbegining transaction...");
			session.beginTransaction();
			List<Student> studentList = session
					.createQuery("from Student s where s.email LIKE '%" + emailSubstring + "'").getResultList();
			session.getTransaction().commit();
			System.out.println("ending the transaction...");
			for (Student s : studentList) {
				System.out.println(s.getId() + ": " + s);
			}

		} finally {
			session.close();
		}

	}

	private static void queryAllStudents(SessionFactory factory) {
		Session session = factory.getCurrentSession();

		try {
			System.out.println("begining transaction...");
			session.beginTransaction();
			List<Student> studentList = session.createQuery("from Student").getResultList();
			session.getTransaction().commit();
			System.out.println("ending the transaction...");
			for (Student s : studentList) {
				System.out.println(s.getId() + ": " + s);
			}

		} finally {
			session.close();
		}
	}

}
