package lt.techin.zoo.service;

import lt.techin.zoo.api.dto.RoomDto;
import lt.techin.zoo.api.dto.mapper.RoomMapper;
import lt.techin.zoo.dao.RoomRepository;
import lt.techin.zoo.model.Room;
import lt.techin.zoo.model.RoomType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static lt.techin.zoo.model.RoomType.*;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }


    public Room create(Room room) {
        return roomRepository.save(room);
    }

    public Room update(Long id, Room room) {
        room.setId(id);//FIXME will improve later

        return roomRepository.save(room);
    }

    public boolean deleteById(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @PostConstruct
    //FIXME for dev purpose
    public void loadInitialRooms() {
        //kodel sioje vietoje DTO ir mapping?
        // nes Entity nesu pasidares patogaus constructor'iaus, todel taip greiciau
        // tai laikinas metodas
        var initialRoomsToAdd = List.of(
                new RoomDto( "priimamasis", LOBBY, ""),
                new RoomDto( "uzrakintas", LOCKED, ""),
                new RoomDto( "U v1", LOCKED, ""),
                new RoomDto( "U v2", LOCKED, ""),
                new RoomDto( "U v3", LOCKED, ""),
                new RoomDto( "U v4", LOCKED, ""),
                new RoomDto( "U v5", LOCKED, ""),
                new RoomDto( "404 kambarys", FORBIDDEN, ""),
                new RoomDto( "404 kambarys", LOCKED, "")
        );

        initialRoomsToAdd.stream()
                .map(RoomMapper::toRoom)
                .forEach(roomRepository::save);
    }

    public String executeSpringDataNamedMethod(String input) {
        return String.valueOf(roomRepository.countByName(input));
    }

    public List<Room> findByType(RoomType type) {
        return roomRepository.findFirst3ByType(type);
    }

}
