package introsde.storage.model;

import introsde.storage.dao.LifeCoachDao;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.Person;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the "Person" database table.
 * 
 */
@Entity
@Table(name = "Person")
@NamedQueries({
		@NamedQuery(name = "Person.findPeopleRange", query = "SELECT p FROM Person p, LifeStatus lf where lf.person = p and lf.measureDefinition.measureName = :type and lf.value >= :minValue and lf.value <= :maxValue"),
		@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p") })
//@XmlRootElement(name="people")
//@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888564417005646353L;

	@Id
	@Column(name = "idPerson")
	private Long idPerson;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "name")
	private String name;

	@Column(name = "username")
	private String username;

	@Column(name = "birthdate")
	private String birthdate;

	@Column(name = "email")
	private String email;

	// mappedBy must be equal to the name of the attribute in LifeStatus that
	// maps this relation
	@OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
//	@XmlElement(name="measure", type=LifeStatus.class)
//	@XmlElementWrapper(name = "Measurements")
	private List<LifeStatus> lifeStatus;

	// mappedBy must be equal to the name of the attribute in LifeStatus that
	// maps this relation
	@OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
//	@XmlElement(name="goal", type=Goal.class)
//	@XmlElementWrapper(name = "Goals")
	private List<Goal> goals;

	public Person() {
	}


	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	public String getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// the XmlElementWrapper defines the name of node in which the list of
	// LifeStatus elements
	// will be inserted
	public List<LifeStatus> getLifeStatus() {
		return lifeStatus;
	}

	public void setLifeStatus(List<LifeStatus> param) {
		this.lifeStatus = param;
	}

	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on
	// each operation.
	// How would you change the DAO to not having to create the entity manager
	// every time?
	public static Person getPersonById(Long personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static List<Person> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
				.getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	public static Person savePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p = em.merge(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static Person updatePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p = em.merge(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static void removePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p = em.merge(p);
		em.remove(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}

	public static List<Person> getPeopleWithinRangeMeasure(String measureType,
			String maxValue, String minValue) {

		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Person> listPersons = em
				.createNamedQuery("Person.findPeopleRange", Person.class)
				.setParameter("type", measureType)
				.setParameter("maxValue", maxValue)
				.setParameter("minValue", minValue).getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return listPersons;
	}

}
