package backend.controller;

import backend.dto.AptRequestDto;
import backend.form.AptRequestForm.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/apt-request")
public interface AptRequestController {

	@GetMapping("/")
	ResponseEntity<List<AptRequestDto>> getAllAptRequests();

	@GetMapping("/{id}")
	ResponseEntity<AptRequestDto> getAptRequestById(@PathVariable Long id);

	@GetMapping("/from/{fromId}")
	ResponseEntity<List<AptRequestDto>> getAptRequestByFromId(@PathVariable Long fromId);

	@GetMapping("/to/{toId}")
	ResponseEntity<List<AptRequestDto>> getAptRequestByToId(
			@PathVariable Long toId,
			@RequestParam @Pattern(regexp = "PENDING|APPROVED|DENIED") String status
	);

	@PostMapping("/create")
	ResponseEntity<AptRequestDto> createAptRequest(@RequestBody @Valid CreateAptRequestForm input);

	@PostMapping("/update")
	ResponseEntity<AptRequestDto> updateAptRequest(@RequestBody @Valid UpdateAptRequestForm input);

	@GetMapping("/approve/{id}")
	ResponseEntity<AptRequestDto> approveAptRequest(@PathVariable Long id);

	@GetMapping("/deny/{id}")
	ResponseEntity<AptRequestDto> denyAptRequest(@PathVariable Long id);

	@GetMapping("/delete/{id}")
	ResponseEntity<Long> deleteAptRequest(@PathVariable Long id);

}