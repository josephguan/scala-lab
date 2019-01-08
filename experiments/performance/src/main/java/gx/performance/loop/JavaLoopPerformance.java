package gx.performance.loop;


public class JavaLoopPerformance {

    public static long forLoop(long times) {
        long cnt = 0;
        for (long i = 0; i < times; i++) {
            cnt += 1;
        }
        return cnt;
    }

    public static long whileLoop(long times) {
        long cnt = 0;
        long i = 0;
        while (i < times) {
            cnt += 1;
            i += 1;
        }
        return cnt;
    }

}
