package backend.service.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.model.AppointmentRequest;
import backend.repository.AppointmentRequestRepository;
import backend.service.AppointmentRequestService;

@Service
public class AppointmentRequestServiceImp implements AppointmentRequestService {

    @Autowired
    AppointmentRequestRepository aptRepository;


    @Override
    public List<AppointmentRequest> getAllAptReq() {
        List<AppointmentRequest> appointmentRequests = new ArrayList<AppointmentRequest>();

        aptRepository.findAll().forEach(appointmentRequests::add);

        return appointmentRequests;
    }

    @Override
    public Optional<AppointmentRequest> getAptReqById(Long aptid) {
        Optional<AppointmentRequest> appointmentRequest = aptRepository.findById(aptid);
        return appointmentRequest;
    }
    @Override
    public AppointmentRequest createAptRequest(User invitee, User attendee, Timestamp time, String location) {
        AppointmentRequest _appointmentRequest = aptRepository.save(new AppointmentRequest());
        _appointmentRequest.setInvitee(invitee);
        _appointmentRequest.setAttendee(attendee);
        _appointmentRequest.setTime(time);
        _appointmentRequest.setLocation(location);
        return _appointmentRequest;
    }
    @Override
    public String acceptAptRequest(AppointmentRequest aptRequest){
        aptRequest.setStatus("ACCEPTED");
        return aptRequest.getStatus();
    }
    @Override
    public String declineAptRequest(AppointmentRequest aptRequest){
        aptRequest.setStatus("DECLINED");

        return aptRequest.getStatus();
    }
    @Override
    public String rescheduleAptRequest(AppointmentRequest aptRequest){
        aptRequest.setStatus("RESCHEDULED");
        return aptRequest.getStatus();
    }


}
