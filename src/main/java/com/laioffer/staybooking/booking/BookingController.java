package com.laioffer.staybooking.booking;

import com.laioffer.staybooking.model.BookingDto;
import com.laioffer.staybooking.model.BookingRequest;
import com.laioffer.staybooking.model.UserEntity;
import com.laioffer.staybooking.model.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingDto> getGuestBookings(@AuthenticationPrincipal UserEntity user) {
        return bookingService.findBookingsByGuestId(user.getId());
    }//user entity is found by username in token

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBooking(@RequestBody BookingRequest body,@AuthenticationPrincipal UserEntity user) {
        bookingService.createBooking(user.getId(), body.listingId(), body.checkInDate(), body.checkOutDate());
    }

    @DeleteMapping("/{bookingId}")//another way front-end pass value to back-end: pass by argument
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable long bookingId,@AuthenticationPrincipal UserEntity user) {
            bookingService.deleteBooking(user.getId(), bookingId);
    }
}

