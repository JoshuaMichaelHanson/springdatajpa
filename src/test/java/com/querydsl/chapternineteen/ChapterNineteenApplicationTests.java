package com.querydsl.chapternineteen;

import com.querydsl.chapternineteen.model.QUser;
import com.querydsl.chapternineteen.model.User;
import com.querydsl.chapternineteen.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import static com.querydsl.chapternineteen.GeneratedUsers.generateUsers;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChapterNineteenApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceUnit()
	private EntityManagerFactory entityManagerFactory;

	private JPAQueryFactory queryFactory;
	/**
	@Test
	void contextLoads() {
	}
	 */

	@BeforeAll
	void beforeAll() {
		System.out.println("JMH::beforeAll");
		userRepository.saveAll(generateUsers());
		System.out.println("JMH::after::userRepository.saveAll(generateUsers())");
	}

	@BeforeEach
	void beforeEach() {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		queryFactory = new JPAQueryFactory(entityManager);
	}

	@AfterEach
	void afterEach() {
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@AfterAll
	void afterAll() {
		userRepository.deleteAll();
	}

	// get one test working then commit to GitHub to move computers upstairs to watch the TV
	@Test
	void testFindByUsername() {
		User fetchedUser = queryFactory.selectFrom(QUser.user)
				.where(QUser.user.username.eq("john"))
				.fetchOne();

		assertAll(
				() -> assertNotNull(fetchedUser),
				() -> assertEquals("john", fetchedUser.getUsername()),
				() -> assertEquals("John", fetchedUser.getFirstName()),
				() -> assertEquals("Smith", fetchedUser.getLastName()),
				() -> assertEquals(2, fetchedUser.getBids().size())
		);
	}
}
