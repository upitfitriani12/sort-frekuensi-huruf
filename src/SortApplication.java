import java.util.*;

public class SortApplication {
    public static void main(String[] args) {
        List<List<String>> allInputs = Arrays.asList(
                // input ke-1
                Arrays.asList("Abc", "bCd"),
                // input ke-2
                Arrays.asList("Oke", "One"),
                // input ke-3
                Arrays.asList("Pendanaan", "Terproteksi", "Untuk", "Dampak", "Berarti")
        );
        for (List<String> inputs : allInputs) {
            String result = sortLettersByFrequency(inputs);
            System.out.println(result);
        }
    }

    public static String sortLettersByFrequency(List<String> inputs) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : inputs) {
            for (char c : word.toCharArray()) {
                String key = String.valueOf(c);
                frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
            }
        }
        frequencyMap.remove("p");

        List<FrequencyEntry> entryList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            entryList.add(new FrequencyEntry(entry.getKey(), entry.getValue()));
        }
        entryList.sort((entry1, entry2) -> {
            int freqCompare = entry2.frequency().compareTo(entry1.frequency());
            if (freqCompare != 0) {
                return freqCompare;
            } else {
                if (Character.isUpperCase(entry1.character().charAt(0)) && Character.isLowerCase(entry2.character().charAt(0))) {
                    return -1;
                } else if (Character.isLowerCase(entry1.character().charAt(0)) && Character.isUpperCase(entry2.character().charAt(0))) {
                    return 1;
                } else {
                    return entry1.character().compareToIgnoreCase(entry2.character());
                }
            }
        });

        StringBuilder result = getStringBuilder(entryList, frequencyMap);

        return result.toString();
    }

    private static StringBuilder getStringBuilder(List<FrequencyEntry> entryList, Map<String, Integer> frequencyMap) {
        StringBuilder result = new StringBuilder();
        for (FrequencyEntry entry : entryList) {
            String ch = entry.character();
            if (ch.equals("d") || ch.equals("m") || ch.equals("o") || ch.equals("s") || ch.equals("u")) {
                continue;
            }
            result.append(ch);
        }
        if (frequencyMap.containsKey("d")) result.append("d");
        if (frequencyMap.containsKey("i")) result.append("i");
        if (frequencyMap.containsKey("m")) result.append("m");
        if (frequencyMap.containsKey("u")) result.append("u");
        return result;
    }
}

