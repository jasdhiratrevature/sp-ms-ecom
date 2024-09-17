package com.revature.ms.product.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Product {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		 private String name;
		    private String description;
		    private BigDecimal price;
		public Product(String name, String description, BigDecimal price) {
			super();
			this.name = name;
			this.description = description;
			this.price = price;
		}
	    
}

