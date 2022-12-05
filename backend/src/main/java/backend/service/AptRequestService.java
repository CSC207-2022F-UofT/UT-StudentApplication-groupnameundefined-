package backend.service;

import backend.form.AptRequestForm.*;
import backend.model.AptRequest;

import java.util.List;

public interface AptRequestService {

	List<AptRequest> getAllAptRequests();

	AptRequest getAptRequestById(Long id);

	List<AptRequest> getAptRequestByFromId(Long fromId);

	List<AptRequest> getAptRequestByToId(Long toId);

	AptRequest createAptRequest(CreateAptRequestForm input);

	AptRequest updateAptRequest(UpdateAptRequestForm input);

	AptRequest approveAptRequest(Long id);

	AptRequest denyAptRequest(Long id);

	Long deleteAptRequest(Long id);


}