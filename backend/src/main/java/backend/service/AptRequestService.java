package backend.service;

import backend.form.AptRequestForm.*;
import backend.model.AptRequest;

import java.util.List;

public interface AptRequestService {

	/**
	 * @return All AptRequests in the AptRequest Table
	 */
	List<AptRequest> getAllAptRequests();

	/**
	 * @param id ID of the AptRequest to find
	 * @return The designated AptRequest with the given id
	 * @throws backend.exception.exceptions.EntityNotFoundException If the AptRequest could not be found
	 */
	AptRequest getAptRequestById(Long id);

	/**
	 * @param fromId ID of the User sending AptRequest
	 * @return All AptRequests sent by user with the given id
	 */
	List<AptRequest> getAptRequestByFromId(Long fromId);

	/**
	 * @param toId ID of the User receiving AptRequest
	 * @return All AptRequests received by user with the given id
	 * @throws backend.exception.exceptions.EntityNotFoundException if the user could not be found with toId
	 */
	List<AptRequest> getAptRequestByToId(Long toId);

	/**
	 * @param input An input defined by CreateAptRequestForm
	 * @return An AptRequest created with the given input
	 */
	AptRequest createAptRequest(CreateAptRequestForm input);

	/**
	 * @param input An input defined by UpdateAptRequestForm
	 * @return The updated AptRequest with the given input
	 * @throws backend.exception.exceptions.EntityNotFoundException if the AptRequest could not be found
	 */
	AptRequest updateAptRequest(UpdateAptRequestForm input);

	/**
	 * @param id ID of the AptRequest to be approved
	 * @return The approved AptRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the AptRequest could not be found
	 */
	AptRequest approveAptRequest(Long id);

	/**
	 * @param id ID of the AptRequest to be denied
	 * @return The denied AptRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the AptRequest could not be found
	 */
	AptRequest denyAptRequest(Long id);

	/**
	 * @param id ID of the AptRequest to be deleted
	 * @return id of the deleted AptRequest
	 * @throws backend.exception.exceptions.EntityNotFoundException if the AptRequest could not be found
	 */
	Long deleteAptRequest(Long id);


}