package com.trip.entity.Member;

import lombok.*;

import java.time.LocalDateTime;

import com.trip.constant.Member.CustomerServiceCategory;
import com.trip.constant.Member.CsOption;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="customer_service")
public class CustomerServiceEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_service_id")
	private Long id;
	
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="cs_option", nullable=false)
	private CsOption csoOtion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="category", nullable=false)
	private CustomerServiceCategory category;
	
	@Column(name="question_text", nullable=false, length=255)
	private String questionText;
	
	@Column(name="question_time", nullable=false, updatable = false)
	private LocalDateTime questionTime;
	
	@OneToOne(mappedBy = "customerService", cascade = CascadeType.ALL, orphanRemoval = true)
	private CustomerServiceAnswerEntity answer;
}
