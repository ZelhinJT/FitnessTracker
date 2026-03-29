package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Nullable
    @Column(name = "weight")
    private int weight;

    @Nullable
    @Column(name = "height")
    private int height;

    @Nullable
    @Column(name = "hearth_rate")
    private int heartRate;

    public HealthMetrics
            (
                    final int user_id,
                    final LocalDate date,
                    final int weight,
                    final int height,
                    final int heartRate) {

        this.user_id = user_id;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;

    }

}