package org.hibernate.bugs;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;
import org.hibernate.bugs.entity.Child;
import org.hibernate.bugs.entity.ChildToy;
import org.hibernate.bugs.entity.Parent;
import org.hibernate.bugs.entity.ParentToy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private static final Logger log = LoggerFactory.getLogger(JPAUnitTestCase.class);
	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.

	@Before
	public void persistData() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(createParent());
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Parent createParent() {
		Parent parent = new Parent();
		Set parentToys = new HashSet<>();
		parentToys.add(new ParentToy());
		parent.setToys(parentToys);

		Child child = new Child();
		Set<ChildToy> childToys = new HashSet<>();
		childToys.add(new ChildToy());
		parent.setChild(child);
		return parent;
	}

	@Test
	public void testEntityGraph() throws Exception {
		System.out.println("testEntityGraph start");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		EntityGraph<?> entityGraph = entityManager.getEntityGraph(Parent.PARENT_WITH_TOYS_AND_CHILD_GRAPH);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("javax.persistence.fetchgraph", entityGraph);
		Parent parent = entityManager.find(Parent.class, 1l, properties);

		Assert.assertTrue(Hibernate.isPropertyInitialized(parent, "toys"));
		Assert.assertTrue(Hibernate.isPropertyInitialized(parent, "child"));
		Assert.assertFalse(Hibernate.isPropertyInitialized(parent.getChild(), "toys"));
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
