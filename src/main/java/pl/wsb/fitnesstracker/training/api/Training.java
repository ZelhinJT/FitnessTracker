package pl.wsb.fitnesstracker.training.api;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Entity
@Getter
@Table(name="trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "Start_Time", nullable = false)
    private Date startTime;

    @Column(name = "End_Time", nullable = false)
    private Date endTime;

    @Column(name = "Activity Type", nullable = false)
    private ActivityType activityType;

    @Column(name = "Distance", nullable = false)
    private double distance;

    @Column(name = "Average_Speed", nullable = false)
    private double averageSpeed;


    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}