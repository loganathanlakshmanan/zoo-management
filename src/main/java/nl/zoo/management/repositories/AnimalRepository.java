package nl.zoo.management.repositories;

import nl.zoo.management.models.Animal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    default List<Animal> findAllAnimalsWithoutRoom(Sort sort) {
        return findAll(sort).stream().filter(checkRoom -> checkRoom.getRoom() == null).collect(Collectors.toList());
    }

}
