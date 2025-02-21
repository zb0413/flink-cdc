package tk.fishfish.cdc.udf;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

// CREATE FUNCTION split_csv AS 'com.udf.SplitCSV';
//
// SELECT
//     order_id,
//     item
// FROM orders,
// LATERAL TABLE(split_csv(items_list)) AS T(item);

// 拆分CSV数据为多行
public class SplitCSV extends TableFunction<Row> {
    public void eval(String csv) {
        String[] items = csv.split(",");
        for (String item : items) {
            collect(Row.of(item.trim()));
        }
    }

    @Override
    public TypeInformation<Row> getResultType() {
        return Types.ROW(Types.STRING);
    }
}