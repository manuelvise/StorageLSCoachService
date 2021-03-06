package introsde.storage.ws;
import introsde.storage.model.Goal;
import introsde.storage.model.HealthMeasureHistory;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.Measure;
import introsde.storage.model.MeasureDefinition;
import introsde.storage.model.Person;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding
public interface People {
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") Long id);
    
    @WebMethod(operationName="readRemotePerson")
    @WebResult(name="remotePerson") 
    public Person readRemotePerson(@WebParam(name="accessToken") String accessToken);
 
    @WebMethod(operationName="getPersonList")
    @WebResult(name="people") 
    public List<Person> getPeople();
 
    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public Person addPerson(@WebParam(name="personJson") String person);
 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId") 
    public Long updatePerson(@WebParam(name="person") Person person);
    
    @WebMethod(operationName="deletePerson")
    @WebResult(name="ValueResult") 
    public int deletePerson(@WebParam(name="personId") Long id);
    
    @WebMethod(operationName="updatePersonHealthProfile")
    @WebResult(name="hpId") 
    public int updatePersonHP(@WebParam(name="personId") Long id, @WebParam(name="healthProfile") LifeStatus hp);
    
    @WebMethod(operationName="readPersonLocalHistory")
    @WebResult(name="localHistory") 
    public List<HealthMeasureHistory> readPersonLocalHistory(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType);

    @WebMethod(operationName="readPersonRemoteWeightHistory")
    @WebResult(name="remoteWeightHistory") 
    public List<HealthMeasureHistory> readPersonRemoteWeightHistory(@WebParam(name="accessToken") String accessToken);

    @WebMethod(operationName="readRemotePersonId")
    @WebResult(name="RemotePersonId") 
    public Long readRemotePersonId(@WebParam(name="accessToken") String accessToken);

    
    @WebMethod(operationName="readPersonMeasurement")
    @WebResult(name="singleMeasureOfPersonById") 
    public HealthMeasureHistory readPersonMeasurement(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType, @WebParam(name="mId") int idMeasure);

    @WebMethod(operationName="savePersonMeasurement")
    @WebResult(name="singleMeasureSaved") 
    public LifeStatus savePersonMeasurement(@WebParam(name="personId") Long idPerson, @WebParam(name="measure") Measure m);
    
    @WebMethod(operationName="saveIfnotExistPersonMeasurement")
    @WebResult(name="singleMeasureSaved") 
    public HealthMeasureHistory saveIfnotExistPersonMeasurement(@WebParam(name="personId") Long idPerson, @WebParam(name="measure") Measure m);
    
    @WebMethod(operationName="deletePersonMeasurementActivities")
    @WebResult(name="boolean") 
    public Boolean deletePersonMeasurementActivities(@WebParam(name="personId") Long idPerson);

    @WebMethod(operationName="deletePersonLifeStatusActivities")
    @WebResult(name="boolean") 
    public Boolean deletePersonLifeStatusActivities(@WebParam(name="personId") Long idPerson);
    
    
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="ListOfMeasureTypes") 
    public ArrayList<MeasureDefinition> readMeasureTypes();
    
    @WebMethod(operationName="getCompleteMeasureTypeFromName")
    @WebResult(name="measureDefinitionJson") 
    public String getCompleteMeasureTypeFromName(@WebParam(name="typeMeasure") String typeMeasure);
    
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="singleMeasureUpdated") 
    public HealthMeasureHistory updatePersonMeasure(@WebParam(name="idMeasure") int idMeasure, @WebParam(name="measure") Measure m);
    
    @WebMethod(operationName="saveMeasureDefinition")
    @WebResult(name="singleMeasureUpdated") 
    public MeasureDefinition saveMeasureDefinition(@WebParam(name="measureDefinitionJson") String measureDefinition);
   
    @WebMethod(operationName="readPersonMeasureByDates")
    @WebResult(name="ListOfMeasureFromToDateAndType") 
    public List<HealthMeasureHistory> readPersonMeasureByDates(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType, @WebParam(name="before") Long before, @WebParam(name="after") Long after);
    
    
    @WebMethod(operationName="readPersonListByMeasurementRange")
    @WebResult(name="ListOfMeasureOnRange") 
    public List<Person> readPersonListByMeasurementRange(@WebParam(name="measureType") String measureType, @WebParam(name="maxValue") String maxValue, @WebParam(name="minValue") String minValue);


    @WebMethod(operationName="readPersonGoals")
    @WebResult(name="Goals") 
    public List<Goal> readPersonGoals(@WebParam(name="personId") Long id);
    
    @WebMethod(operationName="readPersonGoalForMeasureType")
    @WebResult(name="Goal") 
    public Goal readPersonGoalForMeasureType(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType);

    @WebMethod(operationName="saveGoal")
    @WebResult(name="Goal") 
    public Goal addGoal(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType, @WebParam(name="value") String value, @WebParam(name="deadline") Long deadline);

    @WebMethod(operationName="readPersonRemoteActivityHistory")
    @WebResult(name="ListOfActivities") 
	ArrayList<HealthMeasureHistory> readPersonRemoteActivityHistory(@WebParam(name="accessToken") String accessToken);

    
}