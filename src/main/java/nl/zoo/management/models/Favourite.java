package nl.zoo.management.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Favourite")
@AllArgsConstructor
@NoArgsConstructor
public class Favourite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @ManyToMany
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private List<Room> rooms;
    @ManyToMany
    private List<Animal> animals;
    private FavouriteStatus Status;
}
