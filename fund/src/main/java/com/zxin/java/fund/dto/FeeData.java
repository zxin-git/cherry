package com.zxin.java.fund.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zxin
 */
@Data
public class FeeData implements Serializable {

    private Fldata fldata;

    private List<RangeRate> sgqd;

    private List<RangeRate> sghd;

    @Data
    public static class Fldata{
        private String glf;

        private String tgf;

        private String fwf;

        private List<RangeRate> rcsh;
    }

    @Data
    public static class RangeRate {
        private String tj;
        private String shui;
    }


}
