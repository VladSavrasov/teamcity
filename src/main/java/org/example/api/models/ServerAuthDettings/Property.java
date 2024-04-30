package org.example.api.models.ServerAuthDettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Property {
    private String name;
    private String value;
    private boolean inherited;
    private Type type;
}
