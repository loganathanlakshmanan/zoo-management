package nl.zoo.management.services;

import nl.zoo.management.dto.AnimalDTO;
import nl.zoo.management.dto.RoomDTO;
import nl.zoo.management.models.Animal;
import nl.zoo.management.models.Room;
import nl.zoo.management.repositories.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ZooAnimalService {
    private final AnimalRepository animalRepository;

    private final ModelMapper modelMapper;

    public ZooAnimalService(AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    public List<AnimalDTO> retrieveAllAnimalsBelongToRoomId(Long roomId){

        return animalRepository.findAll().stream()
                .filter(animal -> Objects.nonNull(animal.getRoom()) && animal.getRoom().getId() == roomId)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> retrieveAllAnimalsWithoutRoom(String sortBy, String sortDirection) {
        return animalRepository.findAllAnimalsWithoutRoom(Sort.by(findSortDirection(sortDirection), sortBy))
                               .stream()
                               .map(this::convertToDto)
                               .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> retrieveAllAnimalsInSortedOrder(String sortBy, String sortDirection) {
        return animalRepository.findAll(Sort.by(findSortDirection(sortDirection), sortBy))
                               .stream()
                               .map(this::convertToDto)
                               .collect(Collectors.toList());
    }

    private Sort.Direction findSortDirection(String sortDirection) {
        return "ASC".equalsIgnoreCase(sortDirection) ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public AnimalDTO getAnimalById(Long id) {
        return animalRepository.findById(id)
                               .map(animal -> AnimalDTO.builder()
                                                       .title(animal.getTitle())
                                                       .type(animal.getType())
                                                       .createdAt(animal.getCreatedAt())
                                                       .roomDTO(RoomDTO.builder()
                                                                       .title(animal.getRoom().getTitle())
                                                                       .size(animal.getRoom().getSize())
                                                                       .createdAt(animal.getRoom().getCreatedAt())
                                                                       .build())
                                                       .build())
                               .orElseThrow();

    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal savedAnimal = animalRepository.save(convertToEntity(animalDTO));
        return convertToDto(savedAnimal);
    }

    public AnimalDTO updateAnimal(AnimalDTO animalDTO) {
        Animal animal = animalRepository.findById(animalDTO.getId())
                                        .map(animal1 -> wrapModel(animal1, animalDTO))
                                        .map(animalRepository::save)
                                        .get();
        return convertToDto(animal);
    }

    public void deleteAnimal(AnimalDTO animalDTO) {
        animalRepository.deleteById(animalDTO.getId());

    }

    private AnimalDTO convertToDto(Animal animal) {
        return modelMapper.map(animal, AnimalDTO.class);
    }

    private Animal convertToEntity(AnimalDTO animalDTO) {
        return modelMapper.map(animalDTO, Animal.class);
    }

    private Animal wrapModel(Animal animal, AnimalDTO animalDTO) {
        Room room = animal.getRoom();
        room.setSize(animalDTO.getRoomDTO().getSize());
        room.setTitle(animalDTO.getRoomDTO().getTitle());
        room.setSize(animalDTO.getRoomDTO().getSize());
        return animal.toBuilder()
                     .type(animalDTO.getType())
                     .title(animalDTO.getTitle())
                     .createdAt(animalDTO.getCreatedAt())
                     .room(room)
                     .build();
    }
}
