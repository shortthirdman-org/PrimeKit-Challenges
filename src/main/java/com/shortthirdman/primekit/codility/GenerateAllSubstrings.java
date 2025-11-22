package com.shortthirdman.primekit.codility;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllSubstrings {

    /**
     * @param inputString the input string
     * @param curr the current string
     * @param startPos the start position
     * @param glRes the resultant list
     * @return List of substrings
     */
    public List<String> generateSubStrings(String inputString, String curr, int startPos, List<String> glRes) {
        if (startPos >= inputString.length()) {
            List<String> currList = new ArrayList<>();
            currList.add(curr);
            return currList;
        }

        List<String> subStringChars = generateSubStrings(inputString, "" + inputString.charAt(startPos), startPos + 1, glRes);

        List<String> result = new ArrayList<>();
        result.add("" + inputString.charAt(startPos));

        if (startPos < inputString.length() - 1) {
            for (String sub : subStringChars) {
                result.add(inputString.charAt(startPos) + sub);
            }
        }

        glRes.addAll(result);
        return result;
    }

//    public void generateSubstrings(String inputString, int startPos, List<String> res){
//        if (startPos >= inputString.length()) {
//            return;
//        }
//
//        for (int j = startPos; j < inputString.length(); j++) {
//            res.add(inputString.substring(startPos, j + 1));
//        }
//
//        generateSubstrings(inputString, startPos + 1, res);
//    }
}
