package introsde.storage.ws;


import introsde.storage.model.HealthMeasureHistory;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.Measure;
import introsde.storage.model.MeasureDefinition;
import introsde.storage.model.Person;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.storage.ws.People",
	serviceName="PeopleStorageService")
public class PeopleImpl implements People {

	@Override
	public Person readPerson(Long id) {
		System.out.println("---> Reading Person by id = "+id);
		Person p = Person.getPersonById(id);
		if (p!=null) {
			System.out.println("---> Found Person by id = "+id+" => "+p.getName());
		} else {
			System.out.println("---> Didn't find any Person with  id = "+id);
		}
		return p;
	}

	@Override
	public List<Person> getPeople() {
		return Person.getAll();
	}

	@Override
	public Long addPerson(Person person) {
		Person.savePerson(person);
		return person.getIdPerson();
	}

	@Override
	public Long updatePerson(Person person) {
		Person.updatePerson(person);
		return person.getIdPerson();
	}

	@Override
	public int deletePerson(Long id) {
		Person p = Person.getPersonById(id);
		if (p!=null) {
			Person.removePerson(p);
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public int updatePersonHP(Long id, LifeStatus hp) {
		LifeStatus ls = LifeStatus.getLifeStatusById(hp.getIdMeasure());
		if (ls.getPerson().getIdPerson() == id) {
			LifeStatus.updateLifeStatus(hp);
			return hp.getIdMeasure();
		} else {
			return -1;
		}
	}
	
	@Override
	public List<HealthMeasureHistory> readPersonHistory(Long id, String measureType){
				
		List<HealthMeasureHistory> historyForMeasure = HealthMeasureHistory.getLifeStyleOfPersonForMeasure(id, measureType);
		
		return historyForMeasure;
		
	}
	
	@Override
	public HealthMeasureHistory readPersonMeasurement(Long id, String measureType,
			int mid) {
		// TODO Auto-generated method stub
		HealthMeasureHistory measure = HealthMeasureHistory.getLifeStyleOfPersonForIdM(id, measureType, mid);
		
		return measure;
	}
	
	@Override
	public LifeStatus savePersonMeasurement(Long idPerson, Measure m) {
		LifeStatus lifeStatusMeasureSaved = null;

		LifeStatus lf = new LifeStatus();
		lf.setMeasureDefinition(m.getMeasureDefinition());
		lf.setValue(m.getValue());
		Person p = Person.getPersonById(idPerson);
		lf.setPerson(p);
		lifeStatusMeasureSaved = LifeStatus.saveLifeStatus(lf);
		//save also in the history
		HealthMeasureHistory hmHistory = new HealthMeasureHistory();
		hmHistory.setPerson(p);
		hmHistory.setTimestamp(System.currentTimeMillis());
		hmHistory.setMeasureDefinition(m.getMeasureDefinition());
		hmHistory.setValue(m.getValue());
		HealthMeasureHistory newMeasurHistory = HealthMeasureHistory.saveHealthMeasureHistory(hmHistory);
		
		if(newMeasurHistory == null){
			LifeStatus.removeLifeStatus(lifeStatusMeasureSaved);
			lifeStatusMeasureSaved = null;
		}
		return lifeStatusMeasureSaved;
	}
	
	
	@Override
	public List<MeasureDefinition> readMeasureTypes() {
		// TODO Auto-generated method stub
		List<MeasureDefinition> listTypes = MeasureDefinition.getAll();
		
		return listTypes;
	}

	@Override
	public HealthMeasureHistory updatePersonMeasure(int idMeasure, Measure m) {
		// TODO Auto-generated method stub
		
		HealthMeasureHistory measureToUpdate = HealthMeasureHistory.getHealthMeasureHistoryById(idMeasure);
		measureToUpdate.setMeasureDefinition(m.getMeasureDefinition());
		measureToUpdate.setValue(m.getValue());
		HealthMeasureHistory measureUpdated = HealthMeasureHistory.updateHealthMeasureHistory(measureToUpdate);
		
		return measureUpdated;
		
	}
	
	@Override
	public List<HealthMeasureHistory> readPersonMeasureByDates(Long id,
			String measureType, Long before, Long after) {
		
		List<HealthMeasureHistory> listMeasures = HealthMeasureHistory.getHistoryMeasuresFromToOfTypeForPerson(id, measureType,before,after);
		
		return listMeasures;
	}
	
	
	@Override
	public List<Person> readPersonListByMeasurementRange(
			String measureType, String maxValue, String minValue) {
		
		List<Person> listMeasures = Person.getPeopleWithinRangeMeasure(measureType,maxValue,minValue);
		
		return listMeasures;
		
	}

	@Override
	public MeasureDefinition getCompleteMeasureTypeFromName(String typeMeasure) {
		return MeasureDefinition.getTypeMeasureFromName(typeMeasure);
	}

	@Override
	public MeasureDefinition saveMeasureDefinition(
			MeasureDefinition measureDefinition) {
		return MeasureDefinition.saveMeasureDefinition(measureDefinition);
	}
	
	
}
