package backend.controller.imp;

import backend.controller.AptRequestController;
import backend.dto.AptRequestDto;
import backend.form.AptRequestForm.*;
import backend.mappers.AptRequestMapper;
import backend.model.AptRequest;
import backend.service.AptRequestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AptRequestControllerImp implements AptRequestController {

    private final Logger logger;

    private final AptRequestService aptRequestService;

    private final AptRequestMapper aptRequestMapper;

    @Autowired
    public AptRequestControllerImp(
            Logger logger,
            AptRequestService aptRequestService,
            AptRequestMapper aptRequestMapper
    ) {
        this.logger = logger;
        this.aptRequestService = aptRequestService;
        this.aptRequestMapper = aptRequestMapper;
    }

    @Override
    public ResponseEntity<List<AptRequestDto>> getAllAptRequests() {
        List<AptRequest> aptRequests = aptRequestService.getAllAptRequests();
        List<AptRequestDto> aptRequestDtos = aptRequestMapper.toDtoList(aptRequests);

        return new ResponseEntity<>(aptRequestDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AptRequestDto> getAptRequestById(Long id) {
        AptRequest aptRequest = aptRequestService.getAptRequestById(id);
        AptRequestDto aptRequestDto = aptRequestMapper.toDto(aptRequest);

        return new ResponseEntity<>(aptRequestDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AptRequestDto>> getAptRequestByFromId(Long fromId) {
        List<AptRequest> aptRequests = aptRequestService.getAptRequestByFromId(fromId);
        List<AptRequestDto> aptRequestDtos = aptRequestMapper.toDtoList(aptRequests);

        return new ResponseEntity<>(aptRequestDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AptRequestDto>> getAptRequestByToId(Long toId) {
        List<AptRequest> aptRequests = aptRequestService.getAptRequestByToId(toId);
        List<AptRequestDto> aptRequestDtos = aptRequestMapper.toDtoList(aptRequests);

        return new ResponseEntity<>(aptRequestDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AptRequestDto> createAptRequest(CreateAptRequestForm input) {
        AptRequest aptRequest = aptRequestService.createAptRequest(input);
        AptRequestDto aptRequestDto = aptRequestMapper.toDto(aptRequest);

        return new ResponseEntity<>(aptRequestDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AptRequestDto> updateAptRequest(UpdateAptRequestForm input) {
        AptRequest aptRequest = aptRequestService.updateAptRequest(input);
        AptRequestDto aptRequestDto = aptRequestMapper.toDto(aptRequest);

        return new ResponseEntity<>(aptRequestDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AptRequestDto> approveAptRequest(Long id) {
        AptRequest aptRequest = aptRequestService.approveAptRequest(id);
        AptRequestDto aptRequestDto = aptRequestMapper.toDto(aptRequest);

        return new ResponseEntity<>(aptRequestDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AptRequestDto> denyAptRequest(Long id) {
        AptRequest aptRequest = aptRequestService.denyAptRequest(id);
        AptRequestDto aptRequestDto = aptRequestMapper.toDto(aptRequest);

        return new ResponseEntity<>(aptRequestDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> deleteAptRequest(Long id) {
        Long deletedId = aptRequestService.deleteAptRequest(id);

        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

}