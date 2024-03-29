package com.example.handyman.controller;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.handyman.dto.RequestHandymanDto;
import com.example.handyman.dto.JobRequestDto;
import com.example.handyman.dto.JobStatusDto;
import com.example.handyman.dto.LoginDto;
import com.example.handyman.dto.ProposalDto;
import com.example.handyman.dto.RatingDto;
import com.example.handyman.dto.SignUpDto;
import com.example.handyman.email.EmailService;
import com.example.handyman.repository.*;
import com.example.handyman.model.*;


@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private HandyManRepository handymanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
	private EmailService senderService;
    
    @Autowired
    private HandyManServiceRequestRepository HandyManSRRepository;
    
    @Autowired
    private ProposalRepository proposalRepository;
    
    @Autowired
    private JobRequestRepository jobRepository;

    @Autowired
    private JobStatusRepository jobStatusRepository;
    
    @Autowired
    private RatingRepository ratingRepository;
    
	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestBody LoginDto loginDto) {

		try {
			System.out.println("LoginDto Details: " + loginDto);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
					loginDto.getPassword());

			System.out.println("\nAuthentication Token Before Authentication: " + token);

			Authentication authResult = authenticationManager.authenticate(token);

			System.out.println("Hello");
			System.out.println("Authentication Token After Authentication: " + authResult);
			System.out.println();

			System.out.println("Authentication Token in Security Context: "
					+ SecurityContextHolder.getContext().getAuthentication());

			System.out.println();
			if (authResult.isAuthenticated())
				System.out.println("User is Authenticated");

			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Async
	public void sendEmail(String emailId, String emailSubject, String emailBody) throws MessagingException {
		senderService.sendSimpleEmail(emailId, emailBody, emailSubject);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
		try {
			System.out.println("SignUpDTO:" + signUpDto.getEmail());
			// checking for email exists in a database

			if (signUpDto.getRole().equalsIgnoreCase("handyman")) {
				Handyman user = new Handyman();
				user.setFirstName(signUpDto.getFirstName());
				user.setLastName(signUpDto.getLastName());

				user.setEmail(signUpDto.getEmail());
				user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
				String role = signUpDto.getRole();
				user.setRole(role);
				System.out.println("User: " + user.getEmail());
				handymanRepository.save(user);
				sendEmail(signUpDto.getEmail(), "Hey, " + signUpDto.getFirstName() + ",\nYour signup at Handyman is successful!", "Handyman SignUp Success");
				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}

			else if (signUpDto.getRole().equalsIgnoreCase("customer")) {
				
				// creating user object
				Customer user = new Customer();
				user.setFirstName(signUpDto.getFirstName());
				user.setLastName(signUpDto.getLastName());

				user.setEmail(signUpDto.getEmail());
				user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
				String role = signUpDto.getRole();
				user.setRole(role);
				System.out.println("User: " + user.getEmail());
				customerRepository.save(user);
				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {
			System.out.println("ERROR IN SIGNUP");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	//////
	@PostMapping("/jobRequest")
	public ResponseEntity<?> requestHandyman(@RequestBody RequestHandymanDto requestHmanDto) {

		try {
			System.out.println("FindHmandto:" + requestHmanDto.getJobDescription());
			// checking for email exists in a response body
			
			HandyManServiceRequest hManService= new HandyManServiceRequest();
			
			hManService.setJobDateTime(requestHmanDto.getJobDateTime());
			hManService.setTrade(requestHmanDto.getTrade());
			hManService.setJobDescription(requestHmanDto.getJobDescription());
			hManService.setJobDuration(requestHmanDto.getJobDuration());
			hManService.setImage( requestHmanDto.getImage() );
			HandyManSRRepository.save(hManService);
			return new ResponseEntity<>(hManService, HttpStatus.CREATED);

		}catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/jobRequest/{id}")
	public ResponseEntity<?> getHandyman(@PathVariable Integer id) {
	    try {
	        Optional<HandyManServiceRequest> hManService = HandyManSRRepository.findById(id);

	        if (hManService.isPresent()) {
	            return new ResponseEntity<>(hManService.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        System.out.println("Exception " + e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@DeleteMapping("/jobRequest/{id}")
	public ResponseEntity<?> deleteHandyman(@PathVariable Integer id) {
	    try {
	        HandyManSRRepository.deleteById(id);
	        return new ResponseEntity<>("Handyman service with id " + id + " deleted successfully.", HttpStatus.OK);

	    } catch (Exception e) {
	        System.out.println("Exception " + e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PatchMapping("/jobRequest/{id}")
	public ResponseEntity<?> updateHandyman(@PathVariable Integer id, @RequestBody RequestHandymanDto updatedDto) {
	    try {
	        Optional<HandyManServiceRequest> optionalHManService = HandyManSRRepository.findById(id);

	        if (optionalHManService.isPresent()) {
	            HandyManServiceRequest hManService = optionalHManService.get();
	            // Update fields based on the updatedDto
	            hManService.setJobDateTime(updatedDto.getJobDateTime());
	            hManService.setTrade(updatedDto.getTrade());
	            hManService.setJobDescription(updatedDto.getJobDescription());
	            hManService.setJobDuration(updatedDto.getJobDuration());
	            hManService.setImage(updatedDto.getImage());

	            HandyManSRRepository.save(hManService);
	            return new ResponseEntity<>(hManService, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        System.out.println("Exception " + e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	 @PostMapping("/createProposal")
	    public ResponseEntity<?> createProposal(@RequestBody ProposalDto proposalDto) {
	        try {
	            Proposal proposal = new Proposal(
	                    proposalDto.getJobRequestId(),
	                    proposalDto.getTradesmanName(),
	                    proposalDto.getProposalDescription(),
	                    proposalDto.getProposedHours(),
	                    proposalDto.getProposalDate()
	            );

	            Proposal savedProposal = proposalRepository.save(proposal);

	            return new ResponseEntity<>(savedProposal, HttpStatus.CREATED);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 // GET Mapping to retrieve a proposal by ID
	    @GetMapping("/createProposal/{proposalId}")
	    public ResponseEntity<?> getProposalById(@PathVariable Integer proposalId) {
	        try {
	            Optional<Proposal> proposal = proposalRepository.findById(proposalId);

	            if (proposal != null) {
	         
	                return new ResponseEntity<>(proposal.get(), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Proposal not found", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // DELETE Mapping to delete a proposal by ID
	    @DeleteMapping("/createProposal/{proposalId}")
	    public ResponseEntity<?> deleteProposal(@PathVariable Integer proposalId) {
	        try {
	        	proposalRepository.deleteById(proposalId);
	            return new ResponseEntity<>("Proposal deleted successfully", HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // PATCH Mapping to update a proposal by ID
	    @PatchMapping("/createProposal/{proposalId}")
	    public ResponseEntity<?> updateProposal(@PathVariable Integer proposalId, @RequestBody ProposalDto updatedProposalDto) {
	        try {
	            Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);

	            if (optionalProposal.isPresent()) {
	                Proposal existingProposal = optionalProposal.get();

	                existingProposal.setTradesmanName(updatedProposalDto.getTradesmanName());
	                existingProposal.setProposalDescription(updatedProposalDto.getProposalDescription());
	                existingProposal.setProposedHours(updatedProposalDto.getProposedHours());
	                existingProposal.setProposalDate(updatedProposalDto.getProposalDate());

	                Proposal updatedProposal = proposalRepository.save(existingProposal);

	                return new ResponseEntity<>(updatedProposal, HttpStatus.OK);
	            }else {
		            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		        }
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PostMapping("/traderJob")
	    public ResponseEntity<?> createJob(@RequestBody JobRequestDto job) {
	        try {
	        	JobRequest jobRequest = new JobRequest(
	        			job.getJobId(),
	        			job.getJobDuration(),
	        			job.getDescription(),
	        			job.getCost(),
	        			job.getTraderId(),
	        			job.getCustomerId()
	            );	
	        	jobRepository.save(jobRequest);
	        	return new ResponseEntity<>(jobRequest, HttpStatus.CREATED);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @GetMapping("/traderJob/{jobRequestId}")
	    public ResponseEntity<?> getJobRequestById(@PathVariable Integer jobRequestId) {
	        try {
	            Optional<JobRequest> JobRequest = jobRepository.findById(jobRequestId);
	            if (JobRequest != null) {
	   	         
	                return new ResponseEntity<>(JobRequest.get(), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Job Request not found", HttpStatus.NOT_FOUND);
	            }
	            

	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/traderJob/{jobRequestId}")
	    public ResponseEntity<?> deleteJobRequest(@PathVariable Integer jobRequestId) {
	        try {
	        	jobRepository.deleteById(jobRequestId);
	            return new ResponseEntity<>("JobRequest deleted successfully", HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PatchMapping("/traderJob/{jobRequestId}")
	    public ResponseEntity<?> updateJobRequest(@PathVariable Integer jobRequestId, @RequestBody JobRequestDto updatedFields) {
	        try {
	            Optional<JobRequest> optionalJobRequest = jobRepository.findById(jobRequestId);

	            if (optionalJobRequest.isPresent()) {
	                JobRequest existingJobRequest = optionalJobRequest.get();

	                existingJobRequest.setJobId(updatedFields.getJobId());
	                existingJobRequest.setJobDuration(updatedFields.getJobDuration());
	                existingJobRequest.setDescription(updatedFields.getDescription());
	                existingJobRequest.setCost(updatedFields.getCost());
	                existingJobRequest.setTraderId(updatedFields.getTraderId());
	                existingJobRequest.setCustomerId(updatedFields.getCustomerId());
	                JobRequest updatedJobRequest = jobRepository.save(existingJobRequest);

	                return new ResponseEntity<>(updatedJobRequest, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("JobRequest not found", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PostMapping("/jobStatus")
	    public ResponseEntity<?> createJobStatus(@RequestBody JobStatusDto jobStatusDto) {
	        try {
	            JobStatus jobStatus = new JobStatus(
	                    jobStatusDto.getTraderId(),
	                    jobStatusDto.getJobRequestId(),
	                    jobStatusDto.getStatus(),
	                    jobStatusDto.getCustomerId()
	            );

	            JobStatus createdJobStatus = jobStatusRepository.save(jobStatus);
	            return new ResponseEntity<>(createdJobStatus, HttpStatus.CREATED);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @GetMapping("/jobStatus/{id}")
	    public ResponseEntity<?> getJobStatusById(@PathVariable int id) {
	        try {
	            Optional<JobStatus> optionalJobStatus = jobStatusRepository.findById(id);

	            if (optionalJobStatus != null) {
		   	         
	                return new ResponseEntity<>(optionalJobStatus.get(), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Job Status not found", HttpStatus.NOT_FOUND);
	            }
	            
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/jobStatus/{id}")
	    public ResponseEntity<?> deleteJobStatus(@PathVariable int id) {
	        try {
	            jobStatusRepository.deleteById(id);
	            return new ResponseEntity<>("JobStatus deleted successfully", HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PatchMapping("/jobStatus/{id}")
	    public ResponseEntity<?> updateJobStatus(@PathVariable int id, @RequestBody JobStatusDto updatedFields) {
	        try {
	            Optional<JobStatus> optionalJobStatus = jobStatusRepository.findById(id);

	            if (optionalJobStatus.isPresent()) {
	                JobStatus existingJobStatus = optionalJobStatus.get();

	                // Update fields based on the updatedFields
	                existingJobStatus.setTraderId(updatedFields.getTraderId());
	                existingJobStatus.setJobRequestId(updatedFields.getJobRequestId());
	                existingJobStatus.setStatus(updatedFields.getStatus());
	                existingJobStatus.setCustomerId(updatedFields.getCustomerId());

	                JobStatus updatedJobStatus = jobStatusRepository.save(existingJobStatus);

	                return new ResponseEntity<>(updatedJobStatus, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("JobStatus not found", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PostMapping("/rate")
	    public ResponseEntity<?> createRating(@RequestBody RatingDto ratingDto) {
	        try {
	            Rating rating = new Rating(
	                    ratingDto.getTraderId(),
	                    ratingDto.getJobRequestId(),
	                    ratingDto.getCustomerId(),
	                    ratingDto.getRating(),
	                    ratingDto.getRatingFor()
	            );

	            Rating createdRating = ratingRepository.save(rating);
	            return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @GetMapping("/rate/{id}")
	    public ResponseEntity<?> getRatingById(@PathVariable int id) {
	        try {
	            Optional<Rating> optionalRating = ratingRepository.findById(id);

	            if (optionalRating != null) {
		   	         
	                return new ResponseEntity<>(optionalRating.get(), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Rating not found", HttpStatus.NOT_FOUND);
	            }

	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PatchMapping("/rate/{id}")
	    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestBody RatingDto updatedFields) {
	        try {
	            Optional<Rating> optionalRating = ratingRepository.findById(id);

	            if (optionalRating.isPresent()) {
	                Rating existingRating = optionalRating.get();

	                // Update fields based on the updatedFields
	                existingRating.setTraderId(updatedFields.getTraderId());
	                existingRating.setJobRequestId(updatedFields.getJobRequestId());
	                existingRating.setCustomerId(updatedFields.getCustomerId());
	                existingRating.setRating(updatedFields.getRating());
	                existingRating.setRatingFor(updatedFields.getRatingFor());

	                Rating updatedRating = ratingRepository.save(existingRating);

	                return new ResponseEntity<>(updatedRating, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Rating not found", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/rate/{id}")
	    public ResponseEntity<?> deleteRating(@PathVariable int id) {
	        try {
	            ratingRepository.deleteById(id);
	            return new ResponseEntity<>("Rating deleted successfully", HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
		        System.out.println("Exception " + e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}