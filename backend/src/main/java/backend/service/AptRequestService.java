package backend.service;

import backend.form.AptRequestForm.*;
import backend.model.AptRequest;

import java.util.List;

public interface AptRequestService {

    /**
     * @return all AptRequests in the AptRequest Table
     */
    List<AptRequest> getAllAptRequests();

    /**
     * @param id of a AptRequest
     * @return the AptRequests this id refers to
     */
    AptRequest getAptRequestById(Long id);

    /**
     * @param fromId id of the User sending AptRequest
     * @return all AptRequests sent by user with the given id
     */
    List<AptRequest> getAptRequestByFromId(Long fromId);

    /**
     * @param toId id of the User receiving AptRequest
     * @return all AptRequests received by user with the given id
     */
    List<AptRequest> getAptRequestByToId(Long toId);

    /**
     * @param input an input of the form defined in CreateAptRequestForm
     * @return a AptRequest created with given input
     */
    AptRequest createAptRequest(CreateAptRequestForm input);

    /**
     * @param input an input of the form defined in UpdateAptRequestForm
     * @return the updated AptRequest with given input
     */
    AptRequest updateAptRequest(UpdateAptRequestForm input);

    /**
     * @param id id of the AptRequest needs to be approved
     * @return the approved AptRequest
     */
    AptRequest approveAptRequest(Long id);

    /**
     * @param id id of the AptRequest needs to be denied
     * @return the denied AptRequest
     */
    AptRequest denyAptRequest(Long id);

    /**
     * @param id id of the AptRequest needs to be deleted
     * @return id of the deleted AptRequest
     */
    Long deleteAptRequest(Long id);


}