package com.interview.Currencies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NBPResponse {
    private String table;
    private String no;
    private Date effectiveDate;
    private List<Rate> rates;
}
