package com.palazares.hackerrank.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sudoku {

    public static char[][] solveSudoku(char[][] board) {
        if (!isValidSudoku(board)) return board;
        int len = board.length;
        Set<Integer>[][] candidates = new Set[len][len];

        fillCandidates(candidates, board);

        solve(candidates, board);

        return board;
    }

    private static void solve(Set<Integer>[][] candidates, char[][] board) {
        while (solveDirect(candidates, board)) {
        }
        attemptToSolve(candidates, board);
    }

    private static boolean attemptToSolve(Set<Integer>[][] candidates, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    Set<Integer> cand = candidates[i][j];
                    for (int c : cand) {
                        board[i][j] = (char) ('1' + c);
                        if (isValidSudoku(board) && attemptToSolve(candidates, board)) {
                            return true;
                        } else {
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solveDirect(Set<Integer>[][] candidates, char[][] board) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (candidates[i][j].size() == 1) {
                    if (markRec(candidates, board, i, j)) result = true;
                }
            }
        }
        return result;
    }

    private static boolean markRec(Set<Integer>[][] candidates, char[][] board, int x, int y) {
        if (board[x][y] != '.') return false;
        int num = candidates[x][y].iterator().next();
        char c = (char) ('1' + num);
        board[x][y] = c;
        removeFromCandidates(candidates, board, x, y);
        return true;
    }

    private static void removeFromCandidates(Set<Integer>[][] candidates, char[][] board, int x, int y) {
        int num = candidates[x][y].iterator().next();
        removeFromLine(candidates, board, num, x);
        removeFromColumn(candidates, board, num, y);
        removeFromSubSquare(candidates, board, num, x, y);
    }

    private static void removeFromLine(Set<Integer>[][] candidates, char[][] board, int num, int x) {
        for (int i = 0; i < candidates.length; i++) {
            Set<Integer> cand = candidates[x][i];
            cand.remove(num);
        }
        for (int i = 0; i < candidates.length; i++) {
            Set<Integer> cand = candidates[x][i];
            if (cand.size() == 1) {
                markRec(candidates, board, x, i);
            }
        }
    }

    private static void removeFromColumn(Set<Integer>[][] candidates, char[][] board, int num, int y) {
        for (int i = 0; i < candidates.length; i++) {
            Set<Integer> cand = candidates[i][y];
            cand.remove(num);
        }
        for (int i = 0; i < candidates.length; i++) {
            Set<Integer> cand = candidates[i][y];
            if (cand.size() == 1) {
                markRec(candidates, board, i, y);
            }
        }
    }

    private static void removeFromSubSquare(Set<Integer>[][] candidates, char[][] board, int num, int x, int y) {
        int xS = (x / 3) * 3;
        int yS = (y / 3) * 3;
        for (int i = xS; i < 3; i++) {
            for (int j = yS; j < 3; j++) {
                Set<Integer> cand = candidates[i][y];
                cand.remove(num);
            }
        }
        for (int i = xS; i < 3; i++) {
            for (int j = yS; j < 3; j++) {
                Set<Integer> cand = candidates[i][y];
                if (cand.size() == 1) {
                    markRec(candidates, board, i, y);
                }
            }
        }
    }

    private static void fillCandidates(Set<Integer>[][] candidates, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                candidates[i][j] = getAllCandidates(board, i, j);
            }
        }
    }

    private static Set<Integer> getAllCandidates(char[][] board, int x, int y) {
        if (board[x][y] != '.') {
            int num = board[x][y] - '1';
            Set<Integer> result = new HashSet<>();
            result.add(num);
            return result;
        }
        Set<Integer> result = IntStream.range(0, 9).boxed().collect(Collectors.toSet());
        result.removeAll(getAllFilled(board, x, y));
        return result;
    }

    private static Set<Integer> getAllFilledFromLine(char[][] board, int x) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            if (board[x][i] != '.') {
                int num = board[x][i] - '1';
                result.add(num);
            }
        }
        return result;
    }

    private static Set<Integer> getAllFilledFromRow(char[][] board, int x) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i][x] != '.') {
                int num = board[i][x] - '1';
                result.add(num);
            }
        }
        return result;
    }

    private static Set<Integer> getAllFilledFromSubSquare(char[][] board, int x, int y) {
        Set<Integer> result = new HashSet<>();
        int xS = (x / 3) * 3;
        int yS = (y / 3) * 3;
        for (int i = xS; i < 3; i++) {
            for (int j = yS; j < 3; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    result.add(num);
                }
            }
        }
        return result;
    }

    private static Set<Integer> getAllFilled(char[][] board, int x, int y) {
        Set<Integer> result = new HashSet<>();
        result.addAll(getAllFilledFromLine(board, x));
        result.addAll(getAllFilledFromRow(board, y));
        result.addAll(getAllFilledFromSubSquare(board, x, y));
        return result;
    }

    public static boolean isValidSudoku(char[][] board) {
        return checkVertical(board) && checkHorizontal(board) && checkSubsquares(board);
    }

    private static boolean checkVertical(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            boolean[] check = new boolean[board.length];
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    if (check[num]) return false;
                    check[num] = true;
                }
            }
        }
        return true;
    }

    private static boolean checkHorizontal(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            boolean[] check = new boolean[board.length];
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != '.') {
                    int num = board[j][i] - '1';
                    if (check[num]) return false;
                    check[num] = true;
                }
            }
        }
        return true;
    }

    private static boolean checkSubsquares(char[][] board) {
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board.length; j += 3) {
                if (!checkSubsquare(board, i, j)) return false;
            }
        }
        return true;
    }

    private static boolean checkSubsquare(char[][] board, int x, int y) {
        boolean[] check = new boolean[board.length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[x + i][y + j] != '.') {
                    char c = board[x + i][y + j];
                    int num = c - '1';
                    if (check[num]) return false;
                    check[num] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        for (char[] ar : board) {
            StringBuilder sb = new StringBuilder();
            for (char c : ar) {
                sb.append(c);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }

        System.out.println();
        System.out.println("-====Solution====-");
        System.out.println();

        solveSudoku(board);

        for (char[] ar : board) {
            StringBuilder sb = new StringBuilder();
            for (char c : ar) {
                sb.append(c);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
