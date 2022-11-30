package backend.controller;

import backend.model.AppointmentRequest;
import backend.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

import java.util.List;

public interface AppointmentRequestController {
    ResponseEntity<List<AppointmentRequest>> getAllAptReq = null;

    @GetMapping("/appointmentRequest")
    ResponseEntity<List<AppointmentRequest>> getAllAptReq();
    @GetMapping("/appointment-request/{id}")
    ResponseEntity<AppointmentRequest> getAptRequestById(Long aptId);

    @GetMapping("/user/{userId}")
    ResponseEntity<List<AppointmentRequest>> getAptRequestByUserId(@PathVariable Long userId);

    @PostMapping("/create-appointment-request")
    ResponseEntity<AppointmentRequest> createAptRequest(@RequestParam User invitee, @RequestParam User attendee,
                                                        @RequestParam Timestamp time, @RequestParam String location);
}
