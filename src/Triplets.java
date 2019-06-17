import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Triplets {
    static class Number {
        Long base;
        Integer rPower;

        Number(Long base, Integer rPower) {
            this.base = base;
            this.rPower = rPower;
        }
    }

    private static Map<Long, Number> valuesCache = new HashMap<>();

    // Complete the countTriplets function below.
    private static long countTriplets(List<Long> arr, long r) {
        Map<Long, List<Number>> triplets = new HashMap<>();

        for (Long aLong : arr) {
            Number triplet = getNumber(aLong, r);
            List<Number> listForBase = triplets.computeIfAbsent(triplet.base, (k) -> new ArrayList<>());
            listForBase.add(triplet);
        }

        long totalNumTriplets = 0;
        for (Map.Entry<Long, List<Number>> entry : triplets.entrySet()) {
            int size = entry.getValue().size();
            if (size < 3) {
                continue;
            }
            Map<Integer, Integer> numberOfPowers = new HashMap<>();
            for (int i = size - 1; i >= 0; i--) {
                int power = entry.getValue().get(i).rPower;
                numberOfPowers.merge(power, 1, Integer::sum);
                Integer nextPower = numberOfPowers.get(power + 1);
                Integer nextNextPower = numberOfPowers.get(power + 2);
                if(nextPower != null && nextNextPower != null){
                    totalNumTriplets += nextPower * nextNextPower;
                }
            }
        }
        return totalNumTriplets;
    }

    private static Number getNumber(Long number, Long r) {
        Number cached = valuesCache.get(number);
        if(cached != null){
            return new Number(cached.base, cached.rPower);
        }
        Long base = number;
        Integer rPower = 0;
        while(base % r == 0){
            rPower++;
            base /= r;
            cached = valuesCache.get(base);
            if(cached != null){
                Number triplet = new Number(cached.base, cached.rPower + rPower);
                valuesCache.put(number, triplet);
                return triplet;
            }
        }
        Number triplet = new Number(base, rPower);
        valuesCache.put(number, triplet);
        return triplet;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\resources\\tripletsInput\\1_act.txt"));

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

