package example;

import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.rest.annotation.Operation;
import ca.uhn.fhir.rest.annotation.OperationParam;
import java.util.List;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Parameters;

public class QuestionnaireResponseCalculationProvider {

  // http://localhost:8080/$calculate-score?questResponse=3&questResponse=3
  @Operation(name = "$calculate-score", idempotent = true)
  public Parameters calculateScore(
      @OperationParam(name = "questResponse") List<IntegerType> response) {
    int addition = 0;
    for (IntegerType i : response) {
      addition += i.getValue();
    }
    Parameters parameters = new Parameters();
    parameters.addParameter().setName("result").setValue(new IntegerType(addition));
    return parameters;
  }

}
