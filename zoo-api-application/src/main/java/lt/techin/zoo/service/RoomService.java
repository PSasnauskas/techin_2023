package lt.techin.zoo.service;

import lt.techin.zoo.api.dto.RoomDto;
import lt.techin.zoo.api.dto.mapper.RoomMapper;
import lt.techin.zoo.dao.RoomRepository;
import lt.techin.zoo.exception.ZooValidationException;
import lt.techin.zoo.model.Room;
import lt.techin.zoo.model.RoomType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lt.techin.zoo.model.RoomType.*;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final Integer maxRooms;

    public RoomService(RoomRepository roomRepository,
                       @Value("${rooms.max-rooms}") Integer maxRooms) {
        this.roomRepository = roomRepository;
        this.maxRooms = maxRooms;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }


    public Room create(Room room) {
        if (roomRepository.count() >= maxRooms) {
            throw new ZooValidationException("Max Capacity of Rooms exceeded",
                    "roomCount", "Max Capacity of Rooms exceeded", null);
        }

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

//    @PostConstruct
//    //FIXME for dev purpose
//    public void loadInitialRooms() {
//        //kodel sioje vietoje DTO ir mapping?
//        // nes Entity nesu pasidares patogaus constructor'iaus, todel taip greiciau
//        // tai laikinas metodas
//        var initialRoomsToAdd = List.of(
//                new RoomDto("priimamasis", LOBBY, ""),
//                new RoomDto("uzrakintas", LOCKED, ""),
//                new RoomDto("U v1", LOCKED, ""),
//                new RoomDto("U v2", LOCKED, ""),
//                new RoomDto("U v3", LOCKED, ""),
//                new RoomDto("U v4", LOCKED, ""),
//                new RoomDto("U v5", LOCKED, ""),
//                new RoomDto("404 kambarys", FORBIDDEN, ""),
//                new RoomDto("404 kambarys", LOCKED, "")
//        );
//
//        List<RoomDto> rooms2 = new ArrayList<>();
//        rooms2.addAll(initialRoomsToAdd);
//        for (int i = 0; i < 100; i++) {
//            var roomDto = new RoomDto(String.format("Room (%s)", i), OPEN, null);
//            rooms2.add(roomDto);
//        }
//
//        rooms2.stream()
//                .map(RoomMapper::toRoom)
//                .forEach(roomRepository::save);
//    }

    public String executeSpringDataNamedMethod(String input) {
        return String.valueOf(roomRepository.countByName(input));
    }

    public List<Room> findByType(RoomType type) {
        return roomRepository.findFirst3ByType(type);
    }


    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
            .matchingAny()
//            .withMatcher("date??", ExampleMatcher.GenericPropertyMatchers.exact())
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.exact())
            .withIgnorePaths("id", "description",
                    "createdDate", "modifiedDate", "createdBy", "modifiedBy");

    private Pageable pageable(int page, int pageSize, String sortField, Sort.Direction sortDirection) {
        return PageRequest.of(page, pageSize, sortDirection, sortField);
    }

    @Transactional(readOnly = true)
    public Page<Room> findByExample(String name, RoomType roomType, int page, int pageSize) {

        Room room = new Room();
        if (name != null) {
            room.setName(name);
        }
        if (roomType != null) {
            room.setType(roomType);
        }

        Example<Room> roomExample = Example.of(room, SEARCH_CONDITIONS_MATCH_ANY);

        //Jeigu norime prisideti sorting parametrus ir krypti - isplesti sekanti
        Pageable pageable = PageRequest.of(page, pageSize);

        return roomRepository.findAll(roomExample, pageable);
    }
//
//    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
//            .matchingAny()
//            .withMatcher("birthDate", ExampleMatcher.GenericPropertyMatchers.exact())
//            .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//            .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//            .withIgnorePaths("employeeId", "gender", "hireDate", "salaries", "titles");
//


}
