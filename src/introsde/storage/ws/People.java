package introsde.storage.ws;
import introsde.storage.model.HealthMeasureHistory;
import introsde.storage.model.LifeStatus;
import introsde.storage.model.Measure;
import introsde.storage.model.MeasureDefinition;
import introsde.storage.model.Person;

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
    public Person readPerson(@WebParam(name="personId") int id);
 
    @WebMethod(operationName="getPersonList")
    @WebResult(name="people") 
    public List<Person> getPeople();
 
    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public int addPerson(@WebParam(name="person") Person person);
 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId") 
    public int updatePerson(@WebParam(name="person") Person person);
    
    @WebMethod(operationName="deletePerson")
    @WebResult(name="personDeleted") 
    public int deletePerson(@WebParam(name="personId") int id);
    
    @WebMethod(operationName="updatePersonHealthProfile")
    @WebResult(name="hpId") 
    public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") LifeStatus hp);
    
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="history") 
    public List<HealthMeasureHistory> readPersonHistory(@WebParam(name="personId") int id, @WebParam(name="measureType") String measureType);

    @WebMethod(operationName="readPersonMeasurement")
    @WebResult(name="singleMeasureOfPersonById") 
    public HealthMeasureHistory readPersonMeasurement(@WebParam(name="personId") int id, @WebParam(name="measureType") String measureType, @WebParam(name="mId") int idMeasure);

    @WebMethod(operationName="savePersonMeasurement")
    @WebResult(name="singleMeasureSaved") 
    public LifeStatus savePersonMeasurement(@WebParam(name="personId") int idPerson, @WebParam(name="measure") Measure m);
    
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="ListOfMeasureTypes") 
    public List<MeasureDefinition> readMeasureTypes();
    
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="singleMeasureUpdated") 
    public HealthMeasureHistory updatePersonMeasure(@WebParam(name="idMeasure") int idMeasure, @WebParam(name="measure") Measure m);
    
    
    @WebMethod(operationName="readPersonMeasureByDates")
    @WebResult(name="ListOfMeasureFromToDateAndType") 
    public List<HealthMeasureHistory> readPersonMeasureByDates(@WebParam(name="personId") int id, @WebParam(name="measureType") String measureType, @WebParam(name="before") Long before, @WebParam(name="after") Long after);
    
    
    @WebMethod(operationName="readPersonListByMeasurementRange")
    @WebResult(name="ListOfMeasureOnRange") 
    public List<Person> readPersonListByMeasurementRange(@WebParam(name="measureType") String measureType, @WebParam(name="maxValue") String maxValue, @WebParam(name="minValue") String minValue);
}