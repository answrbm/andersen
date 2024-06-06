package lesson2.concerttickets.utils;

import lesson2.concerttickets.exception.TicketBadParamsException;

public class TicketValidator {

    public static String idValidator(String id) {
        if(id.length() > 4) throw new TicketBadParamsException("Id should have max 4 digits and/or chars");
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
