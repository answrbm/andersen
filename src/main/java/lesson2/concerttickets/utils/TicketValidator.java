package lesson2.concerttickets.utils;

import lesson2.concerttickets.exception.TicketBadParamsException;

public class TicketValidator {

    public static Long idValidator(Long id) {
        long digitsCounter = 0;
        long idTemp = id;
        while(idTemp != 0) {
            idTemp = idTemp/10;
            digitsCounter++;
        }
        if(digitsCounter > 4)
            throw new TicketBadParamsException("Id should have max 4 digits");
        return id;
    }

    public static String concertHallValidator(String concertHall) {
        if(concertHall.length() > 10)
            throw new TicketBadParamsException("Concert hall should have max 10 chars");
        return concertHall;
    }

    public static Integer eventCodeValidator(Integer eventCode) {
        int digitsCounter = 0;
        int eventCodeTemp = eventCode;
        while(eventCodeTemp != 0) {
            eventCodeTemp = eventCodeTemp/10;
            digitsCounter++;
        }
        if(digitsCounter > 3)
            throw new TicketBadParamsException("Event code should have max 3 digits");
        return eventCode;
    }
}
