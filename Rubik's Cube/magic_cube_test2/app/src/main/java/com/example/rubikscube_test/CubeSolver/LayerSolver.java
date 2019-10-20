package com.example.rubikscube_test.CubeSolver;

import java.util.Random;
import java.util.Scanner;

public class LayerSolver
{
    String move_sssss = "";
    String fuck = "  2'";

    int stateCount = 0;
    int moveCount = 0;
    int maxCount = 0;
    int minCount = 10000;
    boolean printCubeVerbose = true;



    public LayerSolver(String cubeString) {
        char[] c = {
                'N', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W',
                'N', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
                'N', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G',
                'N', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R',
                'N', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y',
                'N', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'
        };
        StringBuilder stringBuilder = new StringBuilder(cubeString);
        for (int i = 0; i < stringBuilder.length(); i++ ) {
            if (stringBuilder.charAt(i) == 'F') stringBuilder.setCharAt(i, 'G');
            else if (stringBuilder.charAt(i) == 'L') stringBuilder.setCharAt(i, 'O');
            else if (stringBuilder.charAt(i) == 'U') stringBuilder.setCharAt(i, 'W');
            else if (stringBuilder.charAt(i) == 'D') stringBuilder.setCharAt(i, 'Y');
        }
        int i = 0;
        for (int j = 1; j <= 9; i++, j++) c[j] = stringBuilder.charAt(i);
        for (int j = 31; j <= 39; i++, j++) c[j] = stringBuilder.charAt(i);
        for (int j = 21; j <= 29; i++, j++) c[j] = stringBuilder.charAt(i);
        for (int j = 41; j <= 49; i++, j++) c[j] = stringBuilder.charAt(i);
        for (int j = 11; j <= 19; i++, j++) c[j] = stringBuilder.charAt(i);
        for (int j = 59; j >= 51; i++, j--) c[j] = stringBuilder.charAt(i);
//        shuffleCube(c, 4);
        printCube(c);
        solver(c);
        move_sssss = move_sssss.substring(1);
    }

    public String solve() {
        return move_sssss;
    }

