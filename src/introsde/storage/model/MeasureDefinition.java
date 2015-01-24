package introsde.storage.model;

import introsde.storage.dao.LifeCoachDao;
import introsde.storage.model.MeasureDefaultRange;
import introsde.storage.model.MeasureDefinition;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */
@Entity
@Table(name="MeasureDefinition")
@NamedQueries({
@NamedQuery(name="MeasureDefinition.findAll", query="SELECT m FROM MeasureDefinition m"),
@NamedQuery(name="MeasureDefinition.findType", query="SELECT m FROM MeasureDefinition m where measureName = :type")
})
@XmlRootElement
public class MeasureDefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
//	@TableGenerator(name="sqlite_measuredef", table="sqlite_sequence",
//	    pkColumnName="name", valueColumnName="seq",
//	    pkColumnValue="MeasureDefinition")
	@Column(name="idMeasureDef")
	private int idMeasureDef;

	@Column(name="measureName")
	private String measureName;

	@Column(name="measureType")
	private String measureType;

	@OneToMany(mappedBy="measureDefinition")
	private List<MeasureDefaultRange> measureDefaultRange;

	public MeasureDefinition() {
	}

	public int getIdMeasureDef() {
		return this.idMeasureDef;
	}

	public void setIdMeasureDef(int idMeasureDef) {
		this.idMeasureDef = idMeasureDef;
	}

	public String getMeasureName() {
		return this.measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getMeasureType() {
		return this.measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}

	public List<MeasureDefaultRange> getMeasureDefaultRange() {
	    return measureDefaultRange;
	}

	public void setMeasureDefaultRange(List<MeasureDefaultRange> param) {
	    this.measureDefaultRange = param;
	}

	// database operations
	public static MeasureDefinition getMeasureDefinitionById(int idMeasureDef) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureDefinition p = em.find(MeasureDefinition.class, idMeasureDef);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<MeasureDefinition> getAll() {

		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<MeasureDefinition> list = em.createNamedQuery("MeasureDefinition.findAll", MeasureDefinition.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static MeasureDefinition getTypeMeasureFromName(String type) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<MeasureDefinition> list = em.createNamedQuery("MeasureDefinition.findType", MeasureDefinition.class).setParameter("type", type).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list.get(0);
	    
	}
	
	public static MeasureDefinition saveMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static MeasureDefinition updateMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
