package com.trip.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.constant.Member.Status;
import com.trip.dto.admin.CsAnswerDto;
import com.trip.entity.Member.CustomerServiceAnswerEntity;
import com.trip.entity.Member.CustomerServiceEntity;
import com.trip.repository.Member.CustomerServiceAnswerRepository;
import com.trip.repository.Member.CustomerServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceAnswerService {

    private final CustomerServiceRepository customerServiceRepository;
    private final CustomerServiceAnswerRepository answerRepository;

    @Transactional
    public void saveAnswer(CsAnswerDto dto) {
        CustomerServiceEntity service = customerServiceRepository.findById(dto.getCustomerServiceId())
                .orElseThrow(() -> new IllegalArgumentException("고객 문의 없음"));

        // 답변 엔티티 생성 및 저장
        CustomerServiceAnswerEntity answer = new CustomerServiceAnswerEntity();
        answer.setAnswerText(dto.getAnswerText());
        answer.setCustomerService(service); 
        answerRepository.save(answer);
        
        if (dto.getStatus().equals("IN_PROGRESS")) {
            service.setStatus(Status.IN_PROGRESS);
            return;
        }

        // 상태 업데이트
        service.setStatus(Status.valueOf(dto.getStatus()));
    }
}