    public void printCube(char [] c)
    {
        if(printCubeVerbose == true)
        {
            System.out.printf("         -------\n");
            System.out.printf("        | %c %c %c |     \n        | %c %c %c |      \n        | %c %c %c |     \n", c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
            System.out.printf(" ------- ------- -------\n");
            System.out.printf("| %c %c %c | %c %c %c | %c %c %c |\n| %c %c %c | %c %c %c | %c %c %c |\n| %c %c %c | %c %c %c | %c %c %c |\n", c[11], c[12], c[13], c[21], c[22], c[23], c[31], c[32], c[33], c[14], c[15], c[16], c[24], c[25], c[26], c[34], c[35], c[36], c[17], c[18], c[19], c[27], c[28], c[29], c[37], c[38], c[39]);
            System.out.printf(" ------- ------- -------\n");
            System.out.printf("        | %c %c %c |     \n        | %c %c %c |      \n        | %c %c %c |     \n", c[41], c[42], c[43], c[44], c[45], c[46], c[47], c[48], c[49]);
            System.out.printf("         -------\n");
            System.out.printf("        | %c %c %c |     \n        | %c %c %c |      \n        | %c %c %c |     \n", c[51], c[52], c[53], c[54], c[55], c[56], c[57], c[58], c[59]);
            System.out.printf("         -------\n");
            System.out.println();
        }
    }
    public void solver(char [] c)
    {
        moveCount = 0;
        state0(c);
        state1(c);
        state2(c);
        state3(c);
        state4thru7(c, 4, 'W', 'R', 'G');
        state4thru7(c, 5, 'R', 'Y', 'G');
        state4thru7(c, 6, 'Y', 'O', 'G');
        state4thru7(c, 7, 'O', 'W', 'G');
        X(c, 3);
        System.out.printf("X'\n");
        state8thru11(c, 8, 'W', 'R');
        state8thru11(c, 9, 'R', 'Y');
        state8thru11(c, 10, 'Y', 'O');
        state8thru11(c, 11, 'O', 'W');
        state12(c);
        state13advanced(c);
        state14advanced(c);
        state15advanced(c);
        if(moveCount < minCount)
        {
            minCount = moveCount;
        }
        if(moveCount > maxCount)
        {
            maxCount = moveCount;
        }
        System.out.printf("Solution reached in %d moves.\n", moveCount);
    }
    public char [] state0(char [] c)
    {
        if(c[8] == 'W' && c[22] == 'G') { System.out.printf("\tState 0 solved\n"); return c; }
        if(c[34] == 'W' && c[26] == 'G') { mS(c, "F' "); }
        if(c[42] == 'W' && c[28] == 'G') { mS(c, "F2 "); }
        if(c[16] == 'W' && c[24] == 'G') { mS(c, "F "); }
        if(c[58] == 'W' && c[2] == 'G') { mS(c, "U' "); }
        if(c[32] == 'W' && c[6] == 'G') { mS(c, "R' "); }
        if(c[22] == 'W' && c[8] == 'G') { mS(c, "U "); }
        if(c[12] == 'W' && c[4] == 'G') { mS(c, "L "); }
        if(c[4] == 'W' && c[12] == 'G') { mS(c, "U' "); }
        if(c[24] == 'W' && c[16] == 'G') { mS(c, "L' "); }
        if(c[44] == 'W' && c[18] == 'G') { mS(c, "D "); }
        if(c[54] == 'W' && c[14] == 'G') { mS(c, "L "); }
        if(c[6] == 'W' && c[32] == 'G') { mS(c, "U "); }
        if(c[56] == 'W' && c[36] == 'G') { mS(c, "R' "); }
        if(c[46] == 'W' && c[38] == 'G') { mS(c, "D' "); }
        if(c[26] == 'W' && c[34] == 'G') { mS(c, "R "); }
        if(c[28] == 'W' && c[42] == 'G') { mS(c, "D' "); }
        if(c[38] == 'W' && c[46] == 'G') { mS(c, "R "); }
        if(c[52] == 'W' && c[48] == 'G') { mS(c, "D "); }
        if(c[18] == 'W' && c[44] == 'G') { mS(c, "L' "); }
        if(c[48] == 'W' && c[52] == 'G') { mS(c, "B2 "); }
        if(c[36] == 'W' && c[56] == 'G') { mS(c, "B' "); }
        if(c[2] == 'W' && c[58] == 'G') { mS(c, "U2 "); }
        if(c[14] == 'W' && c[54] == 'G') { mS(c, "L2 "); }
        return state0(c);
    }
    public char [] state1(char [] c)
    {
        if(c[42] == 'Y' && c[28] == 'G') { System.out.printf("\tState 1 solved\n"); return c; }
        if(c[34] == 'Y' && c[26] == 'G') { mS(c, "R' "); }
        if(c[16] == 'Y' && c[24] == 'G') { mS(c, "L "); }
        if(c[58] == 'Y' && c[2] == 'G') { mS(c, "B "); }
        if(c[32] == 'Y' && c[6] == 'G') { mS(c, "R "); }
        if(c[12] == 'Y' && c[4] == 'G') { mS(c, "L' "); }
        if(c[4] == 'Y' && c[12] == 'G') { mS(c, "L2 "); }
        if(c[24] == 'Y' && c[16] == 'G') { mS(c, "L "); }
        if(c[44] == 'Y' && c[18] == 'G') { mS(c, "D "); }
        if(c[54] == 'Y' && c[14] == 'G') { mS(c, "L' "); }
        if(c[6] == 'Y' && c[32] == 'G') { mS(c, "R2 "); }
        if(c[56] == 'Y' && c[36] == 'G') { mS(c, "R "); }
        if(c[46] == 'Y' && c[38] == 'G') { mS(c, "D' "); }
        if(c[26] == 'Y' && c[34] == 'G') { mS(c, "R' "); }
        if(c[28] == 'Y' && c[42] == 'G') { mS(c, "D2 "); }
        if(c[38] == 'Y' && c[46] == 'G') { mS(c, "D "); }
        if(c[52] == 'Y' && c[48] == 'G') { mS(c, "B' "); }
        if(c[18] == 'Y' && c[44] == 'G') { mS(c, "D' "); }
        if(c[48] == 'Y' && c[52] == 'G') { mS(c, "D2 "); }
        if(c[36] == 'Y' && c[56] == 'G') { mS(c, "B' "); }
        if(c[2] == 'Y' && c[58] == 'G') { mS(c, "B2 "); }
        if(c[14] == 'Y' && c[54] == 'G') { mS(c, "B "); }
        return state1(c);
    }
    public char [] state2(char [] c)
    {
        if(c[34] == 'R' && c[26] == 'G') { System.out.printf("\tState 2 solved\n"); return c; }
        if(c[16] == 'R' && c[24] == 'G') { mS(c, "L2 "); }
        if(c[58] == 'R' && c[2] == 'G') { mS(c, "U R' U' "); }
        if(c[32] == 'R' && c[6] == 'G') { mS(c, "R' "); }
        if(c[12] == 'R' && c[4] == 'G') { mS(c, "L' "); }
        if(c[4] == 'R' && c[12] == 'G') { mS(c, "L' "); }
        if(c[24] == 'R' && c[16] == 'G') { mS(c, "L2 "); }
        if(c[44] == 'R' && c[18] == 'G') { mS(c, "L "); }
        if(c[54] == 'R' && c[14] == 'G') { mS(c, "B' "); }
        if(c[6] == 'R' && c[32] == 'G') { mS(c, "R "); }
        if(c[56] == 'R' && c[36] == 'G') { mS(c, "B "); }
        if(c[46] == 'R' && c[38] == 'G') { mS(c, "R' "); }
        if(c[26] == 'R' && c[34] == 'G') { mS(c, "R2 "); }
        if(c[38] == 'R' && c[46] == 'G') { mS(c, "R "); }
        if(c[52] == 'R' && c[48] == 'G') { mS(c, "B2 "); }
        if(c[18] == 'R' && c[44] == 'G') { mS(c, "L "); }
        if(c[48] == 'R' && c[52] == 'G') { mS(c, "B "); }
        if(c[36] == 'R' && c[56] == 'G') { mS(c, "R2 "); }
        if(c[2] == 'R' && c[58] == 'G') { mS(c, "B' "); }
        if(c[14] == 'R' && c[54] == 'G') { mS(c, "B2 "); }
        return state2(c);
    }
    public char [] state3(char [] c)
    {
        if(c[16] == 'O' && c[24] == 'G') { System.out.printf("\tState 3 solved\n"); return c; }
        if(c[58] == 'O' && c[2] == 'G') { mS(c, "U' L U "); }
        if(c[32] == 'O' && c[6] == 'G') { mS(c, "R B2 R' "); }
        if(c[12] == 'O' && c[4] == 'G') { mS(c, "L "); }
        if(c[4] == 'O' && c[12] == 'G') { mS(c, "L' "); }
        if(c[24] == 'O' && c[16] == 'G') { mS(c, "L2 "); }
        if(c[44] == 'O' && c[18] == 'G') { mS(c, "L "); }
        if(c[54] == 'O' && c[14] == 'G') { mS(c, "B' "); }
        if(c[6] == 'O' && c[32] == 'G') { mS(c, "R B R' "); }
        if(c[56] == 'O' && c[36] == 'G') { mS(c, "B "); }
        if(c[46] == 'O' && c[38] == 'G') { mS(c, "R' B R "); }
        if(c[38] == 'O' && c[46] == 'G') { mS(c, "R' B2 R "); }
        if(c[52] == 'O' && c[48] == 'G') { mS(c, "B2 "); }
        if(c[18] == 'O' && c[44] == 'G') { mS(c, "L' "); }
        if(c[48] == 'O' && c[52] == 'G') { mS(c, "B' "); }
        if(c[36] == 'O' && c[56] == 'G') { mS(c, "B2 "); }
        if(c[2] == 'O' && c[58] == 'G') { mS(c, "B "); }
        if(c[14] == 'O' && c[54] == 'G') { mS(c, "L2 "); }
        return state3(c);
    }

    public char [] state4thru7(char [] c, int s, char one, char two, char three)
    {
        if(c[9] == one && c[31] == two && c[23] == three) { front(c, 3); System.out.printf("F' \tState %d solved\n", s); moveCount++; return c; }
        if((c[31] == one && c[23] == two && c[9] == three) || (c[57] == one && c[1] == two && c[11] == three)) { mS(c, "R B' R' "); }
        if((c[23] == one && c[9] == two && c[31] == three) || (c[39] == one && c[53] == two && c[49] == three)) { mS(c, "U' B U "); }
        if(c[1] == one && c[11] == two && c[57] == three) { mS(c, "R B2 R' "); }
        if((c[37] == one && c[43] == two && c[29] == three) || (c[43] == one && c[29] == two && c[37] == three) || (c[29] == one && c[37] == two && c[43] == three)) { mS(c, "R' B R "); }
        if((c[41] == one && c[19] == two && c[27] == three) || (c[19] == one && c[27] == two && c[41] == three) || (c[27] == one && c[41] == two && c[19] == three)) { mS(c, "L B L' "); }
        if((c[13] == one && c[7] == two && c[21] == three) || (c[7] == one && c[21] == two && c[13] == three) || (c[21] == one && c[13] == two && c[7] == three)) { mS(c, "L' B L "); }
        if((c[59] == one && c[33] == two && c[3] == three) || (c[33] == one && c[3] == two && c[59] == three) || (c[47] == one && c[51] == two && c[17] == three)) { mS(c, "B "); }
        if((c[53] == one && c[49] == two && c[39] == three) || (c[49] == one && c[39] == two && c[53] == three) || (c[11] == one && c[57] == two && c[1] == three)) { mS(c, "B2 "); }
        if((c[51] == one && c[17] == two && c[47] == three) || (c[17] == one && c[47] == two && c[51] == three) || (c[3] == one && c[59] == two && c[33] == three)) { mS(c, "B' "); }
        return state4thru7(c, s, one, two, three);
    }
    public char [] state8thru11(char [] c, int s, char one, char two)
    {
        if(c[26] == one && c[34] == two) { Y(c, 1); System.out.printf("Y \tState %d solved\n", s); return c; }
        if(c[6] == one && c[32] == two) { mS(c, "U' F' U F U R U' R' "); }
        if((c[32] == one && c[6] == two) || (c[2] == one && c[58] == two)) { mS(c, "U "); }
        if((c[58] == one && c[2] == two) || (c[4] == one && c[12] == two)) { mS(c, "U2 "); }
        if((c[12] == one && c[4] == two) || (c[8] == one && c[22] == two)) { mS(c, "U' "); }
        if((c[22] == one && c[8] == two) || (c[34] == one && c[26] == two)) { mS(c, "U R U' R' U' F' U F "); }
        if((c[24] == one && c[16] == two) || (c[16] == one && c[24] == two)) { mS(c, "U' L' U L U F U' F' "); }
        if((c[56] == one && c[36] == two) || (c[36] == one && c[56] == two)) { mS(c, "U B U' B' U' R' U R "); }
        if((c[14] == one && c[54] == two) || (c[54] == one && c[14] == two)) { mS(c, "U' B' U B U L U' L' "); }
        return state8thru11(c, s, one, two);
    }
    public char [] state12(char [] c)
    {
        if(c[2] == 'B' && c[4] == 'B' && c[6] == 'B' && c[8] == 'B') { System.out.printf("\tState 12 solved\n"); return c; }
        if((c[2] != 'B' && c[4] != 'B' && c[6] != 'B' && c[8] != 'B') || (c[2] != 'B' && c[4] == 'B' && c[6] == 'B' && c[8] != 'B')) { mS(c, "F R U R' U' F' "); }
        if(c[2] == 'B' && c[4] == 'B' && c[6] != 'B' && c[8] != 'B') { mS(c, "F U R U' R' F' "); }
        if(c[2] == 'B' && c[4] != 'B' && c[6] == 'B' && c[8] != 'B') { mS(c, "U' "); }
        if(c[2] != 'B' && c[4] != 'B' && c[6] == 'B' && c[8] == 'B') { mS(c, "U2 "); }
        if((c[2] != 'B' && c[4] == 'B' && c[6] != 'B' && c[8] == 'B') || (c[2] == 'B' && c[4] != 'B' && c[6] != 'B' && c[8] == 'B')) { mS(c, "U "); }
        return state12(c);
    }
    public char [] state13advanced(char [] c)
    {
        if((c[1] == 'B' && c[13] == 'B' && c[33] == 'B' && c[23] == 'B') || (c[3] == 'B' && c[11] == 'B' && c[31] == 'B' && c[21] == 'B') || (c[9] == 'B' && c[7] == 'B' && c[11] == 'B' && c[33] == 'B') || (c[3] == 'B' && c[9] == 'B' && c[11] == 'B' && c[13] == 'B') || (c[3] == 'B' && c[7] == 'B' && c[11] == 'B' && c[23] == 'B') || (c[13] == 'B' && c[57] == 'B' && c[59] == 'B' && c[31] == 'B') || (c[11] == 'B' && c[13] == 'B' && c[31] == 'B' && c[33] == 'B')) { Y(c, 3);  System.out.printf("Y' "); }
        if((c[3] == 'B' && c[13] == 'B' && c[57] == 'B' && c[23] == 'B') || (c[9] == 'B' && c[11] == 'B' && c[59] == 'B' && c[21] == 'B') || (c[7] == 'B' && c[1] == 'B' && c[59] == 'B' && c[23] == 'B') || (c[9] == 'B' && c[7] == 'B' && c[57] == 'B' && c[59] == 'B') || (c[1] == 'B' && c[9] == 'B' && c[59] == 'B' && c[13] == 'B') || (c[57] == 'B' && c[31] == 'B' && c[33] == 'B' && c[21] == 'B')) { Y(c, 2);  System.out.printf("Y2 "); }
        if((c[9] == 'B' && c[13] == 'B' && c[33] == 'B' && c[57] == 'B') || (c[7] == 'B' && c[11] == 'B' && c[31] == 'B' && c[59] == 'B') || (c[1] == 'B' && c[3] == 'B' && c[13] == 'B' && c[31] == 'B') || (c[7] == 'B' && c[1] == 'B' && c[31] == 'B' && c[33] == 'B') || (c[3] == 'B' && c[7] == 'B' && c[57] == 'B' && c[31] == 'B') || (c[11] == 'B' && c[21] == 'B' && c[23] == 'B' && c[33] == 'B')) { Y(c, 1);  System.out.printf("Y "); }
        if(c[1] == 'B' && c[3] == 'B' && c[7] == 'B' && c[9] == 'B') { System.out.printf("\tState 13 solved\n"); return c; } //PLL case
        if(c[7] == 'B' && c[57] == 'B' && c[33] == 'B' && c[23] == 'B') { mS(c, "R U R' U R U2 R' "); } //Sune logic  (bottom left block facing you)
        if(c[1] == 'B' && c[59] == 'B' && c[31] == 'B' && c[21] == 'B') { mS(c, "R' U' R U' R' U2 R "); } //Antisune
        if(c[3] == 'B' && c[9] == 'B' && c[57] == 'B' && c[21] == 'B') { mS(c, "L F R' F' L' F R F' "); } //T (make the T face left)
        if(c[1] == 'B' && c[3] == 'B' && c[21] == 'B' && c[23] == 'B') { mS(c, "R2 D R' U2 R D' R' U2 R' "); } //U
        if(c[1] == 'B' && c[9] == 'B' && c[21] == 'B' && c[33] == 'B') { mS(c, "R' F R B' R' F' R B "); } //L (Make left front face you)
        if(c[59] == 'B' && c[11] == 'B' && c[13] == 'B' && c[23] == 'B') { mS(c, "R U2 R2 U' R2 U' R2 U2 R "); } //P (pi faces left)
        if(c[21] == 'B' && c[23] == 'B' && c[57] == 'B' && c[59] == 'B') { mS(c, "R U2 R' U' R U R' U' R U' R' "); } //H (H facing you)
        return state13advanced(c);
    }
    public char [] state14advanced(char [] c)
    {
        if((c[21] == c[23]) && (c[11] == c[13]) && (c[57] == c[59]) && (c[31] == c[33])) { System.out.printf("\tState 14 solved\n"); return c; }
        if(c[57] == c[59] || ((c[21] != c[23]) && (c[11] != c[13]) && (c[57] != c[59]) && (c[31] != c[33]))) { mS(c, "R' F R' B2 R F' R' B2 R2 "); }
        else if(c[31] == c[33]) { Y(c, 3);  System.out.printf("Y' "); }
        else if(c[21] == c[23]) { Y(c, 2);  System.out.printf("Y2 "); }
        else if(c[11] == c[13]) { Y(c, 1);  System.out.printf("Y "); }
        return state14advanced(c);
    }
    public char [] state15advanced(char [] c)
    {
        if((c[21] == c[22]) && (c[11] == c[12]) && (c[57] == c[58]) && (c[31] == c[32]))
        {
            int Ucount = 0;
            if(c[22] != c[25])
            {
                moveCount++;
            }
            while(c[22] != c[25])
            {
                up(c, 1);
                Ucount++;
            }
            switch(Ucount)
            {
                case 1:
                    System.out.printf("U ");
//                    move_sssss += " U";
                    break;
                case 2:
                    System.out.printf("U2 ");
//                    move_sssss += " U2";

                    break;
                case 3:
                    System.out.printf("U' ");
//                    move_sssss += " U'";
                    break;
            }
            System.out.printf("\tState 15 solved\n"); return c;
        }
        if(c[57] == c[58])
        {
            if(c[22] == c[11]) { mS(c, "R2 U R U R' U' R' U' R' U R' "); }
            else if(c[22] == c[31]) { mS(c, "R U' R U R U R U' R' U' R2 "); }
        }
        else if((c[11] == c[12]) || (c[57] == c[32])) { Y(c, 1);  System.out.printf("Y "); }
        else if(c[21] == c[22]) { Y(c, 2);  System.out.printf("Y2 "); }
        else if(c[31] == c[32]) { Y(c, 3);  System.out.printf("Y' "); }
        else if(c[57] == c[22]) { mS(c, "L2 R2 "); X(c, 2); mS(c, "U L2 R2 "); X(c, 2); mS(c, "U2 L2 R2 "); X(c, 2); mS(c, "U L2 R2 "); }
        else if(c[57] == c[12]) { mS(c, "L2 R2 "); X(c, 2); mS(c, "U L2 R2 "); X(c, 2); mS(c, "U L R' "); X(c, 1); mS(c, "U2 L2 R2 "); X(c, 2); mS(c, "U2 L R' "); X(c, 1); mS(c, "U2 "); }
        return state15advanced(c);
    }

    public void mS(char [] c, String s) //Move Sequence
    {

        Scanner sc = new Scanner(s);
        int suboperation = 0;
        while(sc.hasNext())
        {
            String move = sc.next();
            suboperation = move.length();
            if(suboperation == 2)
            {
                if(move.charAt(1) == '\'')
                {
                    suboperation = 3;
                }
            }
            char m = move.charAt(0);
            String subop = "";
            if(suboperation == 2)
            {
                subop = "2";
            }
            if(suboperation == 3)
            {
                subop = "'";
            }
            switch(m)
            {
                case 'F':
                    front(c, suboperation);
                    System.out.printf("F%s ", subop);
//                    move_sssss += " F" + subop;
                    break;
                case 'R':
                    right(c, suboperation);
                    System.out.printf("R%s ", subop);
//                    move_sssss += " R" + subop;
                    break;
                case 'U':
                    up(c, suboperation);
                    System.out.printf("U%s ", subop);
//                    move_sssss += " U" + subop;
                    break;
                case 'L':
                    left(c, suboperation);
                    System.out.printf("L%s ", subop);
//                    move_sssss += " L" + subop;
                    break;
                case 'D':
                    down(c, suboperation);
                    System.out.printf("D%s ", subop);
//                    move_sssss += " D" + subop;
                    break;
                case 'B':
                    back(c, suboperation);
                    System.out.printf("B%s ", subop);
//                    move_sssss += " B" + subop;
                    break;
            }
            moveCount++;
        }
    }
    public void shuffleCube(char [] c, int length)
    {
        System.out.printf("Shuffle: ");     // "U' F' L2 R"
//        up(c, 3);
//        front(c, 3);
//        left(c, 2);
//        right(c, 3);
//        return;
        int l = length;
        int operation = 0;
        while(length > 0)
        {
            Random rand = new Random(100);
            if(l == length)
            {
                operation += rand.nextInt(6) + 1;
            }
            else
            {
                operation += rand.nextInt(5) + 1;
            }
            operation = operation % 6;
            Random r = new Random();
            int suboperation = r.nextInt(3) + 1;
            String subop = "";
            if(suboperation == 2)
            {
                subop = "2";
            }
            if(suboperation == 3)
            {
                subop = "'";
            }
            switch(operation)
            {
                case 0:
                    front(c, suboperation);
                    System.out.printf("F%s ", subop);
                    break;
                case 1:
                    right(c, suboperation);
                    System.out.printf("R%s ", subop);
                    break;
                case 2:
                    up(c, suboperation);
                    System.out.printf("U%s ", subop);
                    break;
                case 3:
                    left(c, suboperation);
                    System.out.printf("L%s ", subop);
                    break;
                case 4:
                    down(c, suboperation);
                    System.out.printf("D%s ", subop);
                    break;
                case 5:
                    back(c, suboperation);
                    System.out.printf("B%s ", subop);
                    break;
            }
            length--;
        }
        System.out.printf("\n\n");
    }
    public void swap(char [] cube, int a, int b, int c, int d)
    {
        //cube[0] is used as a temp variable
        cube[0] = cube[a];
        cube[a] = cube[d];
        cube[d] = cube[c];
        cube[c] = cube[b];
        cube[b] = cube[0];
    }
    public void front(char [] c, int i)
    {
        move_sssss += " F";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            swap(c, 7, 31, 43, 19);
            swap(c, 8, 34, 42, 16);
            swap(c, 9, 37, 41, 13);
            swap(c, 21, 23, 29, 27);
            swap(c, 22, 26, 28, 24);
            c[0] = 'N';
            i--;
        }
    }
    public void right(char [] c, int i)
    {
        move_sssss += " R";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            swap(c, 23, 3, 53, 43);
            swap(c, 26, 6, 56, 46);
            swap(c, 29, 9, 59, 49);
            swap(c, 31, 33, 39, 37);
            swap(c, 32, 36, 38, 34);
            c[0] = 'N';
            i--;
        }
    }
    public void up(char [] c, int i)
    {
        move_sssss += " U";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            swap(c, 21, 11, 59, 31);
            swap(c, 22, 12, 58, 32);
            swap(c, 23, 13, 57, 33);
            swap(c, 1, 3, 9, 7);
            swap(c, 2, 6, 8, 4);
            c[0] = 'N';
            i--;
        }
    }
    public void left(char [] c, int i)
    {
        move_sssss += " L";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            swap(c, 21, 41, 51, 1);
            swap(c, 24, 44, 54, 4);
            swap(c, 27, 47, 57, 7);
            swap(c, 11, 13, 19, 17);
            swap(c, 12, 16, 18, 14);
            c[0] = 'N';
            i--;
        }
    }
    public void down(char [] c, int i)
    {
        move_sssss += " D";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            swap(c, 27, 37, 53, 17);
            swap(c, 28, 38, 52, 18);
            swap(c, 29, 39, 51, 19);
            swap(c, 41, 43, 49, 47);
            swap(c, 42, 46, 48, 44);
            c[0] = 'N';
            i--;
        }
    }
    public void back(char [] c, int i)
    {
        move_sssss += " B";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            swap(c, 1, 17, 49, 33);
            swap(c, 2, 14, 48, 36);
            swap(c, 3, 11, 47, 39);
            swap(c, 51, 53, 59, 57);
            swap(c, 52, 56, 58, 54);
            c[0] = 'N';
            i--;
        }
    }
    public void X(char [] c, int i)
    {
        move_sssss += " X";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            right(c, 1);
            left(c, 3);
            swap(c, 22, 2, 52, 42);
            swap(c, 25, 5, 55, 45);
            swap(c, 28, 8, 58, 48);
            c[0] = 'N';
            i--;
        }
    }
    public void Y(char [] c, int i)
    {
        move_sssss += " Y";
        if (i > 1) move_sssss += fuck.charAt(i);
        while(i > 0)
        {
            up(c, 1);
            down(c, 3);
            swap(c, 24, 14, 56, 34);
            swap(c, 25, 15, 55, 35);
            swap(c, 26, 16, 54, 36);
            c[0] = 'N';
            i--;
        }
    }
}

