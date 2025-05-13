package com.trip.service.admin;

import java.time.LocalDateTime;

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
    public void saveAnswer(Long csId, String status, String answerText) {
        // 1. 문의 엔티티 조회
        CustomerServiceEntity cs = customerServiceRepository.findById(csId)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객 문의가 존재하지 않습니다."));

        // 2. 상태 업데이트
        cs.setStatus(Status.valueOf(status)); // Enum 변환

        // 3. 답변 엔티티 생성 및 저장
        CustomerServiceAnswerEntity existingAnswer = answerRepository.findByCustomerService(cs);

        if (existingAnswer != null) {
            // 이미 있는 답변이라면 수정
            existingAnswer.setAnswerText(answerText);
            existingAnswer.setAnswerTime(LocalDateTime.now());
        } else {
            // 없으면 새로 저장
            CustomerServiceAnswerEntity answer = new CustomerServiceAnswerEntity();
            answer.setCustomerService(cs);
            answer.setAnswerText(answerText);
            answer.setAnswerTime(LocalDateTime.now());
            answerRepository.save(answer);
        }


    }
    
    @Transactional
    public void updateStatus(Long csId, Status status) {
    	CustomerServiceEntity cs = customerServiceRepository.findById(csId)
    			.orElseThrow(() -> new IllegalArgumentException("고객 문의가 존재하지 않습니다."));
    
    	cs.setStatus(status);
    }
}