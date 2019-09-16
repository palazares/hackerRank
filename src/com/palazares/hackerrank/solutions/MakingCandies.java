package com.palazares.hackerrank.solutions;

public class MakingCandies {

    static long minimumPasses(long m, long w, long p, long n) {
        long passes = 0;
        long candies = 0;
        long run = Long.MAX_VALUE;
        long step;

        while (candies < n)
        {
            step = (m > Long.MAX_VALUE / w) ? 0 : (p - candies) / (m * w);

            if (step <= 0)
            {
                long improvementUnits = candies / p;

                if (m >= w + improvementUnits)
                {
                    w += improvementUnits;
                }
                else if (w >= m + improvementUnits)
                {
                    m += improvementUnits;
                }
                else
                {
                    long total = m + w + improvementUnits;
                    m = total / 2;
                    w = total - m;
                }

                candies %= p;
                step = 1;
            }

            passes += step;

            if (step * m > Long.MAX_VALUE / w)
            {
                candies = Long.MAX_VALUE;
            }
            else
            {
                candies += step * m * w;
                run = Math.min(run, (long)(passes + Math.ceil((n - candies) / (m * w * 1.0))));
            }
        }

        return Math.min(passes, run);
    }

    public static void main(String[] args) {
        long result;

//        result = minimumPasses(3, 1, 2, 12);
//        System.out.println(result);
//
//        result = minimumPasses(1, 2, 1, 60);
//        System.out.println(result);
//
//        result = minimumPasses(4294967296l, 4294967296l, 1000000000, 10000000000l);
//        System.out.println(result);

        result = minimumPasses(1, 1, 400000000000l, 1000000000000l);
        System.out.println(result);
    }
}
