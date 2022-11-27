package backend.controller;

import backend.model.AppointmentRequest;
import backend.model.User;
import backend.service.imp.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface AppointmentRequestController {

    @Autowired
    AppointmentRequestService aptReqService = null;

    @Override
    @GetMapping("/appointmentRequest")
    public ResponseEntity<List<AppointmentRequest>> getAllAptReq() {
        try {
            List<AppointmentRequest> aptRequests = aptReqService.getAllAptReq();
            return new ResponseEntity<>(aptRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/users/{id}")
    public ResponseEntity<AppointmentRequest> getAptReqById(@PathVariable("aptId") Long aptId) {
        Optional<AppointmentRequest> aptRequest = aptReqService.getAptReqById(aptId);

        if (aptRequest.isPresent()) {
            return new ResponseEntity<>(aptRequest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping("/users")
    public ResponseEntity<AppointmentRequest> createAptRequest(@RequestBody AppointmentRequest aptRequest) {
        try {
            AppointmentRequest _aptRequest = aptReqService.createAptRequest(aptRequest);

            return new ResponseEntity<>(_aptRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
