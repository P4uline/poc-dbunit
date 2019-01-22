package com.disney.booking;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="booking")
public class BookingEntity implements Serializable {
    // BOOKING||10009|10758|28/05/2018|29/05/2018|28/04/2018|C||EUR|TBX|0|1.98|1.98|1000

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START")
    private Date start;

    @Column(name = "END")
    private Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
