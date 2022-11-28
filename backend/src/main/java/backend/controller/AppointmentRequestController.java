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

    ResponseEntity<AppointmentRequest> getAptReqById(@PathVariable("aptId") Long aptId);

    @PostMapping("/create-appointment-request")
    ResponseEntity<AppointmentRequest> createAptRequest(@RequestParam User invitee, @RequestParam User attendee,
                                                        @RequestParam Timestamp time, @RequestParam String location);
}
