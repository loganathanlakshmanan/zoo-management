package nl.zoo.management.controllers;

import nl.zoo.management.dto.AnimalDTO;
import nl.zoo.management.exceptions.BadRequestException;
import nl.zoo.management.services.ZooAnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/zoo-management/animals", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class ZooManagementAnimalController {
    private final ZooAnimalService zooAnimalService;
    private final List<String> allowedSortDirection = Arrays.asList("ASC", "DESC");
    private final List<String> allowedSortByFields = List.of("title", "createdAt", "type");

    @GetMapping
    public Collection<AnimalDTO> getAllAnimals(@Valid @RequestParam(required = false, defaultValue = "createdAt")
                                                       String sortBy,
                                               @Valid @RequestParam(required = false, defaultValue = "ASC")
                                                       String sortDirection,
                                               @Valid @RequestParam(required = false, defaultValue = "true")
                                                       boolean located)
    {
        if (!allowedSortDirection.contains(sortDirection) || !allowedSortByFields.contains(sortBy)) {
            throw new BadRequestException("Input invalid");
        }

        if (!located) {
            return zooAnimalService.retrieveAllAnimalsWithoutRoom(sortBy, sortDirection);
        }

        return zooAnimalService.retrieveAllAnimalsInSortedOrder(sortBy, sortDirection);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO) {

        return zooAnimalService.createAnimal(animalDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AnimalDTO updateAnimal(@RequestBody AnimalDTO animalDTO) {

        return zooAnimalService.updateAnimal(animalDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAnimal(@RequestBody AnimalDTO animalDTO) {
        zooAnimalService.deleteAnimal(animalDTO);
    }

    @GetMapping("/{animalId}")
    public AnimalDTO getAnimalByIdForGivenRoom(@PathVariable Long animalId) {
        return zooAnimalService.getAnimalById(animalId);
    }

    @GetMapping("/{animalId}/favourite-rooms")
    public AnimalDTO getFavouriteRoomByGivenAnimal(@PathVariable String animalId) {
        return new AnimalDTO();
    }

}
