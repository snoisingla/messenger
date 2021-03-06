package com.spring.boot.messenger.application.springbootmessengerapplication.otp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {
	
	@Autowired
	private OtpServiceImpl otpService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "otps/request")
	public void sendOTP(@RequestBody Otps otpDetails) {
		otpService.generateSaveAndSendOTP(otpDetails.getContactNumber());
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "otps/{contactNumber}")
	public Integer getOTP(@PathVariable String contactNumber) {
		return otpService.getUserOtpDetails(contactNumber).getOtp();
	}
	
	@PostMapping(path = "otps/verify")
	public VerifyOtpResponse verifyOtp(@RequestBody Otps otpDetails) {
		return otpService.verifyOTP(otpDetails.getContactNumber(), otpDetails.getOtp());
	}
	
	@GetMapping(path = "otps")
	public List<Otps> getAllOtps(){ 
		return otpService.retreiveAll();
	}
}
