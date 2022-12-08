package backend.service.imp;

import java.util.List;
import java.util.Optional;

import backend.exception.exceptions.BadRequestException;
import backend.form.AptRequestForm.*;
import backend.model.AptBlock;
import backend.model.AptRequest;
import backend.model.Timetable;
import backend.model.User;
import backend.repository.AptRequestRepository;
import backend.repository.TimetableRepository;
import backend.repository.UserRepository;
import backend.service.AptRequestService;
import backend.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.exception.exceptions.EntityNotFoundException;

@Service
public class AptRequestServiceImp implements AptRequestService {

	private final Logger logger;

	private final AptRequestRepository aptRequestRepository;

	private final UserRepository userRepository;
	private final UserService userService;
	private final TimetableRepository timetableRepository;

	@Autowired
	public AptRequestServiceImp(
			Logger logger,
			AptRequestRepository aptRequestRepository,
			UserRepository userRepository,
			UserService userService,
			TimetableRepository timetableRepository
	) {
		this.logger = logger;
		this.aptRequestRepository = aptRequestRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.timetableRepository = timetableRepository;
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

		throw new EntityNotFoundException(
				String.format("Unable to find appointment request with id '%d'", id),
				AptRequest.class
		);
	}

	@Override
	public List<AptRequest> getAptRequestByFromId(Long fromId) {
		return aptRequestRepository.findByFromId(fromId);
	}

	@Override
	public List<AptRequest> getAptRequestByToId(Long toId) {
		return aptRequestRepository.findByToId(toId);
	}

	@Override
	public AptRequest createAptRequest(CreateAptRequestForm input) {
		User fromUser = userService.getUserById(input.getFromId());
		User toUser = userService.getUserById(input.getToId());

		AptRequest aptRequest = new AptRequest(
				fromUser,
				toUser,
				input.getMessage()
		);

		AptBlock aptBlock = new AptBlock(
				input.getStartDay(),
				input.getStartMil(),
				input.getEndDay(),
				input.getEndMil(),
				input.getRepetition(),
				input.getRepetitionTime()
		);

		aptBlock.setAptRequest(aptRequest);
		aptRequest.setAptBlock(aptBlock);

		Timetable timetable = fromUser.getStudentProfile().getTimetable();
		aptBlock.addTimetable(timetable);

		return aptRequestRepository.save(aptRequest);
	}

	@Override
	public AptRequest updateAptRequest(UpdateAptRequestForm input) {
		Optional<AptRequest> _aptRequest = aptRequestRepository.findById(input.getId());

		if (_aptRequest.isEmpty()) {
			throw new EntityNotFoundException(
					String.format("Unable to find appointment request with id '%s'.", input.getId()),
					AptRequest.class
			);
		}

		AptRequest aptRequest = _aptRequest.get();
		aptRequest.setMessage(input.getMessage());

		AptBlock aptBlock = aptRequest.getAptBlock();
		aptBlock.update(
				input.getStartDay(),
				input.getStartMil(),
				input.getEndDay(),
				input.getEndMil(),
				input.getRepetition(),
				input.getRepetitionTime(),
				input.getLocation()
		);

		return aptRequestRepository.save(aptRequest);
	}

	@Override
	public AptRequest approveAptRequest(Long id) {
		AptRequest aptRequest = this.getAptRequestById(id);

		if (!aptRequest.getStatus().equals("PENDING")) {
			throw new BadRequestException("The designated appointment request has already been processed.");
		}

		aptRequest.setStatus("APPROVED");

		Timetable timetable = aptRequest.getTo().getStudentProfile().getTimetable();

		timetable.addBlock(aptRequest.getAptBlock());
		timetableRepository.save(timetable);

		return aptRequestRepository.save(aptRequest);


	}

	@Override
	public AptRequest denyAptRequest(Long id) {
		AptRequest aptRequest = this.getAptRequestById(id);

		if (aptRequest.getStatus().equals("PENDING")) {
			aptRequest.setStatus("DENIED");
			aptRequest.setAptBlock(null);

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

		throw new EntityNotFoundException(
				String.format("Unable to find a appointment request with id '%s'.", id),
				AptRequest.class
		);
	}

}