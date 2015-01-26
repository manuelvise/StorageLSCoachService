package introsde.storage.ws;


import introsde.storage.model.Goal;
import introsde.storage.model.HealthMeasureHistory;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.Measure;
import introsde.storage.model.MeasureDefinition;
import introsde.storage.model.Person;
import introsde.wrapper.ws.MeasureWeight;
import introsde.wrapper.ws.WrapperUpdaterService;
import introsde.wrapper.ws.WrapperUpdaterService_Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.fasterxml.jackson.databind.ObjectMapper;

//Service Implementation

@WebService(endpointInterface = "introsde.storage.ws.People",
	serviceName="PeopleStorageService")
public class PeopleImpl implements People {
	
	private WrapperUpdaterService wrapperUpdater;
	
	public PeopleImpl() {

	}

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
	public List<HealthMeasureHistory> readPersonLocalHistory(Long id, String measureType){
				
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

	@Override
	public List<HealthMeasureHistory> readPersonRemoteWeightHistory(String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService.getWrapperUpdaterServiceImplPort();
		
		List<MeasureWeight> wrapperMeasures = wrapperUpdater.getMeasureHistoryFromWeightRK(accessToken);
		List<HealthMeasureHistory> historyWeight = new ArrayList<HealthMeasureHistory>();

		for (MeasureWeight measureWrapper : wrapperMeasures) {
			HealthMeasureHistory measureWeight = new HealthMeasureHistory();
			measureWeight.setTimestamp(measureWrapper.getTimestamp());
			measureWeight.setValue(measureWrapper.getValue());
			
			historyWeight.add(measureWeight);
		}
		
		return historyWeight;
	}

	@Override
	public Long readRemotePersonId(String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService.getWrapperUpdaterServiceImplPort();
		
		return wrapperUpdater.getUserId(accessToken);
	}

	@Override
	public Person readRemotePerson(String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService.getWrapperUpdaterServiceImplPort();
		String personJson = wrapperUpdater.getUser(accessToken);
		ObjectMapper mapper = new ObjectMapper();
		Person personRemote = null;
		try {
			personRemote = mapper.readValue(personJson,Person.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return personRemote;
	}

	@Override
	public List<Goal> readPersonGoals(Long id) {
		
		return Goal.getGoalsOfPerson(id);
		
	}

	@Override
	public Goal readPersonGoalForMeasureType(Long id, String measureType) {
		
		return Goal.getGoalOfPersonForMeasureType(id, measureType);
		
	}


	@Override
	public Goal addGoal(Long id, String measureType, String value, Long deadline) {
		Goal newGoal = new Goal();
		newGoal.setDeadline(deadline);
		newGoal.setMeasureDefinition(MeasureDefinition.getTypeMeasureFromName(measureType));
		newGoal.setPerson(Person.getPersonById(id));
		newGoal.setValue(value);
		
		Goal.saveGoal(newGoal);
		
		return Goal.getGoalOfPersonForMeasureType(id, measureType);
	}
	
	
}
