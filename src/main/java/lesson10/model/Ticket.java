package lesson10.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticket_type")
    private String ticketType;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "user_id")
    private Long userId;
}
