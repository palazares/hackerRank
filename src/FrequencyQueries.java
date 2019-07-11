import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrequencyQueries {

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> valueToOccur = new HashMap<>();
        Map<Integer, Integer> occurToValuesNum = new HashMap<>();

        List<Integer> results = new ArrayList<>();
        for(List<Integer> query : queries){
            Integer request = query.get(0);
            Integer value = query.get(1);

            if(request.equals(1)){
                final Integer newOccurrences = valueToOccur.merge(value, 1, Integer::sum);
                occurToValuesNum.merge(newOccurrences, 1, Integer::sum);
                final Integer valuesNumberForOldOccurrences = occurToValuesNum.get(newOccurrences - 1);
                if(valuesNumberForOldOccurrences != null && valuesNumberForOldOccurrences > 0 ){
                    occurToValuesNum.put(newOccurrences - 1, valuesNumberForOldOccurrences - 1);
                }

            }
            else if (request.equals(2)) {
                final Integer oldOccurrences = valueToOccur.get(value);
                if(oldOccurrences != null && oldOccurrences > 0){
                    valueToOccur.put(value, oldOccurrences - 1);
                    final Integer valuesNumberForOldOccurrences = occurToValuesNum.get(oldOccurrences);
                    if(valuesNumberForOldOccurrences != null && valuesNumberForOldOccurrences > 0){
                        occurToValuesNum.put(oldOccurrences, valuesNumberForOldOccurrences - 1);
                    }

                    if(oldOccurrences > 1) {
                        occurToValuesNum.merge(oldOccurrences - 1, 1, Integer::sum);
                    }
                }
            }
            else if (request.equals(3)) {
                Integer size = occurToValuesNum.get(value);
                if(size == null){
                    size = 0;
                }
                if(size > 0){
                    size = 1;
                }
                results.add(size);
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\resources\\frequencyQueriesInput\\act.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

