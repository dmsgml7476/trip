package com.trip.control.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trip.dto.admin.CsAnswerDto;
import com.trip.dto.admin.CsMgmtDto;
import com.trip.entity.Member.CustomerServiceEntity;
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
	@ResponseBody
	public ResponseEntity<?> saveAnswer(@RequestBody CsAnswerDto dto) {
	    try {
	        customerServiceAnswerService.saveAnswer(dto); // 별도의 서비스 계층에서 처리
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패");
	    }
	}
	

}
