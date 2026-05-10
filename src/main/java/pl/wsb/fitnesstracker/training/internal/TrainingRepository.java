package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.training.api.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(
            value = "SELECT SUM(DISTANCE) FROM TRAININGS WHERE USER_ID = :user_id",
            nativeQuery = true
    )
    float sumUserTrainingsDistance(@Param("user_id") Long user_id);
}
