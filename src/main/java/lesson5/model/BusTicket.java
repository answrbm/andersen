package lesson5.model;

import java.util.Objects;

public class BusTicket {

    Long id;

    String ticketClass;

    String ticketType;

    String startDate;

    String price;

    public BusTicket() {
    }

    public BusTicket(Long id, String ticketClass, String ticketType, String startDate, String price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusTicket busTicket = (BusTicket) o;

        if (!Objects.equals(id, busTicket.id)) return false;
        if (!Objects.equals(ticketClass, busTicket.ticketClass))
            return false;
        if (!Objects.equals(ticketType, busTicket.ticketType)) return false;
        if (!Objects.equals(startDate, busTicket.startDate)) return false;
        return Objects.equals(price, busTicket.price);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ticketClass != null ? ticketClass.hashCode() : 0);
        result = 31 * result + (ticketType != null ? ticketType.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusTicket{" +
                "id=" + id +
                ", ticketClass='" + ticketClass + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
