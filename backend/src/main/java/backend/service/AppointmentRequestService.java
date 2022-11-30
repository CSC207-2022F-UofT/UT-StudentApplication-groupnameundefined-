package backend.service;

import backend.model.AppointmentRequest;
import backend.model.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AppointmentRequestService {
    List<AppointmentRequest> getAllAptReq();

    AppointmentRequest getAptRequestById(Long id);
    AppointmentRequest createAptRequest(User invitee, User attendee, Timestamp time, String location);

    String acceptAptRequest(AppointmentRequest aptRequest);

    String declineAptRequest(AppointmentRequest aptRequest);

    String  rescheduleAptRequest(AppointmentRequest aptRequest);

    List<AppointmentRequest> getAptRequestByUserId(Long userId);
}
