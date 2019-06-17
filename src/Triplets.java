import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Triplets {
    static class Triplet {
        long base;
        int rPower;

        Triplet(long base, int rPower) {
            this.base = base;
            this.rPower = rPower;
        }
    }

    private static Map<Long, Triplet> valuesCache = new HashMap<>();

    // Complete the countTriplets function below.
    private static long countTriplets(List<Long> arr, long r) {
        Map<Long, List<Triplet>> triplets = new HashMap<>();

        for (Long aLong : arr) {
            Triplet triplet = getTriplet(aLong, r);
            List<Triplet> listForBase = triplets.computeIfAbsent(triplet.base, (k) -> new ArrayList<>());
            listForBase.add(triplet);
        }

        long totalNumTriplets = 0;
        for (Map.Entry<Long, List<Triplet>> entry : triplets.entrySet()) {
            int size = entry.getValue().size();
            if (size < 3) {
                continue;
            }
            Map<Integer, Integer> numberOfPowers = new HashMap<>();
            for (int i = size - 1; i >= 0; i--) {
                Triplet firstTriplet = entry.getValue().get(i);
                int firstPower = firstTriplet.rPower;
                numberOfPowers.merge(firstPower, 1, Integer::sum);
                Integer nextPower = numberOfPowers.get(firstPower + 1);
                Integer nextNextPower = numberOfPowers.get(firstPower + 2);
                if(nextPower != null && nextNextPower != null){
                    totalNumTriplets += nextPower * nextNextPower;
                }
            }
        }
        return totalNumTriplets;
    }

    private static Triplet getTriplet(Long number, long r) {
        Triplet cached = valuesCache.get(number);
        if(cached != null){
            return new Triplet(cached.base, cached.rPower);
        }
        long base = number;
        int rPower = 0;
        while(base % r == 0){
            rPower++;
            base /= r;
            cached = valuesCache.get(base);
            if(cached != null){
                Triplet triplet = new Triplet(cached.base, cached.rPower + rPower);
                valuesCache.put(number, triplet);
                return triplet;
            }
        }
        Triplet triplet = new Triplet(base, rPower);
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

