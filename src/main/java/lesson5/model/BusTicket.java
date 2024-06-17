package lesson5.model;

public class BusTicket {

    String ticketClass;

    String ticketType;

    String startDate;

    String price;

    public BusTicket() {
    }

    public BusTicket(String ticketClass, String ticketType, String startDate, String price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
        this.price = price;
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

        if (ticketClass != null ? !ticketClass.equals(busTicket.ticketClass) : busTicket.ticketClass != null)
            return false;
        if (ticketType != null ? !ticketType.equals(busTicket.ticketType) : busTicket.ticketType != null) return false;
        if (startDate != null ? !startDate.equals(busTicket.startDate) : busTicket.startDate != null) return false;
        return price != null ? price.equals(busTicket.price) : busTicket.price == null;
    }

    @Override
    public int hashCode() {
        int result = ticketClass != null ? ticketClass.hashCode() : 0;
        result = 31 * result + (ticketType != null ? ticketType.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusTicket{" +
                "ticketClass='" + ticketClass + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
