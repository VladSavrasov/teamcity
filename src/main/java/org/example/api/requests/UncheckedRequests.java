package org.example.api.requests;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.example.api.requests.unchecked.UncheckedBuildConfig;
import org.example.api.requests.unchecked.UncheckedProject;
import org.example.api.requests.unchecked.UncheckedUser;

@Getter
public class UncheckedRequests {

  private UncheckedUser userRequest;
  private UncheckedProject projectRequest;
  private UncheckedBuildConfig buildConfigRequest;

  public UncheckedRequests(RequestSpecification spec){
      this.userRequest = new UncheckedUser(spec);
      this.buildConfigRequest = new UncheckedBuildConfig(spec);
      this.projectRequest = new UncheckedProject(spec);
  }
}
