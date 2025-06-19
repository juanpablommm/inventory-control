package com.jdn.varieties.inventory_control.users.infrastructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String token;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX", timezone = "America/Bogota")
	@Column(nullable = false, name = "expiry_time")
	private OffsetDateTime expiryTime;

	@OneToOne()
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
