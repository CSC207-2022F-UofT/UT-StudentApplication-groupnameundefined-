package backend.service;

import backend.form.AppointmentRequestForm.*;
import backend.model.AppointmentRequest;

import java.util.List;

public interface AppointmentRequestService {
	List<AppointmentRequest> getAllAppointmentRequests();

	AppointmentRequest getAppointmentRequestById(Long id);

	List<AppointmentRequest> getAppointmentRequestByUserId(Long userId);

	AppointmentRequest createAppointmentRequest(CreateAppointmentRequestForm input);

	AppointmentRequest approveAppointmentRequest(Long id);

	AppointmentRequest denyAppointmentRequest(Long id);

	Long deleteAppointmentRequest(Long id);

	AppointmentRequest updateAppointmentRequest(UpdateAppointmentRequestForm input);
}