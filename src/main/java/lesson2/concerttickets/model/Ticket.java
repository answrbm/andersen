package lesson2.concerttickets.model;

import lesson2.concerttickets.model.abstraction.Entity;
import lesson2.concerttickets.model.abstraction.Shareable;
import lesson2.concerttickets.model.annotation.NullableWarning;
import lesson2.concerttickets.model.enums.StadiumSector;
import lesson2.concerttickets.utils.TicketValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends Entity implements Shareable {

    @NullableWarning
    private String concertHall;
    private Integer eventCode;
    private Long concertTime;
    private boolean isPromo;
    private StadiumSector sector;
    private double maxBackpackWeight;
    private BigDecimal price;
    private LocalDateTime createdAt;

    public Ticket() {
        this.createdAt = LocalDateTime.now();
    }

    public Ticket(Long id, String concertHall, Integer eventCode, Long concertTime, boolean isPromo, StadiumSector sector, double maxBackpackWeight, BigDecimal ticketPrice) {
        super(id);
        TicketValidator.idValidator(getId());
        this.concertHall = TicketValidator.concertHallValidator(concertHall);
        this.eventCode = TicketValidator.eventCodeValidator(eventCode);
        this.concertTime = concertTime;
        this.isPromo = isPromo;
        this.sector = sector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.createdAt = LocalDateTime.now();
        this.price = ticketPrice;
    }

    public Ticket(String concertHall, Integer eventCode, Long concertTime) {
        this.concertHall = TicketValidator.concertHallValidator(concertHall);
        this.eventCode = TicketValidator.eventCodeValidator(eventCode);
        this.concertTime = concertTime;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public void share(String phone) {
        System.out.println("Shared by phone: " + phone);
    }

    @Override
    public void share(String phone, String email) {
        System.out.println("Shared by phone: " + phone + " and email: " + email);
    }

    public String getConcertHall() {
        return concertHall;
    }

    public Integer getEventCode() {
        return eventCode;
    }

    public Long getConcertTime() {
        return concertTime;
    }

    public void setConcertTime(Long concertTime) {
        this.concertTime = concertTime;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public StadiumSector getSector() {
        return sector;
    }

    public void setSector(StadiumSector sector) {
        this.sector = sector;
    }

    public double getMaxBackpackWeight() {
        return maxBackpackWeight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (isPromo != ticket.isPromo) return false;
        if (Double.compare(ticket.maxBackpackWeight, maxBackpackWeight) != 0) return false;
        if (!Objects.equals(concertHall, ticket.concertHall)) return false;
        if (!Objects.equals(eventCode, ticket.eventCode)) return false;
        if (!Objects.equals(concertTime, ticket.concertTime)) return false;
        if (sector != ticket.sector) return false;
        if (!Objects.equals(price, ticket.price)) return false;
        return Objects.equals(createdAt, ticket.createdAt);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = concertHall != null ? concertHall.hashCode() : 0;
        result = 31 * result + (eventCode != null ? eventCode.hashCode() : 0);
        result = 31 * result + (concertTime != null ? concertTime.hashCode() : 0);
        result = 31 * result + (isPromo ? 1 : 0);
        result = 31 * result + (sector != null ? sector.hashCode() : 0);
        temp = Double.doubleToLongBits(maxBackpackWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + super.getId() + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", concertTime=" + concertTime +
                ", isPromo=" + isPromo +
                ", sector=" + sector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
