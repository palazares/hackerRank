import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyQueries {

    static List<Integer> freqQuery(int[][] queries) {
        Map<Integer, Integer> valueToOccur = new HashMap<>();
        Map<Integer, Integer> occurToValuesNum = new HashMap<>();

        List<Integer> results = new ArrayList<>();
        for(int[] query : queries){
            Integer request = query[0];
            Integer value = query[1];

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
                results.add(size == null || size == 0 ? 0 : 1);
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in))) {

            int q = Integer.parseInt(bufferedReader.readLine().trim());
            int[][] queries = new int[q][2];

            for (int i = 0; i < q; i++) {
                String[] query = bufferedReader.readLine().split(" ");
                queries[i][0] = Integer.parseInt(query[0]);
                queries[i][1] = Integer.parseInt(query[1]);
            }

            List<Integer> ans = freqQuery(queries);

            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(".\\resources\\frequencyQueriesInput\\1_act.txt"))) {

                bufferedWriter.write(ans.stream().map(Object::toString)
                        .collect(joining("\n")) + "\n");
            }
        }
    }
}

