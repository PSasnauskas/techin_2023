package lt.techin.zoo.dao;

import lt.techin.zoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

// findStoreByLocationId

}
