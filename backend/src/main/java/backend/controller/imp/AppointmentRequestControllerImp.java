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
	private AppointmentRequestService AppointmentRequestService;

	@Autowired
	Logger logger;

	@Override
	@GetMapping("/")
	public ResponseEntity<List<AppointmentRequest>> getAllAppointmentRequests() {
		try {
			List<AppointmentRequest> allAppointmentRequests = AppointmentRequestService.getAllAppointmentRequests();
			return new ResponseEntity<List<AppointmentRequest>>(allAppointmentRequests, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentRequest> getAppointmentRequestById(@PathVariable Long id) {
		try {
			AppointmentRequest AppointmentRequest = AppointmentRequestService.getAppointmentRequestById(id);
			return new ResponseEntity<AppointmentRequest>(AppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<AppointmentRequest>> getAppointmentRequestByUserId(@PathVariable Long userId) {
		try {
			List<AppointmentRequest> allAppointmentRequests = AppointmentRequestService.getAppointmentRequestByUserId(userId);
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
			AppointmentRequest newAppointmentRequest = AppointmentRequestService.createAppointmentRequest(input);
			return new ResponseEntity<AppointmentRequest>(newAppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/approve/{id}")
	public ResponseEntity<AppointmentRequest> approveAppointmentRequest(@PathVariable Long id) {
		try {
			AppointmentRequest AppointmentRequest = AppointmentRequestService.approveAppointmentRequest(id);
			return new ResponseEntity<AppointmentRequest>(AppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/deny/{id}")
	public ResponseEntity<AppointmentRequest> denyAppointmentRequest(@PathVariable Long id) {
		try {
			AppointmentRequest AppointmentRequest = AppointmentRequestService.denyAppointmentRequest(id);
			return new ResponseEntity<AppointmentRequest>(AppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@GetMapping("/delete/{id}")
	public ResponseEntity<Long> deleteAppointmentRequest(@PathVariable Long id) {
		try {
			Long deletedId = AppointmentRequestService.deleteAppointmentRequest(id);
			return new ResponseEntity<Long>(deletedId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@PostMapping("/post")
	public ResponseEntity<AppointmentRequest> updateAppointmentRequest(@RequestBody UpdateAppointmentRequestForm input) {
		try {
			AppointmentRequest updatedAppointmentRequest = AppointmentRequestService.updateAppointmentRequest(input);
			return new ResponseEntity<AppointmentRequest>(updatedAppointmentRequest, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}