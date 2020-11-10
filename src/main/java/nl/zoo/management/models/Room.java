package nl.zoo.management.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity(name = "Room")
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int size;
    @CreationTimestamp
    private Instant createdAt;
    @OneToMany(mappedBy = "room")
    private Set<Animal> animals;

}
