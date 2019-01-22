package com.disney.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;


    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping(value = "/bookings")
    public List<BookingEntity> viewBookings() {
        return this.bookingRepository.findAll();
    }


    @GetMapping(value = "/populate-bookings")
    public List<BookingEntity> populateBookings() {
        List<BookingEntity> bookings = generateBookingEntities();
        bookingRepository.save(bookings);
        return this.bookingRepository.findAll();
    }

    @GetMapping(value = "/populate-booking")
    public List<BookingEntity> populateBookings(@RequestParam(name="description") String description) {

        BookingEntity booking = new BookingEntity();
        booking.setDescription(description);
        booking.setEnd(new Date());
        booking.setStart(new Date());
        bookingRepository.save(booking);
        return this.bookingRepository.findAll();
    }


    private List<BookingEntity> generateBookingEntities() {
        List<BookingEntity> bookings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookingEntity booking = new BookingEntity();
            booking.setDescription("description" + i);
            booking.setEnd(new Date());
            booking.setStart(new Date());
            bookings.add(booking);
        }
        return bookings;
    }

}
