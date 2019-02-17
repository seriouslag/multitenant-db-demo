package com.nullspace.multitenant.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nullspace.multitenant.demo.support.Cuid;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model {
	
	@Id
	@Column(columnDefinition = "text")
	private String id;
	
	@Column(nullable = false)
	@JsonFormat(shape = STRING)
	private Instant createdAt;
	
	@Column(nullable = false, columnDefinition = "text")
	private String tenant;
	
	public Model(String tenant) {
		this.tenant = tenant;
	}
	
	@PrePersist
	protected void prePersist() {
		id = Cuid.createCuid();
		createdAt = Instant.now();
	}
}
