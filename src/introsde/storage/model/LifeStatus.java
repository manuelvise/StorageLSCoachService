package introsde.storage.model;

import introsde.storage.dao.LifeCoachDao;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.MeasureDefinition;
import introsde.storage.model.Person;

import java.io.Serializable;
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
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.OneToOne;

/**
 * The persistent class for the "LifeStatus" database table.
 * 
 */
@Entity
@Table(name = "LifeStatus")
@NamedQueries({
@NamedQuery(name = "LifeStatus.findAll", query = "SELECT l FROM LifeStatus l"),
@NamedQuery(name = "LifeStatus.findLifeStatusOfPersonForMeasure", query = "SELECT m FROM LifeStatus m where m.person = :person and m.measureDefinition = :type"),
@NamedQuery(name = "LifeStatus.findLifeStatusOfPersonForidM", query = "SELECT m FROM LifeStatus m where m.person = :person and m.measureDefinition = :type and m.idMeasure = :idM")})
@XmlRootElement(name="Measure")
public class LifeStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
//	@TableGenerator(name="sqlite_lifestatus", table="sqlite_sequence",
//	    pkColumnName="name", valueColumnName="seq",
//	    pkColumnValue="LifeStatus")
	@Column(name = "idMeasure")
	private int idMeasure;

	@Column(name = "value")
	private String value;
	
	@ManyToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef", insertable = true, updatable = true)
	private MeasureDefinition measureDefinition;
	
	@ManyToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson")
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

	public LifeStatus() {
	}

	public int getIdMeasure() {
		return this.idMeasure;
	}

	public void setIdMeasure(int idMeasure) {
		this.idMeasure = idMeasure;
	}

	// we make this transient for JAXB to avoid and infinite loop on serialization
	@XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static LifeStatus getLifeStatusById(int lifestatusId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		LifeStatus p = em.find(LifeStatus.class, lifestatusId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<LifeStatus> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<LifeStatus> list = em.createNamedQuery("LifeStatus.findAll", LifeStatus.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static LifeStatus saveLifeStatus(LifeStatus p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		LifeStatus oldLifeStatus = getLifeStyleOfPersonForMeasure(p.getPerson().getIdPerson(), p.getMeasureDefinition());
		if(oldLifeStatus == null){
			oldLifeStatus = new LifeStatus();
		}
		oldLifeStatus.setMeasureDefinition(p.getMeasureDefinition());
		oldLifeStatus.setValue(p.getValue());
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		oldLifeStatus = em.merge(oldLifeStatus);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return oldLifeStatus;
	}
	
	public static LifeStatus updateLifeStatus(LifeStatus p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeLifeStatus(LifeStatus p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
	
	
	public static LifeStatus getLifeStyleOfPersonForMeasure(int idPerson,
			MeasureDefinition type) {

		Person person = Person.getPersonById(idPerson);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		LifeStatus status = null;
		try{
		status = em
				.createNamedQuery("LifeStatus.findLifeStatusOfPersonForMeasure",
						LifeStatus.class).setParameter("person", person)
				.setParameter("type", type)
				.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		LifeCoachDao.instance.closeConnections(em);
		return status;
	}
	

}
