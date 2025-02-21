package tk.fishfish.cdc.udf;

import org.apache.flink.table.functions.ScalarFunction;

//-- 注册函数
// CREATE TEMPORARY FUNCTION mask_phone AS 'tk.fishfish.cdc.udf.MaskPhoneNumber';
//-- 使用示例
// SELECT
// user_id,
// mask_phone(phone) AS masked_phone
// FROM user_logs;

// 处理手机号脱敏
public class MaskPhoneNumber extends ScalarFunction {
    public String eval(String phone) {
        if (phone == null || phone.length() != 11) return "INVALID";
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }
}