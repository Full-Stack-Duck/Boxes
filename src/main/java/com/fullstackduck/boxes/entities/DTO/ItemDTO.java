package com.fullstackduck.boxes.entities.DTO;

import lombok.Getter;
import lombok.Setter;

public class ItemDTO{
	@Getter @Setter private Long produtoId;
	@Getter @Setter private Integer quantidade;
	@Getter @Setter private Double desconto;
}
