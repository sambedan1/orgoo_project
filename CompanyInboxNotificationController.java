package com.tuespotsolutions.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.CompanyInboxNotificationDetails;
import com.tuespotsolutions.models.CandidateAcceptedNotitications;
import com.tuespotsolutions.models.CandidateRejectedNotifitations;
import com.tuespotsolutions.models.CompanyInboxNotificationAcceptedByCandidate;
import com.tuespotsolutions.models.CompanyInboxNotificationAll;
import com.tuespotsolutions.models.CompanyInboxNotificationAllWithPagination;
import com.tuespotsolutions.models.CompanyInboxPagination;
import com.tuespotsolutions.models.CompanyNotificationRejectedByCandidate;
import com.tuespotsolutions.models.CompletedInterviewList;
import com.tuespotsolutions.models.NotificationCompletedByCompany;
import com.tuespotsolutions.models.SendInviteToCandidateModel;
import com.tuespotsolutions.service.CandidateInboxNotificationService;
import com.tuespotsolutions.service.CompanyInboxNotificationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class CompanyInboxNotificationController {

	@Autowired
	private CompanyInboxNotificationService companyInboxNotificationService;

	@Autowired
	private CandidateInboxNotificationService candidateInboxNotificationService;

	@GetMapping("/by")
	public ResponseEntity<?> getCompanyInboxNotification(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxNotificationAllWithPagination notificationList = this.companyInboxNotificationService
				.getNotificationList(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxNotificationAllWithPagination>(notificationList, HttpStatus.OK);
	}

	@GetMapping("/shortlisted/by")
	public ResponseEntity<?> getShortListedCompanyInboxNotification(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxNotificationAllWithPagination notificationList = this.companyInboxNotificationService
				.getShortListCompanyInbox(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxNotificationAllWithPagination>(notificationList, HttpStatus.OK);
	}

	@GetMapping("/trashed/by")
	public ResponseEntity<?> getTrashedCompanyInboxNotification(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxNotificationAllWithPagination notificationList = this.companyInboxNotificationService
				.getTrashListCompanyInbox(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxNotificationAllWithPagination>(notificationList, HttpStatus.OK);
	}

	@GetMapping("/latest/by")
	public ResponseEntity<?> getCompanyInboxNotificationLatest(@RequestParam("companyId") Long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxNotificationAllWithPagination latestNotification = this.companyInboxNotificationService
				.getLatestNotification(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxNotificationAllWithPagination>(latestNotification, HttpStatus.OK);
	}

	@GetMapping("/detail/by")
	public ResponseEntity<?> getCompanyInboxNotificationDetail(@RequestParam("notificationId") long notificationId) {
		CompanyInboxNotificationDetails notificationDetails = this.companyInboxNotificationService
				.getNotificationDetails(notificationId);
		return new ResponseEntity<CompanyInboxNotificationDetails>(notificationDetails, HttpStatus.OK);
	}

	@PostMapping("/changestatus")
	public ResponseEntity<?> NotificationSeenStatus(@RequestBody long notificationId) {
		this.companyInboxNotificationService.setSeenStatusOnNotifiation(notificationId);
		@SuppressWarnings("unchecked")
		Map<String, String> status = new HashedMap();
		status.put("status", "Notification seen");
		return new ResponseEntity<Map<String, String>>(status, HttpStatus.OK);
	}

	@PostMapping("/shortlist")
	public ResponseEntity<?> shortListedInboxNotification(@RequestBody List<Long> notificationId) {
		List<CompanyInboxNotificationAll> shortListCompanyInbox = this.companyInboxNotificationService
				.shortListCompanyInbox(notificationId);
		return new ResponseEntity<List<CompanyInboxNotificationAll>>(shortListCompanyInbox, HttpStatus.OK);

	}

	@PostMapping("/trashlist")
	public ResponseEntity<?> trashInboxNotification(@RequestBody List<Long> notificationId) {
		List<CompanyInboxNotificationAll> trashListCompanyInbox = this.companyInboxNotificationService
				.trashListCompanyInbox(notificationId);
		return new ResponseEntity<List<CompanyInboxNotificationAll>>(trashListCompanyInbox, HttpStatus.OK);

	}

	@GetMapping("/shortlisted")
	public ResponseEntity<?> getShortListedCompanyInboxNotificationbyPagination(
			@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxPagination allComapanyNotifications = this.companyInboxNotificationService
				.getAllComapanyNotifications(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxPagination>(allComapanyNotifications, HttpStatus.OK);
	}

	@GetMapping("/rejectByCandidate/by")
	public ResponseEntity<?> getNotificationsRejectedByCandidate(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyNotificationRejectedByCandidate notificationByRejectedByCandidate = this.companyInboxNotificationService
				.getNotificationByRejectedByCandidate(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyNotificationRejectedByCandidate>(notificationByRejectedByCandidate,
				HttpStatus.OK);
	}

	@GetMapping("/acceptedByCandidate/by")
	public ResponseEntity<?> getNotifiactionAcceptedByCandidate(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxNotificationAcceptedByCandidate notificationByAcceptedByCandidate = this.companyInboxNotificationService
				.getNotificationByAcceptedByCandidate(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxNotificationAcceptedByCandidate>(notificationByAcceptedByCandidate,
				HttpStatus.OK);
	}

	@PutMapping("/mark/completed/by")
	public ResponseEntity<?> markCompletedInterviewNotificationByCompany(@RequestParam("companyId") long companyId) {
		Map<String, String> markInterviewNotitfationCompleted = this.companyInboxNotificationService
				.markNotifitationCompletedByCompany(companyId);
		return new ResponseEntity<Map<String, String>>(markInterviewNotitfationCompleted, HttpStatus.OK);
	}

	@GetMapping("/completed/notification/list/by")
	public ResponseEntity<?> getNotifiactionCompletedByCandidate(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		NotificationCompletedByCompany completedNotificationByCompany = this.companyInboxNotificationService
				.getCompletedNotificationByCompany(companyId, pageNumber, pageSize);
		return new ResponseEntity<NotificationCompletedByCompany>(completedNotificationByCompany, HttpStatus.OK);
	}

	@GetMapping("/notification/of/company/by")
	public ResponseEntity<?> getAllNotiticationWithCurrentStatus(@RequestParam("companyId") long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyInboxNotificationAllWithPagination notificationList = this.companyInboxNotificationService
				.getNotificationListWithCurrentStatus(companyId, pageNumber, pageSize);
		return new ResponseEntity<CompanyInboxNotificationAllWithPagination>(notificationList, HttpStatus.OK);
	}

	@GetMapping("/notification/of/company/detail/by")
	public ResponseEntity<?> getCompanyInboxNotificationDetailWithTimelineLogs(
			@RequestParam("notificationId") long notificationId) {
		CompanyInboxNotificationDetails notificationDetails = this.companyInboxNotificationService
				.getNotificationDetailsWithTimeLineLogs(notificationId);
		return new ResponseEntity<CompanyInboxNotificationDetails>(notificationDetails, HttpStatus.OK);
	}

	@PutMapping("/approve/notification/by")
	public ResponseEntity<?> approveNotiticationByCompany(@RequestParam("notificationId") long notificationId) {
		Map<String, String> approveNotiticationByCompany = this.companyInboxNotificationService
				.approveNotitication(notificationId);
		return new ResponseEntity<Map<String, String>>(approveNotiticationByCompany, HttpStatus.OK);
	}

	@PutMapping("/reject/notification/by")
	public ResponseEntity<?> rejectNotiticationByCompany(@RequestParam("notificationId") long notificationId) {
		Map<String, String> rejectNotiticationByCompany = this.companyInboxNotificationService
				.rejectNotitication(notificationId);
		return new ResponseEntity<Map<String, String>>(rejectNotiticationByCompany, HttpStatus.OK);
	}

	@PutMapping("/completed/notification/by")
	public ResponseEntity<?> completedNotiticationByCompany(@RequestParam("notificationId") long notificationId) {
		Map<String, String> completedNotiticationByCompany = this.companyInboxNotificationService
				.completedNotitication(notificationId);
		return new ResponseEntity<Map<String, String>>(completedNotiticationByCompany, HttpStatus.OK);
	}

	@PutMapping("/send/invitation/to/candiate")
	public ResponseEntity<?> sendInterviewInvite(@RequestBody SendInviteToCandidateModel request) {
		Map<String, String> sentInviteByCompany = this.candidateInboxNotificationService.sentInviteByCompany(request);
		return new ResponseEntity<Map<String, String>>(sentInviteByCompany, HttpStatus.OK);
	}
}
