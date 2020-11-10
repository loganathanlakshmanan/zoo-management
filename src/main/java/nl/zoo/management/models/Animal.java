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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Animal")
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @CreationTimestamp
    private Instant createdAt;
    private String type;
    @ManyToOne
    private Room room;
}
