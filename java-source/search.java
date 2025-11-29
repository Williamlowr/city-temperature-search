import java.io.*;
import java.nio.file.*;
import java.util.*;

public class search {
    public static void main(String[] args) throws Exception {

        // Read parameters from environment passed by Vercel
        String query = System.getenv("QUERY_STRING");
        Map<String,String> params = parseParams(query);

        String category = params.getOrDefault("category", "");
        String value    = params.getOrDefault("value", "");

        // Load CSV into LinkedList using your code
        LinkedList ll = new LinkedList();
        WorldTemps.importFile(ll, null);

        StringBuilder out = new StringBuilder();

        if (category == null || category.isEmpty()) {
            reading min = WorldTemps.findMin(ll);
            reading max = WorldTemps.findMax(ll);

            out.append("Global Min:\n");
            out.append(format(min));
            out.append("\nGlobal Max:\n");
            out.append(format(max));
        } else {
            reading min = WorldTemps.findMin(ll, category, value);
            reading max = WorldTemps.findMax(ll, category, value);

            out.append("Query: " + category + " = " + value + "\n\n");
            out.append("Min Result:\n");
            out.append(format(min));
            out.append("\nMax Result:\n");
            out.append(format(max));
        }

        System.out.print(out.toString());
    }

    static String format(reading r) {
        if (r == null) return "No matching results.\n";

        return String.format(
            "%1.2f°F on %d-%d-%d in %s, %s, %s",
            r.getAvgTemperature(),
            r.getMonth(),
            r.getDay(),
            r.getYear(),
            r.getCity(),
            r.getState(),
            r.getCountry()
        );
    }

    static Map<String,String> parseParams(String raw) {
        Map<String,String> map = new HashMap<>();
        if (raw == null || raw.isEmpty()) return map;

        String[] parts = raw.split("&");
        for (String p : parts) {
            if (!p.contains("=")) continue;
            String[] kv = p.split("=", 2);
            map.put(kv[0], kv.length > 1 ? kv[1] : "");
        }
        return map;
    }
}