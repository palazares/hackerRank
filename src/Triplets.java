import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Triplets {
    private static class Number {
        Long base;
        Integer rPower;

        Number(Long base, Integer rPower) {
            this.base = base;
            this.rPower = rPower;
        }
    }

    private static class ValueCount {
        Integer powNum;
        Integer tuplesNum;

        ValueCount(){
            this.powNum = 0;
            this.tuplesNum = 0;
        }
    }

    private static Map<Long, Number> valuesCache = new HashMap<>();

    // Complete the countTriplets function below.
    private static long countTriplets(List<Long> arr, long r) {
        if(r == 1L){
            return countTripletsWhenBase1(arr);
        }

        long totalNumTriplets = 0;
        Map<Long, ValueCount[]> triplets = new HashMap<>();
        for (int i = arr.size() - 1; i >= 0; i--) {
            Number number = getNumber(arr.get(i), r);
            ValueCount[] numberOfPowers = triplets.computeIfAbsent(number.base, k -> new ValueCount[30]);
            ValueCount powerValue = numberOfPowers[number.rPower];
            if(powerValue == null){
                powerValue = new ValueCount();
                numberOfPowers[number.rPower] = powerValue;
            }
            powerValue.powNum++;

            ValueCount powerPlus1Value = numberOfPowers[number.rPower + 1];
            if(powerPlus1Value != null) {
                powerValue.tuplesNum += powerPlus1Value.powNum;
                totalNumTriplets += powerPlus1Value.tuplesNum;
            }
        }
        return totalNumTriplets;
    }

    private static Number getNumber(Long number, Long r) {
        Number cached = valuesCache.get(number);
        if(cached != null){
            return cached;
        }
        Long base = number;
        Integer rPower = 0;
        while(base % r == 0){
            rPower++;
            base /= r;
            cached = valuesCache.get(base);
            if(cached != null){
                rPower += cached.rPower;
                base = cached.base;
                break;
            }
        }
        Number triplet = new Number(base, rPower);
        valuesCache.put(number, triplet);
        return triplet;
    }

    private static long countTripletsWhenBase1(List<Long> arr) {
        final Map<Long, Integer> vals = new HashMap<>();
        long totalSum = 0;
        for (Long aLong : arr) {
            vals.merge(aLong, 1, Integer::sum);
            final Integer currentNumberOfVals = vals.get(aLong);
            if(currentNumberOfVals > 2){
                totalSum += countAriphmProgrSum(currentNumberOfVals - 2);
            }
        }

        return totalSum;
    }

    private static long countAriphmProgrSum(int n){
        return n*(n+1) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\resources\\tripletsInput\\act.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

