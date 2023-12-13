package example;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/*")
public class HapiServer extends RestfulServer {

  public HapiServer() {
    super(FhirContext.forR4());
  }

  @Override
  public void initialize(){
    List<IResourceProvider> provider = new ArrayList<>();
    provider.add(new PatientResourceProvider());
    setResourceProviders(provider);

    registerInterceptor(new ResponseHighlighterInterceptor());
  }
}
