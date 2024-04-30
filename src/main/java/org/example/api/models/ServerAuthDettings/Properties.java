package org.example.api.models.ServerAuthDettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Properties {
    private List<Property> property;
    private int count;
    private String href;
}
