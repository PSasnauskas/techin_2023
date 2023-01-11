package lt.techin.zoo.dao;

import lt.techin.zoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

// findStoreByLocationId

    @Query(value = "SELECT a FROM Animal a WHERE a.status = 1",
            nativeQuery = true)
    List<Animal> findAllMarkedAnimals();


//    @Modifying
//    @Query("delete Animal a where a.active = false")
    //The @Modifying annotation is used to enhance the @Query annotation so that we can execute not only SELECT queries, but also INSERT, UPDATE, DELETE, and even DDL queries.
//    int deleteDeactivatedAnimals();

//    @Query(
//            value = "SELECT * FROM animal a WHERE a.status = 1",
//            nativeQuery = true)
//    Collection<Animal> findAllActiveAnimalNative();
    //native query nebegaletume paduoti "sort"

    //findAll(Sort.by(Sort.Direction.ASC, "name"));
    //findAll(Sort.by("LENGTH(name)"));

//    @Query(value = "SELECT a FROM animal a")
//    List<Animal> findAllAnimals(Sort sort);

}
