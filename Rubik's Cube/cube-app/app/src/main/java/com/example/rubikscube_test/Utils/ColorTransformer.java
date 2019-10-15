package com.example.rubikscube_test.Utils;

public class ColorTransformer {
    public static String transCubestring(String cubeString) {
        StringBuilder stringBuilder = new StringBuilder(cubeString);
        String charToNum = "UDFBLR";

        // up
        for (int i = 0, j = 2; i < 3; i++, j--)
            for (int k = 0; k < 3; k++)
                stringBuilder.setCharAt(j * 3 + k, cubeString.charAt(i * 3 + k));
//        // down
        for (int l = 0, j = 3; l < 3; l++, j++)
            for (int k = 0, i = 9; i < 12; k++, i++)
                stringBuilder.setCharAt(j * 3 + k, cubeString.charAt(i * 3 + l));
//        // front
        for (int l = 0, j = 6; l < 3; l++, j++)
            for (int k = 0, i = 6; k < 3; k++, i++)
                stringBuilder.setCharAt(j * 3 + k, cubeString.charAt(i * 3 + l));
        // back
        for (int l = 0, j = 9; l < 3; l++, j++)
            for (int k = 0, i = 15; k < 3; k++, i++)
                stringBuilder.setCharAt(j * 3 + k, cubeString.charAt(i * 3 + l));
        // left
        for (int i = 12, j = 12; j < 15; j++, i++)
            for (int k = 0, l = 2; k < 3; l--, k++)
                stringBuilder.setCharAt(j * 3 + k, cubeString.charAt(i * 3 + l));
//        // right
        for (int j = 15, l = 0; l < 3; l++, j++)
            for (int k = 0, i = 3; k < 3; k++, i++)
                stringBuilder.setCharAt(j * 3 + k, cubeString.charAt(i * 3 + l));

        for (int i = 0; i < stringBuilder.length(); i++)
            stringBuilder.setCharAt(i, (char) ('0' + charToNum.indexOf(stringBuilder.charAt(i))));
        return stringBuilder.toString();
    }
}
