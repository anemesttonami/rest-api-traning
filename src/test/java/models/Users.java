package models;

import lombok.Data;

import java.util.List;

@Data
public class Users {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<ResponseData> data;
    private ResponseSupport support;

}
