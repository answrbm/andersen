package lesson5.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusTicket {

    private Long id;
    private String ticketClass;
    private String ticketType;
    private String startDate;
    private String price;
    private LocalDateTime creationDate;
    private Long userId;
}
