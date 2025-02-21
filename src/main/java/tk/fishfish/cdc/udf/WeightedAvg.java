package tk.fishfish.cdc.udf;

import org.apache.flink.table.functions.AggregateFunction;

// CREATE FUNCTION weighted_avg AS 'com.udf.WeightedAvg';
//
// SELECT
//   product_id,
//   weighted_avg(rating, sales_volume) AS weighted_rating
// FROM product_reviews
// GROUP BY product_id;

// 计算加权平均值
public class WeightedAvg extends AggregateFunction<Double, WeightedAvg.WeightedAccum> {

    // 累加器结构
    public static class WeightedAccum {
        public double sum = 0.0;
        public int count = 0;
    }

    @Override
    public Double getValue(WeightedAccum acc) {
        return acc.sum / acc.count;
    }

    @Override
    public WeightedAccum createAccumulator() {
        return new WeightedAccum();
    }

    // 累加方法
    public void accumulate(WeightedAccum acc, Double value, Integer weight) {
        acc.sum += value * weight;
        acc.count += weight;
    }
}