package example;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.server.IResourceProvider;
import java.util.HashMap;
import java.util.Map;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;

public class PatientResourceProvider  implements IResourceProvider {

  private Map<String, Patient> patients = new HashMap<>();
  @Override
  public Class<? extends IBaseResource> getResourceType() {
    return Patient.class;
  }
  @Read
  public Patient read(@IdParam IdType theId){
    Patient patient = patients.get(theId.asStringValue());
    return patient;
  }
}
