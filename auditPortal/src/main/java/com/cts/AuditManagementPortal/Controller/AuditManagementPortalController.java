package com.cts.AuditManagementPortal.Controller;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.cts.AuditManagementPortal.Exception.CheckListException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.cts.AuditManagementPortal.Model.UserDetails;
import com.cts.AuditManagementPortal.Util.AuditPortalConstant;
import com.cts.AuditManagementPortal.Exception.CheckListException;
import com.cts.AuditManagementPortal.Model.AuditDetail;
import com.cts.AuditManagementPortal.Model.AuditRequest;
import com.cts.AuditManagementPortal.Model.AuditResponse;
import com.cts.AuditManagementPortal.Model.QuestionResponse;
import com.cts.AuditManagementPortal.Model.UserCredentials;

@Controller
public class AuditManagementPortalController {

	Logger log = LoggerFactory.getLogger(AuditManagementPortalController.class);
	@Autowired
	private RestTemplate restTemp;

	@Autowired
	private AuditRequest auditRequest;

	@Autowired
	private AuditDetail auditDetail;

	@Value("${TokenService.url}")
	private String authenticateUrl;

	@Value("${ChecklistService.url}")
	private String checkListUrl;

	@Value("${SeverityService.url}")
	private String SeverityUrl;

	private UserDetails userDetails;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		log.info("START");
		return "login";
	}

	@GetMapping("/home")
	public String homePage(HttpSession session, ModelMap model) {
		log.info("Home Page URL");
		String token = (String) session.getAttribute("token");

		return "auditDetailsForm";

	}

	/*
	 * This Method takes user credentials from form, validate it using Authorization
	 * Service and generates a Token
	 */

	@PostMapping(value = "/validateCredentials", consumes = "application/x-www-form-urlencoded")
	public String userCredentialsValidation(UserCredentials user, ModelMap model, HttpServletRequest request)
			throws Exception {

		log.info("User Credent");
		ResponseEntity<String> response = null;

		try {

			response = restTemp.postForEntity(authenticateUrl, user, String.class);
			log.info("token{}:", response.getBody());
			log.info("token generated successfully");

		} catch (Exception e) {
			e.printStackTrace();

			log.error("Invalid credentials");
			model.addAttribute("message",AuditPortalConstant.USER_LOGIN_ERROR);
			return "login";
		}
		String token = response.getBody();
		log.info("Token = ", token);
		System.out.println(token);
		log.info("Session attribute set");
		request.getSession().setAttribute("token", "Bearer " + token);
		// request.getSession().setAttribute("token", token);
		return "auditDetailsForm";

	}

	/*
	 * This method takes input from User and call Checklist service to display
	 * Questions according to the AuditType selected by user
	 */
	@PostMapping(value = "/auditQues", consumes = "application/x-www-form-urlencoded")
	public String showAuditQuestions(UserDetails userDetails, ModelMap model, HttpSession session)
			throws CheckListException {

		this.userDetails = userDetails;
		log.info("UserDetails/n/n");
		log.info(userDetails.toString());
		HttpHeaders headers = new HttpHeaders();

		String token = (String) session.getAttribute("token");
		headers.set("Authorization", token);
		HttpEntity<Boolean> requestEntity = new HttpEntity<>(null, headers);

		try {

			ResponseEntity<List<String>> response = restTemp.exchange(checkListUrl + userDetails.getAuditType(),
					HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<String>>() {
					});

			List<String> questionList = (List<String>) response.getBody();

			int count = 1;
			Map<Integer, String> map = new LinkedHashMap<>();
			for (String str : questionList) {
				map.put(count++, str);
			}
			System.out.println(userDetails);
			model.addAttribute("message", map);
			return "auditQuestion";

		} catch (Exception e) {
			log.error("Error occured " + e);
			model.addAttribute("message", AuditPortalConstant.PORTAL_SERVICE_ERROR);
			return "login";
		}
	}

	/*
	 * This method takes response of questions answered call Severity Service and
	 * displays response
	 */

	@PostMapping(value = "/auditQuestionsRes", consumes = "application/x-www-form-urlencoded")
	public String getResponse(@ModelAttribute QuestionResponse Questionresponse, ModelMap model, HttpSession session) {

		String token = (String) session.getAttribute("token");
		log.info("Question Response Received");
		int noOfNo = 0;

		ResponseEntity<?> response = null;
		List<String> auditQuestionsResponse = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);

		auditQuestionsResponse.add(Questionresponse.getRadio_1());
		auditQuestionsResponse.add(Questionresponse.getRadio_2());
		auditQuestionsResponse.add(Questionresponse.getRadio_3());
		auditQuestionsResponse.add(Questionresponse.getRadio_4());
		auditQuestionsResponse.add(Questionresponse.getRadio_5());

		auditRequest.setApplicationOwnerName(userDetails.getAoname());
		auditRequest.setProjectManagerName(userDetails.getPmname());
		auditRequest.setProjectName(userDetails.getPname());
		this.auditDetail.setAuditType(userDetails.getAuditType());
		this.auditDetail.setAuditDate(new java.util.Date());
		for (String respList : auditQuestionsResponse) {
			if (respList.equalsIgnoreCase("no")) {
				noOfNo++;
			}
		}
		this.auditDetail.setAuditQuestions(noOfNo);
		this.auditRequest.setAuditDetail(auditDetail);
		HttpStatus statusCode = null;
		HttpEntity<AuditRequest> requestEntity = new HttpEntity<>(auditRequest, headers);
		try {

			response = restTemp.exchange(SeverityUrl, HttpMethod.POST, requestEntity, AuditResponse.class);
			statusCode = response.getStatusCode();
			if (statusCode == HttpStatus.OK) {
				log.info("AuditResponse Received");
				AuditResponse auditResponse = (AuditResponse) response.getBody();
				model.addAttribute("response", auditResponse);
				model.addAttribute("response2", auditRequest);
				return "auditResponse";
			} else {

				model.addAttribute("message", response.getBody());
				model.addAttribute("errorCode", response.getStatusCodeValue());
				log.info("Internal Server Error " + response.getBody());
				return "login";
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", AuditPortalConstant.PORTAL_SERVICE_ERROR);
			log.error("Exception occured  " + e);
			return "login";
		}

	}

	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request, ModelMap model) {
		log.info("Logging user out");
		request.getSession().invalidate();
		log.info("END");

		model.addAttribute("logout", AuditPortalConstant.USER_LOGOUT);
		return "login";
	}

}
