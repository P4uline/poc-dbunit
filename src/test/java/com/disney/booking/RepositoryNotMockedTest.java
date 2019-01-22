package com.disney.booking;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryNotMockedTest {

    @Autowired
    public BookingRepository bookingRepository;

    @Test
    public void should_test_repository() {
        Assertions.assertThat(bookingRepository.findAll()).isEmpty();
    }

    @Test
    public void should_test_repository_adding_value() {
        BookingEntity booking = new BookingEntity();
        booking.setDescription("desc");
        booking.setStart(new Date());
        booking.setEnd(new Date());
        bookingRepository.save(booking);
        List<BookingEntity> all = bookingRepository.findAll();
        Assertions.assertThat(all).isNotEmpty();
        Assertions.assertThat(bookingRepository.findOne(1L)).isEqualTo(booking);
        bookingRepository.delete(1L);
        Assertions.assertThat(bookingRepository.findOne(1L)).isNull();
    }


}
