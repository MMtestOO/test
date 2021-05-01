package com.proto.mm.model;

import lombok.Data;

@Data
public class Amount {
	 
    private Integer total, tax_free, vat, point, discount;
}