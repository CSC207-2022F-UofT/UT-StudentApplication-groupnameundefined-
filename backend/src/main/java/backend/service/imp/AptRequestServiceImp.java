package backend.service.imp;

import java.util.List;
import java.util.Optional;

import backend.exception.exceptions.BadRequestException;
import backend.form.AptRequestForm.*;
import backend.model.AptRequest;
import backend.model.User;
import backend.repository.AptRequestRepository;
import backend.repository.UserRepository;
import backend.service.AptRequestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.exception.exceptions.EntityNotFoundException;

@Service
public class AptRequestServiceImp implements AptRequestService {

	private final Logger logger;

	private final AptRequestRepository aptRequestRepository;

	private final UserRepository userRepository;

	@Autowired
	public AptRequestServiceImp(
			Logger logger,
			AptRequestRepository aptRequestRepository,
			UserRepository userRepository
	) {
		this.logger = logger;
		this.aptRequestRepository = aptRequestRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<AptRequest> getAllAptRequests() {
		return aptRequestRepository.findAll();
	}

	@Override
	public AptRequest getAptRequestById(Long id) {
		Optional<AptRequest> aptRequest = aptRequestRepository.findById(id);
		if (aptRequest.isPresent()) {
			return aptRequest.get();
		}

		throw new EntityNotFoundException(String.format("Unable to find appointment request with id '%d'", id), AptRequest.class);
	}

	@Override
	public List<AptRequest> getAptRequestByFromId(Long fromId) {
		return aptRequestRepository.findByFromId(fromId);
	}

	@Override
	public List<AptRequest> getAptRequestByToId(Long toId) {
		return aptRequestRepository.findByToId(toId);
	}

	public AptRequest createAptRequest(CreateAptRequestForm input) {

		Optional<User> fromUser = userRepository.findById(input.getFromId());
		Optional<User> toUser = userRepository.findById(input.getToId());

		if (fromUser.isEmpty()) {
			throw new backend.exception.exceptions.EntityNotFoundException(
					String.format("Unable to find user with id '%s'.", input.getFromId()), User.class);
		}

		if (toUser.isEmpty()) {
			throw new backend.exception.exceptions.EntityNotFoundException(
					String.format("Unable to find user with id '%s'.", input.getToId()), User.class);
		}

		AptRequest aptRequest = new AptRequest(
				fromUser.get(),
				toUser.get(),
				input.getMessage(),
				input.getLocation(),
				input.getStartDay(),
				input.getStartMil(),
				input.getEndDay(),
				input.getEndMil(),
				input.getRepetition(),
				input.getRepetitionTime()
		);

		return aptRequestRepository.save(aptRequest);
	}

	@Override
	public AptRequest updateAptRequest(UpdateAptRequestForm input) {
		Optional<AptRequest> _aptRequest = aptRequestRepository.findById(input.getId());

		if (_aptRequest.isEmpty()) {
			throw new EntityNotFoundException(String.format("Unable to find appointment request with id '%s'.", input.getId()), AptRequest.class);
		}
		AptRequest aptRequest = _aptRequest.get();
		aptRequest.updateAppointmentRequest(
				input.getMessage(),
				input.getLocation(),
				input.getStartDay(),
				input.getStartMil(),
				input.getEndDay(),
				input.getEndMil(),
				input.getRepetition(),
				input.getRepetitionTime()
		);

		return aptRequestRepository.save(aptRequest);
	}

	@Override
	public AptRequest approveAptRequest(Long id) {
		AptRequest aptRequest = this.getAptRequestById(id);

		if (aptRequest.getStatus().equals("PENDING")) {
			aptRequest.setStatus("APPROVED");
			return aptRequestRepository.save(aptRequest);
		}

		throw new BadRequestException("The designated appointment request has already been processed.");
	}

	@Override
	public AptRequest denyAptRequest(Long id) {
		AptRequest aptRequest = this.getAptRequestById(id);

		if (aptRequest.getStatus().equals("PENDING")) {
			aptRequest.setStatus("DENIED");
			return aptRequestRepository.save(aptRequest);
		}

		throw new BadRequestException("The designated appointment request has already been processed.");
	}

	@Override
	public Long deleteAptRequest(Long id) {
		if (aptRequestRepository.existsById(id)) {
			aptRequestRepository.deleteById(id);
			return id;
		}

		throw new EntityNotFoundException(String.format("Unable to find a appointment request with id '%s'.", id), AptRequest.class);
	}

}