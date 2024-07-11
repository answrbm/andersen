package lesson8.model;

import lesson2.concerttickets.model.abstraction.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserImpl extends User {

    private String name;
    private LocalDateTime creationDate;
}
