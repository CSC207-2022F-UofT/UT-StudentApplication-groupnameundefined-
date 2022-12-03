package backend.controller;

import backend.form.AppointmentRequestForm.*;
import backend.model.AppointmentRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AppointmentRequestController {

	ResponseEntity<List<AppointmentRequest>> getAllAppointmentRequests();

	ResponseEntity<AppointmentRequest> getAppointmentRequestById(Long id);

	ResponseEntity<List<AppointmentRequest>> getAppointmentRequestByUserId(Long userId);

	ResponseEntity<AppointmentRequest> createAppointmentRequest(CreateAppointmentRequestForm input);

	ResponseEntity<AppointmentRequest> approveAppointmentRequest(Long id);

	ResponseEntity<AppointmentRequest> denyAppointmentRequest(Long id);

	ResponseEntity<Long> deleteAppointmentRequest(Long id);

	ResponseEntity<AppointmentRequest> updateAppointmentRequest(UpdateAppointmentRequestForm input);

}