package cn.kbyte.utils.model;

import com.github.crab2died.annotation.ExcelField;

public class NumberDetail {
    @ExcelField(title = "手机号", order = 1)
    private String number;

    @ExcelField(title = "省", order = 2)
    private String province;


    @ExcelField(title = "市", order = 3)
    private String city;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "NumberDetail{" +
                "number='" + number + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
