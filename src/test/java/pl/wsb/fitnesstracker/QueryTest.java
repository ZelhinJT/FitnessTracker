package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.event.EventRepository;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.TrainingRepository;

import java.util.List;

@SpringBootTest
class QueryTest {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private EventRepository eventRepository;
    @Test
    void queryReturnsValue(){
        System.out.println("Łączny dystans: " + trainingRepository.sumUserTrainingsDistance(2L));
    }

    @Test
    void returnEventList(){
        System.out.println(eventRepository.listEventNames());
    }

}
