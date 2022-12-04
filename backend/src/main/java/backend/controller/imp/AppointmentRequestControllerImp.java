package backend.controller.imp;

import backend.controller.AppointmentRequestController;
import backend.form.AppointmentRequestForm.*;
import backend.model.AppointmentRequest;
import backend.service.AppointmentRequestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/friend-request")
public class AppointmentRequestControllerImp implements AppointmentRequestController {

	@Autowired
	private AppointmentRequestService appointmentRequestService;

	@Autowired
	Logger logger;

	@Override
	@GetMapping("/")
	public ResponseEntity<List<AppointmentRequest>> getAllAppointmentRequests() {
		try {
			List<AppointmentRequest> allAppointmentRequests = appointmentRequestService.getAllAppointmentRequests();
			return new ResponseEntity<List<AppointmentRequest>>(allAppointmentRequests, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentRequest> getAppointmentRequestById(@PathVariable Long id) {
		try {
			AppointmentRequest AppointmentRequest = appointmentRequestService.getAppointmentRequestById(id);
			return new ResponseEntity<AppointmentRequest>(AppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<AppointmentRequest>> getAppointmentRequestByFromId(Long fromId) {
		try {
			List<AppointmentRequest> allAppointmentRequests = appointmentRequestService.getAppointmentRequestByFromId(fromId);
			return new ResponseEntity<List<AppointmentRequest>>(allAppointmentRequests, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<AppointmentRequest>> getAppointmentRequestByToId(Long toId) {
		try {
			List<AppointmentRequest> allAppointmentRequests = appointmentRequestService.getAppointmentRequestByToId(toId);
			return new ResponseEntity<List<AppointmentRequest>>(allAppointmentRequests, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	@PostMapping("/create")
	public ResponseEntity<AppointmentRequest> createAppointmentRequest(@RequestBody CreateAppointmentRequestForm input) {
		try {
			AppointmentRequest newAppointmentRequest = appointmentRequestService.createAppointmentRequest(input);
			return new ResponseEntity<AppointmentRequest>(newAppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/approve/{id}")
	public ResponseEntity<AppointmentRequest> approveAppointmentRequest(@PathVariable Long id) {
		try {
			AppointmentRequest AppointmentRequest = appointmentRequestService.approveAppointmentRequest(id);
			return new ResponseEntity<AppointmentRequest>(AppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/deny/{id}")
	public ResponseEntity<AppointmentRequest> denyAppointmentRequest(@PathVariable Long id) {
		try {
			AppointmentRequest AppointmentRequest = appointmentRequestService.denyAppointmentRequest(id);
			return new ResponseEntity<AppointmentRequest>(AppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/delete/{id}")
	public ResponseEntity<Long> deleteAppointmentRequest(@PathVariable Long id) {
		try {
			Long deletedId = appointmentRequestService.deleteAppointmentRequest(id);
			return new ResponseEntity<Long>(deletedId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PostMapping("/post")
	public ResponseEntity<AppointmentRequest> updateAppointmentRequest(@RequestBody UpdateAppointmentRequestForm input) {
		try {
			AppointmentRequest updatedAppointmentRequest = appointmentRequestService.updateAppointmentRequest(input);
			return new ResponseEntity<AppointmentRequest>(updatedAppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}