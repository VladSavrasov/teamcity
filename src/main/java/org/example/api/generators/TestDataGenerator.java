package org.example.api.generators;

import org.example.api.models.BuildType;
import org.example.api.models.NewProjectDescription;
import org.example.api.models.Project;
import org.example.api.models.Role;
import org.example.api.models.Roles;
import org.example.api.models.User;

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

        NewProjectDescription projectDesctiption = NewProjectDescription
                .builder()
                .parentProject(Project.builder()
                        .locator("_Root")
                        .build())
                .name(RandomData.getRandomStringForIdsAndNames())
                .id(RandomData.getRandomStringForIdsAndNames())
                .copyAllAssociatedSettings(true)
                .build();
        BuildType builType = BuildType.builder()
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
