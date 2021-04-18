import java.util.HashMap;
import java.util.Map;

//Queen's Attack 2
public class QueensAttackII {
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int res = 0;
        Map<String, String> map = getObstaclesAsMap(obstacles);

        int pStepDown = r_q - 1;
        int pStepLeft = c_q - 1;
        int pStepRight = c_q + 1;
        int pStepUp = r_q + 1;

        //diagonals down right (-1; 1)
        while (isValidPosition(pStepDown, pStepRight, "rightDown", n) && !map.containsKey(pStepDown + "," + pStepRight)) {
            res++;
            pStepDown--;
            pStepRight++;
        }
        pStepDown = r_q - 1;
        pStepRight = c_q + 1;

        //diagonals down left (-1; -1)
        while (isValidPosition(pStepDown, pStepLeft, "leftDown", n) && !map.containsKey(pStepDown + "," + pStepLeft)) {
            res++;
            pStepDown--;
            pStepLeft--;
        }
        pStepLeft = c_q - 1;

        //diagonals up left (1; -1)
        while (isValidPosition(pStepUp, pStepLeft, "leftUp", n) && !map.containsKey(pStepUp + "," + pStepLeft)) {
            res++;
            pStepUp++;
            pStepLeft--;
        }
        pStepUp = r_q + 1;

        //diagonals up right (1; 1)
        while (isValidPosition(pStepUp, pStepRight, "rightUp", n) && !map.containsKey(pStepUp + "," + pStepRight)) {
            res++;
            pStepUp++;
            pStepRight++;
        }

        int horizontal = n - 1;
        int vertical = n - 1;

        if (obstacles != null && obstacles.length > 0) {
            for (int[] oneObstacle : obstacles) {
                int r_o = oneObstacle[0];
                int c_o = oneObstacle[1];

                if (r_q == r_o) {
                    if (c_o < c_q)
                        horizontal -= c_o;
                    else if (c_o > c_q)
                        horizontal -= (n - (c_o - 1));
                } else if (c_q == c_o) {
                    if (r_o < r_q)
                        vertical -= r_o;
                    else vertical -= (n - (r_o - 1));
                }
            }
        }
        return res + horizontal + vertical;
    }

    private static Map<String, String> getObstaclesAsMap(int[][] obstacles) {
        Map<String, String> obstaclesAsMap = new HashMap<>();

        if (obstacles != null && obstacles.length > 0)
            for (int[] oneObstacle : obstacles) {
                int c_o = oneObstacle[0];
                int r_o = oneObstacle[1];
                obstaclesAsMap.put(c_o + "," + r_o, "*");
            }
        return obstaclesAsMap;
    }

    private static boolean isValidPosition(int positionOne, int positionTwo, String direction, int chessBoardSize) {
        switch (direction) {
            case "leftDown":
                return positionOne >= 1 && positionTwo >= 1;
            case "rightDown":
                return positionOne >= 1 && positionTwo <= chessBoardSize;
            case "leftUp":
                return positionOne <= chessBoardSize && positionTwo >= 1;
            case "rightUp":
                return positionOne <= chessBoardSize && positionTwo <= chessBoardSize;
        }

        return false;
    }
}
