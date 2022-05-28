package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import main.db.JdbcSpitterRepository;
import main.db.JdbcSpittleRepository;
import main.domain.Spitter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
//在JUnit4的测试方法中打事务注解@Transactional，默认会按照@Rollback(true)来进行处理，无论如何都会回滚
public class JdbcTemplateTest {

	@Autowired
	JdbcSpitterRepository spitterRepository;
	@Autowired
	JdbcSpittleRepository spittleRepository;

	@Test
	@Transactional(readOnly=true)
	public void findAllByJDBC() {
		List<Spitter> spitters = spitterRepository.findAllByJDBC();
		assertEquals(1, spitters.size());
	}

	@Test
	@Transactional(readOnly=true)
	public void findAll1() {
		List<Spitter> spitters = spitterRepository.findAll1();
		assertEquals(4, spitters.size());
	}

	@Test
	@Transactional(readOnly=true)
	public void findAll2() {
		List<Spitter> spitters = spitterRepository.findAll2();
		assertEquals(4, spitters.size());
	}

	@Test
	@Transactional(readOnly=true)
	public void findAll3() {
		List<Spitter> spitters = spitterRepository.findAll3();
		assertEquals(4, spitters.size());
	}

	@Test
	@Transactional(readOnly=true)
	public void findAll4() {
		List<Spitter> spitters = spitterRepository.findAll4();
		assertEquals(1, spitters.size());
	}

	@Test
	@Transactional(readOnly=true)
	public void count() {
		assertEquals(4, spitterRepository.count());
	}

	@Test
	@Transactional(readOnly=true)
	public void findByUsername() {
		Spitter spitter=spitterRepository.findByUsername("habuma", "password");
		assertNotNull(spitter);
	}

	@Test
	@Transactional(readOnly=true)
	public void findOne() {
		assertSpitter(0, spitterRepository.findOne(1L));
		assertSpitter(1, spitterRepository.findOne(2L));
		assertSpitter(2, spitterRepository.findOne(3L));
		assertSpitter(3, spitterRepository.findOne(4L));
	}

	private static void assertSpitter(int expectedSpitterIndex, Spitter actual) {
		assertSpitter(expectedSpitterIndex, actual, "Newbie");
	}

	private static void assertSpitter(int expectedSpitterIndex, Spitter actual, String expectedStatus) {
		Spitter expected = SPITTERS[expectedSpitterIndex];
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getUsername(), actual.getUsername());
		assertEquals(expected.getPassword(), actual.getPassword());
		assertEquals(expected.getFullName(), actual.getFullName());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.isUpdateByEmail(), actual.isUpdateByEmail());
	}

	private static Spitter[] SPITTERS = new Spitter[6];

	@BeforeClass
	public static void before() {
		SPITTERS[0] = new Spitter(1L, "habuma", "password", "Craig Walls", "craig@habuma.com", false);
		SPITTERS[1] = new Spitter(2L, "mwalls", "password", "Michael Walls", "mwalls@habuma.com", true);
		SPITTERS[2] = new Spitter(3L, "chuck", "password", "Chuck Wagon", "chuck@habuma.com", false);
		SPITTERS[3] = new Spitter(4L, "artnames", "password", "Art Names", "art@habuma.com", true);
		SPITTERS[4] = new Spitter(5L, "newbee", "letmein", "New Bee", "newbee@habuma.com", true);
		SPITTERS[5] = new Spitter(4L, "arthur", "letmein", "Arthur Names", "arthur@habuma.com", false);
	}

	@Test
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public void save_newSpitter() {
		assertEquals(4, spitterRepository.count());
		Spitter spitter = new Spitter(null, "newbee", "letmein", "New Bee", "newbee@habuma.com", true);
		Spitter saved = spitterRepository.save(spitter);
		assertNotNull(saved);
	}

	@Test
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public void save_existingSpitter() {
		assertEquals(4, spitterRepository.count());
		Spitter spitter = new Spitter(4L, "arthur", "letmein", "Arthur Names", "arthur@habuma.com", false);
		Spitter saved = spitterRepository.save(spitter);
		assertNotNull(saved);
	}
	
	@Test
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	//在测试用例类上或者测试方法上@Rollback(false),可以禁止回滚
	//@Rollback(false)
	public void insertSpitter()
	{
		Spitter spitter = new Spitter(null, "lhz", "1111", "New Bee", "newbee@habuma.com", true);
		spitterRepository.insertSpitter(spitter);
		Spitter inserted = spitterRepository.findByUsername("lhz", "1111");
		assertNotNull(inserted);
	}
	@Test
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)	
	public void deleteSpitter()
	{
		spitterRepository.deleteSpitter("chuck");
		Spitter deleted = spitterRepository.findByUsername("chuck", "password");
		assertNull(deleted);
	}
}