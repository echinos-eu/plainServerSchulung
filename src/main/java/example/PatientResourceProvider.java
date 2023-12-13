package example;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.IResourceProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;

public class PatientResourceProvider implements IResourceProvider {

  private Map<String, Patient> patients = new HashMap<>();

  @Override
  public Class<? extends IBaseResource> getResourceType() {
    return Patient.class;
  }

  @Read
  public Patient read(@IdParam IdType theId) {
    Patient patient = patients.get(theId.getIdPart());
    return patient;
  }

  @Search
  public List<Resource> getAllPatients() {
//    return new HashSet<>(patients.values());
    FhirContext fhirContext = FhirContext.forR4Cached();
    String serverBase = "https://fhir.hl7.de/fhir";
    IGenericClient client = fhirContext.newRestfulGenericClient(serverBase);
    Bundle bundle = client
        .search()
        .forResource(Patient.class)
        .returnBundle(Bundle.class)
        .execute();
    List<Resource> list = bundle.getEntry().stream().map(e -> e.getResource()).toList();
    list.forEach(p -> p.setId(p.getIdPart()));
    return list;

  }
}
