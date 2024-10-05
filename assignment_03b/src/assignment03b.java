public class assignment03b {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the number of iterations as an argument.");
            return;
        }

        int iterations = Integer.parseInt(args[0]);
        long startTime, endTime;

        // String test
        String str = "";
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            str += "a";
        }
        endTime = System.currentTimeMillis();
        long stringTime = endTime - startTime;

        // StringBuffer test
        StringBuffer stringBuffer = new StringBuffer();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append("a");
        }
        endTime = System.currentTimeMillis();
        long stringBufferTime = endTime - startTime;

        // StringBuilder test
        StringBuilder stringBuilder = new StringBuilder();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append("a");
        }
        endTime = System.currentTimeMillis();
        long stringBuilderTime = endTime - startTime;

        // Print the results
        System.out.println(stringTime + " " + stringBufferTime + " " + stringBuilderTime);
    }
}

