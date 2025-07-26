package com.oracle.kyc.controller;

import com.oracle.kyc.dto.KycDTO;
import java.util.Base64;

import com.oracle.kyc.service.KycService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kycservice/api")
public class KycController {

	@Autowired
	private KycService kycService;

	@Operation(summary = "Submit KYC for a customer", description = "Saves PAN, Aadhaar, Photograph, and sets initial status as PENDING")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "KYC submitted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input") })
	@PostMapping("/kyc")
	public ResponseEntity<KycDTO> submitKyc(@RequestBody KycDTO kycDTO) {
		KycDTO saved = kycService.saveKyc(kycDTO);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@Operation(summary = "Get KYC by Customer ID", description = "Returns masked KYC details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "KYC found"),
			@ApiResponse(responseCode = "404", description = "KYC not found") })
	@GetMapping("/kyc/{customerId}")
	public ResponseEntity<KycDTO> getKyc(@PathVariable String customerId) {
		return ResponseEntity.ok(kycService.getKycByCustomerId(customerId));
	}

	@Operation(summary = "Fetch all KYC entries", description = "Returns list of all KYC records with masked data")
	@ApiResponse(responseCode = "200", description = "List fetched")
	@GetMapping("/kyc")
	public ResponseEntity<List<KycDTO>> getAllKycs() {
		return ResponseEntity.ok(kycService.getAllKycs());
	}

	@Operation(summary = "Update KYC by Customer ID", description = "Updates KYC info")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Updated successfully"),
			@ApiResponse(responseCode = "404", description = "KYC not found") })
	@PutMapping("/kyc/{customerId}")
	public ResponseEntity<KycDTO> updateKyc(@PathVariable String customerId, @RequestBody KycDTO kycDTO) {
		return ResponseEntity.ok(kycService.updateKyc(customerId, kycDTO));
	}

	@Operation(summary = "Delete KYC by Customer ID", description = "Removes KYC entry")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Deleted successfully"),
			@ApiResponse(responseCode = "404", description = "KYC not found") })
	@DeleteMapping("/kyc/{customerId}")
	public ResponseEntity<Void> deleteKyc(@PathVariable String customerId) {
		kycService.deleteKyc(customerId);
		return ResponseEntity.noContent().build();
	}

	// Adding review api for admin
	@Operation(summary = "Admin review of KYC", description = "Update KYC status (VERIFIED/REJECTED) with optional admin remark")
	@PutMapping("/kyc/{customerId}/review")
	public ResponseEntity<String> reviewKyc(@PathVariable String customerId, @RequestBody Map<String, String> body) {
		String status = body.get("kycStatus");
		String remark = body.get("adminRemark"); // Can be null
		kycService.reviewKyc(customerId, status, remark);
		return ResponseEntity.ok("KYC status updated to " + status);
	}

	// GET API for customer status
	@Operation(summary = "Get KYC status", description = "Returns the KYC status and admin remark for the customer")
	@GetMapping("/kyc/{customerId}/status")
	public ResponseEntity<Map<String, String>> getStatus(@PathVariable String customerId) {
		KycDTO dto = kycService.getKycByCustomerId(customerId);

		Map<String, String> response = new HashMap<>();
		response.put("kycStatus", dto.getKycStatus()); // actual stored value
		response.put("adminRemark", dto.getAdminRemark() != null ? dto.getAdminRemark() : "No remark");

		return ResponseEntity.ok(response);
	}

	// Allowing admin to view the photograph directly in the browser

@Operation(summary = "View photograph", description = "Returns the KYC photograph as an image")
@GetMapping(value = "/kyc/{customerId}/photo/view", produces = MediaType.IMAGE_JPEG_VALUE)
public ResponseEntity<byte[]> viewPhotograph(@PathVariable String customerId) {
    KycDTO dto = kycService.getKycByCustomerId(customerId);
    
    if (dto.getPhotograph() == null || dto.getPhotograph().isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    byte[] imageBytes = Base64.getDecoder().decode(dto.getPhotograph());
    return ResponseEntity.ok(imageBytes);
}

}
