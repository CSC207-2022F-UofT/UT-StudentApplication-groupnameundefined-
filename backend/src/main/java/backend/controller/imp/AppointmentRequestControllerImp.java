package backend.controller.imp;

import backend.controller.AppointmentRequestController;
import backend.model.AppointmentRequest;
import backend.model.User;
import backend.service.AppointmentRequestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/appointment-request")
public class AppointmentRequestControllerImp implements AppointmentRequestController {

    @Autowired
    AppointmentRequestService aptReqService = null;

    @Autowired
    Logger logger;
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
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentRequest> getAptRequestById(@PathVariable Long id) {
        try{
            AppointmentRequest aptRequest = aptReqService.getAptRequestById(id);
            return new ResponseEntity<AppointmentRequest>(aptRequest, HttpStatus.OK);
        } catch (Exception e){
            logger.error("error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppointmentRequest>> getAptRequestByUserId(@PathVariable Long userId) {
        try{
            List<AppointmentRequest> allFriendRequests = aptReqService.getAptRequestByUserId(userId);
            return new ResponseEntity<List<AppointmentRequest>>(allFriendRequests, HttpStatus.OK);
        } catch (Exception e){
            logger.error("error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
