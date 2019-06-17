import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Triplets {

    static class Triplet {
        long base;
        int rPower;
        int index;
        int predecessors;

        Triplet(long base, int rPower, int index) {
            this.base = base;
            this.rPower = rPower;
            this.index = index;
        }
    }

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Map<Long, ArrayList<Triplet>> triplets = new HashMap<>();

        for (int i = 0; i < arr.size(); i ++) {
            Triplet triplet = getTriplet(arr.get(i), r, i);
            ArrayList<Triplet> listForBase = triplets.computeIfAbsent(triplet.base, (k) -> new ArrayList<>());
            listForBase.add(triplet);
        }

        long totalNumTriplets = 0;
        for (Map.Entry<Long, ArrayList<Triplet>> entry : triplets.entrySet()) {
            int size = entry.getValue().size();
            if (size <= 2) {
                continue;
            }
            for (int i = size - 1; i > 0; i--) {
                Triplet firstTriplet = entry.getValue().get(i);
                int firstPower = firstTriplet.rPower;
                for (int j = i - 1; j >= 0; j--) {
                    Triplet secondTriplet = entry.getValue().get(j);
                    int secondPower = secondTriplet.rPower;
                    if(secondPower == firstPower - 1){
                        secondTriplet.predecessors++;
                        totalNumTriplets += firstTriplet.predecessors;
                    }
                }
            }

        }
        return totalNumTriplets;
    }

    static Triplet getTriplet(Long number, long r, int index) {
        int rPower = 0;
        while(number % r == 0){
            rPower++;
            number /= r;
        }
        return new Triplet(number, rPower, index);
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

