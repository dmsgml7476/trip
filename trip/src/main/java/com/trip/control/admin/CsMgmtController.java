package com.trip.control.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.trip.dto.admin.CsMgmtDto;
import com.trip.constant.Member.Status;
import com.trip.repository.Member.CustomerServiceRepository;
import com.trip.service.admin.CustomerServiceAnswerService;

@Controller
@RequestMapping("/admin")
public class CsMgmtController {
	
	@Autowired
	private CustomerServiceRepository customerServiceRepository;
	
	@Autowired
	private CustomerServiceAnswerService customerServiceAnswerService;
	
	
	@GetMapping("/csMgmt")
	public String csMgmtPage(Model model) {
		
		
		List<CsMgmtDto> csList = customerServiceRepository.findAllByOrderByQuestionTimeDesc()
				.stream()
				.map(CsMgmtDto::from)
				.collect(Collectors.toList());
		model.addAttribute("csList", csList);
		
		return "admin/page/csMgmt";
	}
	
	@PostMapping("/csMgmt/answer")
	public String answerSubmit(@RequestParam("status")  String status,
								@RequestParam(value = "answerText", required = false) String answerText,
								@RequestParam("csId") Long csId) {
		if ("IN_PROGRESS".equals(status)) {
		    // 답변 없이 상태만 업데이트
			customerServiceAnswerService.updateStatus(csId, Status.IN_PROGRESS);
		} else {
		    customerServiceAnswerService.saveAnswer(csId, status, answerText);
		}
		
		return "redirect:/admin/csMgmt";
	}
	

	

}
