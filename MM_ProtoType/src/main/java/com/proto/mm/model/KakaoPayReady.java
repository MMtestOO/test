package com.proto.mm.model;

import java.util.Date;

import lombok.Data;
 
@Data
public class KakaoPayReady {
    
    //response
    private String tid, next_redirect_pc_url;
    private Date created_at;
    
}