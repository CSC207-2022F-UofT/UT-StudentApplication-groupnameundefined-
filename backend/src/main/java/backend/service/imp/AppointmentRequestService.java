package backend.service.imp;

import backend.model.AppointmentRequest;
import backend.model.User;

import java.util.List;
import java.util.Optional;

public interface AppointmentRequestService {
    List<AppointmentRequest> getAllAptReq();

    Optional<AppointmentRequest> getAptReqById(Long id);

    AppointmentRequest createAptRequest(AppointmentRequest aptRequest);
}
