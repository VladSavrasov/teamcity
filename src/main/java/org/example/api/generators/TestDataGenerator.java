package org.example.api.generators;

import lombok.var;
import org.example.api.models.*;

import java.util.Arrays;

public class TestDataGenerator {
    public static TestData generate(){
        User user = User.builder()
                .username(RandomData.getString())
                .password(RandomData.getString())
                .email(RandomData.getString() + "@gmail.com")
                .roles(Roles.builder()
                        .role(Arrays.asList(Role.builder()
                                .roleId("SYSTEM_ADMIN")
                                .scope("g")
                                .build()))
                        .build())
                .build();

        var projectDesctiption = NewProjectDescription
                .builder()
                .parentProject(Project.builder()
                        .locator("_Root")
                        .build())
                .name(RandomData.getString())
                .id(RandomData.getString())
                .copyAllAssociatedSettings(true)
                .build();
        var builType = BuildType.builder()
                .id(RandomData.getString())
                .name(RandomData.getString())
                .project(projectDesctiption)
                .build();
        return TestData.builder()
                .user(user)
                .project(projectDesctiption)
                .buildType(builType)
                .build();
    }
      public static Roles generateRoles(org.example.api.enums.Role role,String scope){
          return Roles.builder().role(Arrays.asList(Role.builder()
                  .roleId(role.getText()).scope(scope).build())).build();
      }
}
