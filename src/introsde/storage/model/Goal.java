package introsde.storage.model;

import introsde.storage.dao.LifeCoachDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Goal")
@NamedQueries({
		@NamedQuery(name = "Goal.getGoalsOfPerson", query = "SELECT m FROM Goal m where m.person = :person"),
		@NamedQuery(name = "Goal.getGoalsOfPersonDeadlineType", query = "SELECT m FROM Goal m where m.person = :person and m.deadline = :deadline and m.measureDefinition.measureName = :type"),
		@NamedQuery(name = "Goal.getGoalsOfPersonForMeasureType", query = "SELECT m FROM Goal m where m.person = :person and m.measureDefinition.measureName = :type") })
//@XmlRootElement
public class Goal {

	@Id
	@GeneratedValue
	@Column(name = "idGoal")
	private int idGoal;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef")
	private MeasureDefinition measureDefinition;

	@Column(name = "value")
	private String value;

	@Column(name = "deadline")
	private Long deadline;

	@ManyToOne
	@JoinColumn(name = "idPerson", referencedColumnName = "idPerson")
	private Person person;

	public int getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
	}

	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getDeadline() {
		return deadline;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}

	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public static List<Goal> getGoalsOfPerson(Long idPerson) {

		Person person = Person.getPersonById(idPerson);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> list = null;

		try {
			list = em
					.createNamedQuery("Goal.getGoalsOfPerson", Goal.class)
					.setParameter("person", person).getResultList();
			LifeCoachDao.instance.closeConnections(em);

		} catch (NoResultException e) {
			return null;
		}

		if (list.size()<1) {
			return null;
		}

		return list;

	}

	public static Goal getGoalOfPersonForMeasureType(Long id, String measureType) {
		Person person = Person.getPersonById(id);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> listGoal = null;

		listGoal = em
				.createNamedQuery("Goal.getGoalsOfPersonForMeasureType",
						Goal.class).setParameter("person", person)
				.setParameter("type", measureType).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		if (listGoal != null) {
			return listGoal.get(0);
		} else {
			return null;
		}
	}

	public static Goal saveGoal(Goal newGoal) {

		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		newGoal = em.merge(newGoal);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return newGoal;
	}

	public static Goal getGoalForPersonDeadlineType(Long id, Long deadline,
			String measureType) {
		Person person = Person.getPersonById(id);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> listGoal = null;

		listGoal = em
				.createNamedQuery("Goal.getGoalsOfPersonDeadlineType",
						Goal.class).setParameter("person", person)
				.setParameter("deadline", deadline)
				.setParameter("type", measureType).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		if (listGoal.size()>0) {
			return listGoal.get(0);
		} else {
			return null;
		}

	}

}
