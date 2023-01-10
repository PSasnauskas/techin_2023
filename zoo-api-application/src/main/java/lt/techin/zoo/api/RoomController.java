package lt.techin.zoo.api;

import lt.techin.zoo.api.dto.RoomDto;
import lt.techin.zoo.api.dto.mapper.RoomMapper;
import lt.techin.zoo.model.RoomType;
import lt.techin.zoo.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static lt.techin.zoo.api.dto.mapper.RoomMapper.toRoom;
import static lt.techin.zoo.api.dto.mapper.RoomMapper.toRoomDto;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/rooms")
@Validated
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @ResponseBody
    public List<RoomDto> getRooms() {
        return roomService.getAll().stream()
                .map(RoomMapper::toRoomDto)
                .collect(toList());
        //return ResponseEntity.ok(animalRepository.getAll());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long roomId) {
        var roomOptional = roomService.getById(roomId);

        var responseEntity = roomOptional
                .map(room -> ok(toRoomDto(room)))
                .orElseGet(() -> ResponseEntity.notFound().build());

//        responseEntity = new ResponseEntity<>(toRoomDto(roomOptional.get()), HttpStatus.OK);

        return responseEntity;
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        var roomDeleted = roomService.deleteById(roomId);

        if (roomDeleted) {
            return ResponseEntity.noContent().build();
//            new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody RoomDto roomDto) {
        //FIXME temp
        roomDto.setId(null);

        var createdRoom = roomService.create(toRoom(roomDto));

        return ok(toRoomDto(createdRoom));
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable Long animalId, @RequestBody RoomDto roomDto) {
        //FIXME temp
        roomDto.setId(null);

        var updatedRoom = roomService.update(animalId, toRoom(roomDto));

        return ok(toRoomDto(updatedRoom));
    }

    @GetMapping("/custom")
    public ResponseEntity<String> executeCustom(@RequestParam String input) {
        var result = roomService.executeSpringDataNamedMethod(input);
        return ok(result);
    }

    @GetMapping("/type/top")
    public ResponseEntity<List<RoomDto>> findLatestByType(@RequestParam RoomType type) {
        var result = roomService.findByType(type).stream()
                .map(RoomMapper::toRoomDto)
                .collect(toList());

        return ok(result);
    }

}
