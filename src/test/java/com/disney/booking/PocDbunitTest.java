package com.disney.booking;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.*;

import static com.disney.booking.PocDbunitTest.BookingEntityBuilder.beBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/bookingData.xml")
public class PocDbunitTest {

    public static final String PERSITED_DESCRIPTION = "Persited Description";
    @Autowired
    public BookingRepository bookingRepository;

    @Test
    public void should_validate_we_are_using_dbunit_the_first_element_is_a_stub() {
        BookingEntity result = bookingRepository.findOne(1L);
        assertThat(result).isNotNull();
        assertThat(result.getDescription()).isEqualTo("Stub Test Description");
    }

    @Test
    public void should_save_booking_with_a_description_and_find_this_booking_in_repository() {
        bookingRepository.save(buildBookingEntity(PERSITED_DESCRIPTION));

        assertThat(bookingRepository.findAll()) //
                .filteredOn("description", PERSITED_DESCRIPTION) //
                .hasSize(1);
    }

    private BookingEntity buildBookingEntity(String expectedDescription) {
        return beBuilder() //
                .withDescription(expectedDescription) //
                .withStart(new Date()) //
                .withEnd(new Date()).get();
    }

    static class BookingEntityBuilder {

        private BookingEntity bookingEntity = new BookingEntity();

        public static BookingEntityBuilder beBuilder() {
            return new BookingEntityBuilder();
        }

        public BookingEntityBuilder withDescription(String expectedDescription) {
             bookingEntity.setDescription(expectedDescription);
             return this;
        }

        public BookingEntityBuilder withStart(Date date) {
            bookingEntity.setStart(date);
            return this;
        }

        public BookingEntityBuilder withEnd(Date date) {
            bookingEntity.setEnd(date);
            return this;
        }

        public BookingEntity get() {
            return bookingEntity;
        }
    }
}
