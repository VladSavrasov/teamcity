package org.example.api.models.ServerAuthDettings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerAuthSettings {
    private boolean allowGuest;
    private String guestUsername;
    private String welcomeText;
    private boolean collapseLoginForm;
    private boolean perProjectPermissions;
    private boolean emailVerification;
    private AuthModules modules;
}
