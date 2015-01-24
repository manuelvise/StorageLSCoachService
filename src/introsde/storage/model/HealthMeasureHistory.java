package introsde.storage.model;

import introsde.storage.dao.LifeCoachDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the "HealthMeasureHistory" database table.
 * 
 */
@Entity
@Table(name = "HealthMeasureHistory")
@NamedQueries({
		@NamedQuery(name = "HealthMeasureHistory.findAll", query = "SELECT h FROM HealthMeasureHistory h"),
		@NamedQuery(name = "HealthMeasureHistory.findLifeStatusOfPersonForMeasure", query = "SELECT m FROM HealthMeasureHistory m where m.person = :person and m.measureDefinition.measureName = :type"),
		@NamedQuery(name = "HealthMeasureHistory.findMeasuresFromTo", query = "SELECT m FROM HealthMeasureHistory m where m.person = :idPerson and m.measureDefinition.measureName = :type and m.timestamp >= :from and m.timestamp <= :to"),
		@NamedQuery(name = "HealthMeasureHistory.findLifeStatusOfPersonForidM", query = "SELECT m FROM HealthMeasureHistory m where m.person = :person and m.measureDefinition.measureName = :type and m.idMeasureHistory = :idM") })
@XmlRootElement
public class HealthMeasureHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// @TableGenerator(name="sqlite_mhistory", table="sqlite_sequence",
	// pkColumnName="name", valueColumnName="seq",
	// pkColumnValue="HealthMeasureHistory")
	@Column(name = "idMeasureHistory")
	private int idMeasureHistory;

	@Column(name = "timestamp")
	private Long timestamp;

	@Column(name = "value")
	private String value;

	@ManyToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef", insertable = true, updatable = true)
	private MeasureDefinition measureDefinition;

	// notice that we haven't included a reference to the history in Person
	// this means that we don't have to make this attribute XmlTransient
	@ManyToOne
	@JoinColumn(name = "idPerson", referencedColumnName = "idPerson")
	private Person person;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition measureDefinition) {
		this.measureDefinition = measureDefinition;
	}

	public HealthMeasureHistory() {
	}

	public int getIdMeasureHistory() {
		return this.idMeasureHistory;
	}

	public void setIdMeasureHistory(int idMeasureHistory) {
		this.idMeasureHistory = idMeasureHistory;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person param) {
		this.person = param;
	}

	// database operations
	public static HealthMeasureHistory getHealthMeasureHistoryById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		HealthMeasureHistory p = em.find(HealthMeasureHistory.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static List<HealthMeasureHistory> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<HealthMeasureHistory> list = em.createNamedQuery(
				"HealthMeasureHistory.findAll", HealthMeasureHistory.class)
				.getResultList();
		LifeCoachDao.instance.closeConnections(em);
		return list;
	}

	public static HealthMeasureHistory saveHealthMeasureHistory(
			HealthMeasureHistory p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}

	public static HealthMeasureHistory updateHealthMeasureHistory(
			HealthMeasureHistory p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p = em.merge(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return p;
	}

	public static void removeHealthMeasureHistory(HealthMeasureHistory p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p = em.merge(p);
		em.remove(p);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}

	public static List<HealthMeasureHistory> getLifeStyleOfPersonForMeasure(
			Long idPerson, String type) {

		
			Person person = Person.getPersonById(idPerson);
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			List<HealthMeasureHistory> list = null;	
	
			list = em
					.createNamedQuery(
							"HealthMeasureHistory.findLifeStatusOfPersonForMeasure",
							HealthMeasureHistory.class)
					.setParameter("person", person).setParameter("type", type)
					.getResultList();
			LifeCoachDao.instance.closeConnections(em);
			
		return list;
		

	}

	public static HealthMeasureHistory getLifeStyleOfPersonForIdM(
			Long idPerson, String type, int idMeasure) {

		Person person = Person.getPersonById(idPerson);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		HealthMeasureHistory measure = em
				.createNamedQuery(
						"HealthMeasureHistory.findLifeStatusOfPersonForidM",
						HealthMeasureHistory.class)
				.setParameter("person", person).setParameter("type", type)
				.setParameter("idM", idMeasure).getSingleResult();
		LifeCoachDao.instance.closeConnections(em);
		return measure;
	}

	public static List<HealthMeasureHistory> getHistoryMeasuresFromToOfTypeForPerson(
			Long idPerson, String type, Long from, Long to) {

		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<HealthMeasureHistory> measuresFromTo = em
				.createNamedQuery("HealthMeasureHistory.findMeasuresFromTo",
						HealthMeasureHistory.class)
				.setParameter("idPerson", Person.getPersonById(idPerson))
				.setParameter("type", type).setParameter("from", from)
				.setParameter("to", to).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return measuresFromTo;
	}

}
