package com.trip.entity.Member;

import lombok.*;

import java.time.LocalDateTime;

import com.trip.constant.Member.CustomerServiceCategory;
import com.trip.constant.Member.Option;

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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private UserEntity user;
	
	@Enumerated(EnumType.STRING)
	@Column(name="option", nullable=false)
	private Option option;
	
	@Enumerated(EnumType.STRING)
	@Column(name="category", nullable=false)
	private CustomerServiceCategory category;
	
	@Column(name="question_text", nullable=false, length=255)
	private String questionText;
	
	@Column(name="question_time", nullable=false, updatable = false)
	private LocalDateTime questionTime = LocalDateTime.now();
	
	@OneToOne(mappedBy = "customerService", cascade = CascadeType.ALL, orphanRemoval = true)
	private CustomerServiceAnswerEntity answer;
}
