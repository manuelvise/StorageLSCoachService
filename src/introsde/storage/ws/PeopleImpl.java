package introsde.storage.ws;

import introsde.storage.model.Goal;
import introsde.storage.model.HealthMeasureHistory;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.Measure;
import introsde.storage.model.MeasureDefinition;
import introsde.storage.model.Person;
import introsde.wrapper.ws.MeasureActivity;
import introsde.wrapper.ws.MeasureWeight;
import introsde.wrapper.ws.WrapperUpdaterService;
import introsde.wrapper.ws.WrapperUpdaterService_Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Service Implementation

@WebService(endpointInterface = "introsde.storage.ws.People", serviceName = "PeopleStorageService")
public class PeopleImpl implements People {

	private WrapperUpdaterService wrapperUpdater;

	public PeopleImpl() {

	}

	@Override
	public Person readPerson(Long id) {
		System.out.println("---> Reading Person by id = " + id);
		Person p = Person.getPersonById(id);
		if (p != null) {
			System.out.println("---> Found Person by id = " + id + " => "
					+ p.getName());
		} else {
			System.out.println("---> Didn't find any Person with  id = " + id);
		}
		return p;
	}

	@Override
	public List<Person> getPeople() {

		List<Person> people = Person.getAll();
		if (people.size() > 0) {
			if (people.get(0).getLifeStatus().size() < 1) {
				for (int i = 0; i < people.size(); i++) {
					List<LifeStatus> lifestatus = LifeStatus
							.getLifeStyleOfPerson(people.get(i).getIdPerson());

					if (lifestatus.size() > 0) {
						people.get(i).setLifeStatus(lifestatus);
					}
				}

			}
			if (people.get(0).getGoals().size() < 1) {
				for (int i = 0; i < people.size(); i++) {
					List<Goal> goals = Goal.getGoalsOfPerson(people.get(i)
							.getIdPerson());

					if (goals.size() > 0) {
						people.get(i).setGoals(goals);
					}
				}

			}
		}

		return people;
	}

