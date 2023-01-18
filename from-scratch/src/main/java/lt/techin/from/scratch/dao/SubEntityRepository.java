package lt.techin.from.scratch.dao;


import lt.techin.from.scratch.model.Entity;
import lt.techin.from.scratch.model.SubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubEntityRepository extends JpaRepository<SubEntity, Long> {

    List<SubEntity> findAllByEntity(Entity entity);

//    List<SubEntity> findAllByEntity_Id(Long entityId);

}
