package cn.kbyte.utils.phone;

import cn.kbyte.utils.model.NumberDetail;
import cn.kbyte.utils.model.Try;
import com.github.crab2died.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author vector
 * @date: 2019/2/20 0020 14:29
 */
public class NumberExcel2DetailExcel {

    public static PhoneNumberGeo phoneNumberGeo;

    @BeforeClass
    public static void initPhoneNumber() {
        phoneNumberGeo = new PhoneNumberGeo();
    }

    @Test
    public void buildDetailNumber() {
        String path = "C:\\Users\\bd2\\Desktop\\电话归属地分类\\电话归属地分类.xls";
        try {
            List<List<String>> lists = ExcelUtils.getInstance().readExcel2List(path);
            List<NumberDetail> result = lists.stream().flatMap(Collection::stream)
                    .parallel()
                    .map(Try.of(NumberExcel2DetailExcel::getAddressDetail))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            System.out.println(result.size());
            ExcelUtils.getInstance().exportObjects2Excel(result, NumberDetail.class, true,
                    null, true, "C:\\Users\\bd2\\Desktop\\电话归属地分类\\Done-电话归属地分类.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static NumberDetail getAddressDetail(String number) {
        if (StringUtils.isBlank(number)) {
            return null;
        }
        try {
            PhoneNumberInfo phoneNumberInfo = phoneNumberGeo.lookup(number);
            if (Objects.isNull(phoneNumberInfo)) {
                return new NumberDetail();
            }
            NumberDetail numberDetail = new NumberDetail();
            numberDetail.setNumber(number);
            numberDetail.setProvince(phoneNumberInfo.getProvince());
            numberDetail.setCity(phoneNumberInfo.getCity());
            return numberDetail;
        } catch (Exception e) {
            System.out.println("=======================>" + number);
            e.printStackTrace();
            return new NumberDetail();
        }

    }
}
