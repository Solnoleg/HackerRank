import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Test {
    static int sherlockAndAnagrams(String s) {
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i < s.length() - 1; i++) {

                String target = s.substring(j, i + 1);

                String remainder = s.substring(i + 1);

                if (has(target, remainder)) res++;
            }
        }

        return res;
    }

    private static boolean has(String t, String r) {
        char[] chars = t.toCharArray();
        for (char c : chars) {
            if (r.contains(String.valueOf(c))) {
                r = r.replaceFirst(String.valueOf(c), "");
            } else
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

//        int itemCount = s.nextInt();
        int itemCount = 3;
//        double backPackCapacity = s.nextDouble();
        double backPackCapacity = 50;

        int[][] items = new int[itemCount][2];

        items[0][0] = 60;
        items[0][1] = 20;
        items[1][0] = 100;
        items[1][1] = 50;
//        items[2][0] = 120;
        items[2][0] = 600;
//        items[2][1] = 30;
        items[2][1] = 150;

//        while (s.hasNextInt()) {
//            for (int i = 0; i < itemCount; i++) {
//                items[i][0] = s.nextInt();
//                items[i][1] = s.nextInt();
//            }
//        }

        fullBackPack(itemCount, backPackCapacity, items);
    }

    private static void fullBackPack(int itemCount, double backPackCapacity, int[][] items) {
        Arrays.sort(items, Collections.reverseOrder(Comparator.comparing(mas -> mas[0] / mas[1])));

        double res = 0;
        for (int i = 0; i < items.length; i++) {

            int oneItemWeight = items[i][1];

            if (oneItemWeight > backPackCapacity) {
                res += (backPackCapacity / oneItemWeight) * items[i][0];
                backPackCapacity = 0;
            } else {
                backPackCapacity -= oneItemWeight;
                res += items[i][0];
            }

            if (backPackCapacity <= 0) break;
        }

        System.out.println(res);
    }

//    private static int nod(int a, int b) {
//        if (a == 0) return b;
//        if (b == 0) return a;
//        if (a == b) return a;
//
//        if (a > b) {
//            return nod(a % b, b);
//        } else
//            return nod(a, b % a);
//    }

//    private static void getPoints(List<Point> points) {
//        Comparator<Point> comparator = Comparator.comparing(Point::getY).thenComparing(p -> Math.abs(p.y - p.x));
//        List<Integer> res = new ArrayList<>();
//
//        List<Point> sorted = points.stream()
//                .sorted(comparator)
//                .collect(Collectors.toList());
//
//        for (int i = 0; i < sorted.size(); i++) {
//            Point p = sorted.get(i);
//            int y = p.y;
//            res.add(y);
//
//            for (int j = i; j < sorted.size(); j++) {
//                if (lineContainsPoint(sorted.get(j), y)) {
//                    i++;
//                } else {
//                    i--;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(res.size());
//        res.forEach(x -> System.out.print(x + " "));
//    }

//    private static boolean lineContainsPoint(Point point, int y) {
//        return point.x <= y && point.y >= y;
//    }

}

/*

3 50

600 150
60 20
100 50

 */
