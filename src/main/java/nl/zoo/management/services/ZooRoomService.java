package nl.zoo.management.services;

import nl.zoo.management.exceptions.RoomNotFound;
import nl.zoo.management.models.Room;
import nl.zoo.management.dto.RoomDTO;
import nl.zoo.management.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZooRoomService {
    private final RoomRepository roomRepository;

    public List<RoomDTO> retrieveAllRooms() {
        return roomRepository.findAll()
                             .stream()
                             .map(room -> RoomDTO.builder()
                                                 .id(room.getId())
                                                 .title(room.getTitle())
                                                 .createdAt(room.getCreatedAt())
                                                 .size(room.getSize())
                                                 .build())
                             .collect(Collectors.toList());
    }

//    public List<RoomDTO> retrieveAllAnimalsBelongToRoomId(Long roomId) {
//        return roomRepository.findAll()
//                             .stream()
//                             .filter(animal -> animal.)
//                             .map(room -> RoomDTO.builder()
//                                                 .id(room.getId())
//                                                 .title(room.getTitle())
//                                                 .createdAt(room.getCreatedAt())
//                                                 .size(room.getSize())
//                                                 .build())
//                             .collect(Collectors.toList());
//    }

    public RoomDTO retrieveRoomById(Long id) {
        return roomRepository.findById(id)
                             .map(room -> RoomDTO.builder().build())
                             .orElseThrow(() -> new RoomNotFound("Not found" + id));

    }

    public Room createRoom(RoomDTO roomDTO) {

        Room room = roomRepository.save(Room.builder().title(roomDTO.getTitle()).size(roomDTO.getSize()).build());

        //        room.getAnimal() (Animal.builder()
        //                             .Title(roomDTO.getAnimalDTO().getTitle())
        //                             .type(roomDTO.getAnimalDTO().getType())
        //                             .build());
        return roomRepository.save(room);
    }

    public Room updateRoom(RoomDTO roomDTO) {
        return roomRepository.findById(roomDTO.getId())
                             .map(room1 -> transformDTO(roomDTO, room1))
                             .map(roomRepository::save)
                             .orElse(null);

    }

    private Room transformDTO(RoomDTO roomDTO, Room roomToSave) {
        roomToSave.setTitle(roomDTO.getTitle());
        roomToSave.setSize(roomDTO.getSize());
        //        roomToSave.setAnimal(roomToSave.getAnimal());
        //        Animal animalToSave = roomToSave.getAnimal();
        //        animalToSave.setType(roomDTO.getAnimalDTO().getType());
        //        animalToSave.setTitle(roomDTO.getAnimalDTO().getTitle());
        return roomToSave;
    }

}
