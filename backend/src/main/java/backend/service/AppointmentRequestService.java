package backend.service;

import backend.form.AppointmentRequestForm.*;
import backend.model.AppointmentRequest;
import backend.model.User;

import java.util.List;

public interface AppointmentRequestService {
	List<AppointmentRequest> getAllAppointmentRequests();

	AppointmentRequest getAppointmentRequestById(Long id);

	List<AppointmentRequest> getAppointmentRequestByFromId(Long fromId);
	List<AppointmentRequest> getAppointmentRequestByToId(Long toId);

	AppointmentRequest createAppointmentRequest(CreateAppointmentRequestForm input);

	AppointmentRequest approveAppointmentRequest(Long id);

	AppointmentRequest denyAppointmentRequest(Long id);

	Long deleteAppointmentRequest(Long id);

	AppointmentRequest updateAppointmentRequest(UpdateAppointmentRequestForm input);

}