	@Override
	public Person addPerson(String person) {
		Person personToSave = null;
		try {
			ObjectMapper mapper = new ObjectMapper();

			personToSave = mapper.readValue(person, Person.class);

			Person.savePerson(personToSave);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personToSave;
	}

	@Override
	public Long updatePerson(Person person) {
		Person.updatePerson(person);
		return person.getIdPerson();
	}

	@Override
	public int deletePerson(Long id) {
		Person p = Person.getPersonById(id);
		if (p != null) {
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
	public List<HealthMeasureHistory> readPersonLocalHistory(Long id,
			String measureType) {

		List<HealthMeasureHistory> historyForMeasure = null;

		try {

			if (measureType.equals("activity")) {
				historyForMeasure = HealthMeasureHistory
						.getActivitiesOfPerson(id);
			} else {

				historyForMeasure = HealthMeasureHistory
						.getLifeStyleOfPersonForMeasure(id, measureType);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyForMeasure;

	}

	@Override
	public HealthMeasureHistory readPersonMeasurement(Long id,
			String measureType, int mid) {
		// TODO Auto-generated method stub
		HealthMeasureHistory measure = HealthMeasureHistory
				.getLifeStyleOfPersonForIdM(id, measureType, mid);

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
		// save also in the history
		HealthMeasureHistory hmHistory = new HealthMeasureHistory();
		hmHistory.setPerson(p);
		hmHistory.setTimestamp(String.valueOf(System.currentTimeMillis()));
		hmHistory.setMeasureDefinition(m.getMeasureDefinition());
		hmHistory.setValue(m.getValue());
		HealthMeasureHistory newMeasurHistory = HealthMeasureHistory
				.saveHealthMeasureHistory(hmHistory);

		if (newMeasurHistory == null) {
			LifeStatus.removeLifeStatus(lifeStatusMeasureSaved);
			lifeStatusMeasureSaved = null;
		}

		return lifeStatusMeasureSaved;
	}

	@Override
	public ArrayList<MeasureDefinition> readMeasureTypes() {
		// TODO Auto-generated method stub
		ArrayList<MeasureDefinition> listTypes = (ArrayList<MeasureDefinition>) MeasureDefinition
				.getAll();

		return listTypes;
	}

	@Override
	public HealthMeasureHistory updatePersonMeasure(int idMeasure, Measure m) {
		// TODO Auto-generated method stub

		HealthMeasureHistory measureToUpdate = HealthMeasureHistory
				.getHealthMeasureHistoryById(idMeasure);
		measureToUpdate.setMeasureDefinition(m.getMeasureDefinition());
		measureToUpdate.setValue(m.getValue());
		HealthMeasureHistory measureUpdated = HealthMeasureHistory
				.updateHealthMeasureHistory(measureToUpdate);

		return measureUpdated;

	}

	@Override
	public ArrayList<HealthMeasureHistory> readPersonMeasureByDates(Long id,
			String measureType, Long before, Long after) {

		ArrayList<HealthMeasureHistory> listMeasures = (ArrayList<HealthMeasureHistory>) HealthMeasureHistory
				.getHistoryMeasuresFromToOfTypeForPerson(id, measureType,
						before, after);

		return listMeasures;
	}

	@Override
	public ArrayList<Person> readPersonListByMeasurementRange(
			String measureType, String maxValue, String minValue) {

		ArrayList<Person> listMeasures = (ArrayList<Person>) Person
				.getPeopleWithinRangeMeasure(measureType, maxValue, minValue);

		return listMeasures;

	}

	@Override
	public String getCompleteMeasureTypeFromName(String typeMeasure) {
		try {
			ObjectMapper mapperJson = new ObjectMapper();
			String typeJson = null;
			MeasureDefinition measureDef = MeasureDefinition
					.getTypeMeasureFromName(typeMeasure);
			try {
				typeJson = mapperJson.writeValueAsString(measureDef);
				return typeJson;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MeasureDefinition saveMeasureDefinition(String measureDefinition) {

		MeasureDefinition meDefToSave = null;
		try {
			ObjectMapper mapper = new ObjectMapper();

			meDefToSave = mapper.readValue(measureDefinition,
					MeasureDefinition.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return MeasureDefinition.saveMeasureDefinition(meDefToSave);
	}

	@Override
	public ArrayList<HealthMeasureHistory> readPersonRemoteWeightHistory(
			String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService
				.getWrapperUpdaterServiceImplPort();

		ArrayList<MeasureWeight> wrapperMeasures = (ArrayList<MeasureWeight>) wrapperUpdater
				.getMeasureHistoryFromWeightRK(accessToken);
		ArrayList<HealthMeasureHistory> historyWeight = new ArrayList<HealthMeasureHistory>();

		for (MeasureWeight measureWrapper : wrapperMeasures) {
			HealthMeasureHistory measureWeight = new HealthMeasureHistory();
			measureWeight.setTimestamp(measureWrapper.getTimestamp());
			measureWeight.setValue(measureWrapper.getValue());

			historyWeight.add(measureWeight);
		}

		return historyWeight;
	}

	@Override
	public ArrayList<HealthMeasureHistory> readPersonRemoteActivityHistory(
			String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService
				.getWrapperUpdaterServiceImplPort();

		ArrayList<MeasureActivity> wrapperMeasures = (ArrayList<MeasureActivity>) wrapperUpdater
				.getFitnessActivities(accessToken);
		ArrayList<HealthMeasureHistory> historyWeight = new ArrayList<HealthMeasureHistory>();

		for (MeasureActivity measureWrapper : wrapperMeasures) {
			HealthMeasureHistory measureActivity = new HealthMeasureHistory();
			measureActivity.setTimestamp(measureWrapper.getTimestamp());
			measureActivity.setValue(measureWrapper.getValue());
			String measureDefJson = getCompleteMeasureTypeFromName(measureWrapper
					.getType());
			if (measureDefJson != null) {
				MeasureDefinition meDefToSave = null;

				try {
					ObjectMapper mapper = new ObjectMapper();

					meDefToSave = mapper.readValue(measureDefJson,
							MeasureDefinition.class);

				} catch (Exception e) {
					e.printStackTrace();
				}

				measureActivity.setMeasureDefinition(meDefToSave);
			} else {

				ObjectMapper mapperJson = new ObjectMapper();
				String typeJson = null;
				MeasureDefinition measureDef = new MeasureDefinition();
				measureDef.setMeasureName(measureWrapper.getType());
				measureDef.setMeasureType("String");
				try {
					typeJson = mapperJson.writeValueAsString(measureDef);
					MeasureDefinition measureSaved = saveMeasureDefinition(typeJson);

					measureActivity.setMeasureDefinition(measureSaved);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			historyWeight.add(measureActivity);
		}

		return historyWeight;
	}

	@Override
	public Long readRemotePersonId(String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService
				.getWrapperUpdaterServiceImplPort();

		return wrapperUpdater.getUserId(accessToken);
	}

	@Override
	public Person readRemotePerson(String accessToken) {
		WrapperUpdaterService_Service wrapperUpdaterService = new WrapperUpdaterService_Service();
		wrapperUpdater = wrapperUpdaterService
				.getWrapperUpdaterServiceImplPort();
		String personJson = wrapperUpdater.getUser(accessToken);
		ObjectMapper mapper = new ObjectMapper();
		Person personRemote = null;
		try {
			personRemote = mapper.readValue(personJson, Person.class);
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
		newGoal.setMeasureDefinition(MeasureDefinition
				.getTypeMeasureFromName(measureType));
		newGoal.setPerson(Person.getPersonById(id));
		newGoal.setValue(value);

		Goal goalStored = Goal.getGoalForPersonDeadlineType(id, deadline,
				measureType);

		if (goalStored == null) {
			goalStored = new Goal();

			MeasureDefinition mDef = MeasureDefinition
					.getTypeMeasureFromName(measureType);

			if (mDef == null) {
				mDef = new MeasureDefinition();
				mDef.setMeasureName(measureType);
				mDef.setMeasureType("String");
				mDef = MeasureDefinition.saveMeasureDefinition(mDef);
			}

			goalStored.setMeasureDefinition(mDef);

			goalStored.setPerson(Person.getPersonById(id));
		}

		goalStored.setDeadline(deadline);
		goalStored.setValue(value);

		Goal.saveGoal(newGoal);

		return Goal.getGoalOfPersonForMeasureType(id, measureType);
	}

	@Override
	public HealthMeasureHistory saveIfnotExistPersonMeasurement(Long idPerson,
			Measure m) {
		LifeStatus lifeStatusMeasureSaved = null;

		LifeStatus lf = new LifeStatus();
		lf.setMeasureDefinition(m.getMeasureDefinition());
		lf.setValue(m.getValue());
		Person p = Person.getPersonById(idPerson);
		lf.setPerson(p);
		lifeStatusMeasureSaved = LifeStatus.saveLifeStatus(lf);

		HealthMeasureHistory hmHistory = HealthMeasureHistory
				.getHealthMeasureForDefinitionAndTimestamp(idPerson, m);

		if (hmHistory == null) {
			hmHistory = new HealthMeasureHistory();
			hmHistory.setPerson(p);
			hmHistory.setTimestamp(m.getTimestamp());
			hmHistory.setMeasureDefinition(m.getMeasureDefinition());
			hmHistory.setValue(m.getValue());
			HealthMeasureHistory newMeasurHistory = HealthMeasureHistory
					.saveHealthMeasureHistory(hmHistory);

			return newMeasurHistory;
		} else {
			HealthMeasureHistory newMeasurHistory = HealthMeasureHistory
					.updateHealthMeasureHistory(hmHistory);

			return newMeasurHistory;
		}

	}

}
