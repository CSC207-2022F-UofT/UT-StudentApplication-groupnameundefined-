package backend.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.form.AppointmentRequestForm.*;
import backend.model.AppointmentRequest;
import backend.model.User;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.repository.AppointmentRequestRepository;
import backend.service.AppointmentRequestService;

import javax.persistence.EntityNotFoundException;

@Service
public class AppointmentRequestServiceImp implements AppointmentRequestService {

	@Autowired
	AppointmentRequestRepository aptRepository;

	@Autowired
	private UserRepository userRepository;


	@Override
	public List<AppointmentRequest> getAllAppointmentRequests() {
		List<AppointmentRequest> appointmentRequests = new ArrayList<AppointmentRequest>();

		appointmentRequests.addAll(aptRepository.findAll());

		return appointmentRequests;
	}

	@Override
	public AppointmentRequest getAppointmentRequestById(Long id) {
		Optional<AppointmentRequest> appointmentRequest = aptRepository.findById(id);
		if (appointmentRequest.isPresent()) {
			return appointmentRequest.get();
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public List<AppointmentRequest> getAppointmentRequestByFromId(Long fromId) {
		return aptRepository.findByFromId(fromId);
	}

	@Override
	public List<AppointmentRequest> getAppointmentRequestByToId(Long toId) {
		return aptRepository.findByToId(toId);
	}

	public AppointmentRequest createAppointmentRequest(CreateAppointmentRequestForm input) {

		Optional<User> fromUser = userRepository.findById(input.getFrom());
		Optional<User> toUser = userRepository.findById(input.getTo());

		if (fromUser.isEmpty()) {
			throw new backend.exception.exceptions.EntityNotFoundException(
					String.format("Unable to find user with id '%s'.", input.getFrom()), User.class);
		}

		if (toUser.isEmpty()) {
			throw new backend.exception.exceptions.EntityNotFoundException(
					String.format("Unable to find user with id '%s'.", input.getTo()), User.class);
		}
		AppointmentRequest appointmentRequest = new AppointmentRequest(
				fromUser.get(),
				toUser.get(),
				input.getMessage(),
				input.getLocation(),
				input.getStartDay(),
				input.getStartMil(),
				input.getEndDay(),
				input.getEndMil());

		return aptRepository.save(appointmentRequest);
	}

	@Override
	public AppointmentRequest approveAppointmentRequest(Long id) {
		Optional<AppointmentRequest> AppointmentRequest = aptRepository.findById(id);
		if (AppointmentRequest.isPresent()) {
			AppointmentRequest _appointmentRequest = AppointmentRequest.get();
			if (_appointmentRequest.getStatus().equals("PENDING")) {
				_appointmentRequest.setStatus("APPROVED");
				aptRepository.save(_appointmentRequest);
			} else {
				throw new RuntimeException();
			}
			return _appointmentRequest;
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public AppointmentRequest denyAppointmentRequest(Long id) {
		Optional<AppointmentRequest> AppointmentRequest = aptRepository.findById(id);
		if (AppointmentRequest.isPresent()) {
			AppointmentRequest _AppointmentRequest = AppointmentRequest.get();
			if (_AppointmentRequest.getStatus().equals("PENDING")) {
				_AppointmentRequest.setStatus("DENIED");
				aptRepository.save(_AppointmentRequest);
			} else {
				throw new RuntimeException();
			}
			return _AppointmentRequest;
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Long deleteAppointmentRequest(Long id) {
		try {
			aptRepository.deleteById(id);
			return id;
		} catch (Exception e) {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public AppointmentRequest updateAppointmentRequest(UpdateAppointmentRequestForm input) {
		Optional<AppointmentRequest> _updateApt = aptRepository.findById(input.getId());

		if (!_updateApt.isPresent()) {
			throw new EntityNotFoundException();
		}
		AppointmentRequest updateApt = _updateApt.get();
		updateApt.updateAppointmentRequest(
				input.getMessage(),
				input.getLocation(),
				input.getStartDay(),
				input.getStartMil(),
				input.getEndDay(),
				input.getEndMil());

		return aptRepository.save(updateApt);
	}


}