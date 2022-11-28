package backend.controller.imp;

import backend.controller.AppointmentRequestController;
import backend.model.AppointmentRequest;
import backend.model.User;
import backend.service.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/appointment-request")
public class AppointmentRequestControllerImp implements AppointmentRequestController {

    @Autowired
    AppointmentRequestService aptReqService = null;

    @Override
    @GetMapping("/appointment-request")
    public ResponseEntity<List<AppointmentRequest>> getAllAptReq() {
        try {
            List<AppointmentRequest> aptRequests = aptReqService.getAllAptReq();
            return new ResponseEntity<>(aptRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/appointment-request/{id}")
    public ResponseEntity<AppointmentRequest> getAptReqById(@RequestParam Long aptId) {
        Optional<AppointmentRequest> aptRequest = aptReqService.getAptReqById(aptId);

        if (aptRequest.isPresent()) {
            return new ResponseEntity<>(aptRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/create-appointment-request")
    public ResponseEntity<AppointmentRequest> createAptRequest(@RequestParam User invitee, @RequestParam User attendee,
                                                               @RequestParam Timestamp time, @RequestParam String location) {
        try {
            AppointmentRequest _aptRequest = aptReqService.createAptRequest(invitee, attendee, time, location);
            return new ResponseEntity<>(_aptRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
