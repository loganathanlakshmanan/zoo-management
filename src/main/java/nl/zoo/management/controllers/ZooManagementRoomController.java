package nl.zoo.management.controllers;

import nl.zoo.management.dto.AnimalDTO;
import nl.zoo.management.dto.RoomDTO;
import nl.zoo.management.services.ZooAnimalService;
import nl.zoo.management.services.ZooRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import java.util.Collection;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/zoo-management/rooms", produces = "application/json;charset=UTF-8")
public class ZooManagementRoomController {
    private final ZooRoomService zooRoomService;
    private final ZooAnimalService zooAnimalService;

    @GetMapping
    public Collection<RoomDTO> getAllRooms(@Valid @RequestParam(name = "status", required = false)
                                                   String occupiedStatus)
    {
        return zooRoomService.retrieveAllRooms();
    }

    @GetMapping("/{roomId}")
    public RoomDTO getRoomById(@Valid @PathVariable String roomId) {
        return new RoomDTO();
    }

    @GetMapping("/{roomId}/animals")
    public Collection<AnimalDTO> getAllAnimalsForGivenRoom(@Valid @PathVariable Long roomId) {
        return zooAnimalService.retrieveAllAnimalsBelongToRoomId(roomId);
    }

    @GetMapping("/{roomId}/animals/{animalId}")
    public AnimalDTO getAnimalByIdForGivenRoom(@Valid @PathVariable Long roomId,
                                               @Valid @PathVariable Long animalId)
    {
        return new AnimalDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO createRoom(@Valid @RequestBody RoomDTO roomDTO) {
        return Optional.of(zooRoomService.createRoom(roomDTO))
                       .map(room -> RoomDTO.builder()
                                           .id(room.getId())
                                           .title(room.getTitle())
                                           .size(room.getSize())
                                           .build())
                       .orElse(null);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoomDTO updateRoom(@Valid @RequestBody RoomDTO roomDTO) {
        return Optional.of(zooRoomService.updateRoom(roomDTO))
                       .map(room -> RoomDTO.builder()
                                           .id(room.getId())
                                           .title(room.getTitle())
                                           .size(room.getSize())
                                           .build())
                       .orElse(null);

    }
}
