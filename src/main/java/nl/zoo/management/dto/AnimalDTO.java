package nl.zoo.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimalDTO {
    private Long id;
    private String title;
    @JsonProperty("date_added")
    private Instant createdAt;
    private String type;
    @JsonProperty("room")
    private RoomDTO roomDTO;
}
