package entity;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class Document implements Serializable {
    private String id;
    private String chnldesc;
    private String city;
    private String docchannel;
    private String page_time;
    private String industry;
    private String province;
    private Date publishtime;
    private String qcodes;
    private String web_source_no;
    private String web_source_name;
    private String doctitle;
    private String doccontent;
    private String district;
    private String tenderee;
    private String win_tenderer;
    private String agency;
    private String win_bid_price;
    private String bidding_budget;
}
