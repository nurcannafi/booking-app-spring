package az.edu.turing.domain.repository;

import az.edu.turing.domain.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {

    Optional<PassengerEntity> findByFirstNameAndLastName(String firstName, String lastName);

    List<PassengerEntity> findByAgeBetween(int minAge, int maxAge);

}
