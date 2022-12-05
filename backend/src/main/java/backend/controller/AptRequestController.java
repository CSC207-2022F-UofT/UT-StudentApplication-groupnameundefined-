package backend.controller;

import backend.dto.AptRequestDto;
import backend.form.AptRequestForm.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AptRequestController {

	ResponseEntity<List<AptRequestDto>> getAllAptRequests();

	ResponseEntity<AptRequestDto> getAptRequestById(Long id);

	ResponseEntity<List<AptRequestDto>> getAptRequestByFromId(Long fromId);

	ResponseEntity<List<AptRequestDto>> getAptRequestByToId(Long toId);

	ResponseEntity<AptRequestDto> createAptRequest(CreateAptRequestForm input);

	ResponseEntity<AptRequestDto> updateAptRequest(UpdateAptRequestForm input);

	ResponseEntity<AptRequestDto> approveAptRequest(Long id);

	ResponseEntity<AptRequestDto> denyAptRequest(Long id);

	ResponseEntity<Long> deleteAptRequest(Long id);

}