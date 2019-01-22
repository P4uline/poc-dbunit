package com.disney.booking;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PocDataJpaTest {

    @Autowired
    public BookingRepository bookingRepository;

    @Before
    public void init() {
        BookingEntity booking = getBookingEntity();
        bookingRepository.save(booking);
    }

    @Test
    public void should_test_repository() {
        Assertions.assertThat(bookingRepository.findAll()).hasSize(1);
        BookingEntity booking = getBookingEntity();

        // Trying to make the database dirt, but it's ok, database is cleaned for every test
        bookingRepository.save(booking);
        bookingRepository.save(booking);
        bookingRepository.save(booking);
        bookingRepository.save(booking);
    }

    @Test
    public void should_test_repository_adding_value() {
        // Trying to make the database dirt, but it's ok, database is cleaned for every test
        Assertions.assertThat(bookingRepository.findAll()).hasSize(1);
        BookingEntity booking = getBookingEntity();
        bookingRepository.save(booking);
        List<BookingEntity> all = bookingRepository.findAll();
        Assertions.assertThat(all).isNotEmpty();
        Assertions.assertThat(bookingRepository.findOne(1L).getId()).isEqualTo(1L);

        bookingRepository.save(booking);
        bookingRepository.save(booking);
        bookingRepository.save(booking);
        bookingRepository.save(booking);
    }

    private BookingEntity getBookingEntity() {
        BookingEntity booking = new BookingEntity();
        booking.setDescription("desc");
        booking.setStart(new Date());
        booking.setEnd(new Date());
        return booking;
    }


}