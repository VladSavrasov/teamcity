package org.example.api.models.ServerAuthDettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class AuthModule {
    private String name;
    private Properties properties;
}
