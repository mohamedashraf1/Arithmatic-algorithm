/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

// head first design pattern
import java.util.Vector;

public class Arithmetic {

    static Vector<Node> myChars = new Vector();

    static void getProb(String word) {//take the word and get the prob of each char and save them in myChars
        Vector<String> chars = new Vector();
        Vector<Integer> freq = new Vector();

        for (int i = 0; i < word.length(); i++) {//ACBA
            if (chars.contains(word.charAt(i) + "")) {//is the char in? if yes
                int indx = 0;
                for (int j = 0; j < chars.size(); j++) {//chars = A B C
                    if (chars.elementAt(j).equals(word.charAt(i) + "")) {
                        indx = j;
                        break;
                    }
                }

                freq.set(indx, freq.elementAt(indx) + 1);
            } else {//if no
                chars.add(word.charAt(i) + "");
                freq.add(1);

            }
        }

        for (int i = 0; i < chars.size(); i++) {
            Node tmp = new Node();
            tmp.setSymbol(chars.elementAt(i));
            tmp.setFreq((double) freq.elementAt(i) / (double) word.length());
            myChars.add(tmp);
        }
    }

    static Vector<Node> Sort(Vector<Node> arr) {//sort myChars alphabetically

        int i, j;
        for (i = 0; i < arr.size() - 1; i++) {//bubble sort
            for (j = 0; j < arr.size() - i - 1; j++) {
                if ((int) arr.elementAt(j).getSymbol().charAt(0) > (int) arr.elementAt(j + 1).getSymbol().charAt(0)) {
                    double lowTmp;
                    double highTmp;
                    double lowerRangeTmp;
                    double highRangeTmp;
                    String symbolTmp;
                    double freqTmp;

                    //swap arr[j] and arr[j+1]
                    lowTmp = arr.elementAt(j).getLow();
                    arr.elementAt(j).setLow(arr.elementAt(j + 1).getLow());
                    arr.elementAt(j + 1).setLow(lowTmp);

                    highTmp = arr.elementAt(j).getHigh();
                    arr.elementAt(j).setHigh(arr.elementAt(j + 1).getHigh());
                    arr.elementAt(j + 1).setHigh(highTmp);

                    lowerRangeTmp = arr.elementAt(j).getLowerRange();
                    arr.elementAt(j).setLowerRange(arr.elementAt(j + 1).getLowerRange());
                    arr.elementAt(j + 1).setLowerRange(lowerRangeTmp);

                    highRangeTmp = arr.elementAt(j).getHighRange();
                    arr.elementAt(j).setHighRange(arr.elementAt(j + 1).getHighRange());
                    arr.elementAt(j + 1).setHighRange(highRangeTmp);

                    symbolTmp = arr.elementAt(j).getSymbol();
                    arr.elementAt(j).setSymbol(arr.elementAt(j + 1).getSymbol());
                    arr.elementAt(j + 1).setSymbol(symbolTmp);

                    freqTmp = arr.elementAt(j).getFreq();
                    arr.elementAt(j).setFreq(arr.elementAt(j + 1).getFreq());
                    arr.elementAt(j + 1).setFreq(freqTmp);

                }
            }
        }
        return arr;
    }

    static void saveLowHighRange(Vector<Node> arr) {//save the low_range and high_range of each node in myChar
        for (int i = 0; i < arr.size(); i++) {
            if (i == 0) {
                //set lower and higher range
                arr.elementAt(0).setLowerRange(0);
                arr.elementAt(0).setHighRange(arr.elementAt(0).getFreq());
                //set low and high of the first time
                arr.elementAt(0).setLow(0);
                arr.elementAt(0).setHigh(arr.elementAt(0).getFreq());
            } else {
                //set lower and higher range
                arr.elementAt(i).setLowerRange(arr.elementAt(i - 1).getHighRange());
                arr.elementAt(i).setHighRange(arr.elementAt(i - 1).getHighRange() + arr.elementAt(i).getFreq());
                //set low and high of the first time
                arr.elementAt(i).setLow(arr.elementAt(i).getLowerRange());
                arr.elementAt(i).setHigh(arr.elementAt(i).getHighRange());
            }
        }
    }

    static void updateLowHigh(double l, double h) {
        double range = h - l;
        for (int i = 0; i < myChars.size(); i++) {
            myChars.elementAt(i).setLow(l + range * myChars.elementAt(i).getLowerRange());
            myChars.elementAt(i).setHigh(l + range * myChars.elementAt(i).getHighRange());
        }
    }

    static double Compress(String word) {
        double lower = 0;
        double higher = 1;
        double compressValue = 0;

        getProb(word);
        myChars = Sort(myChars);
        saveLowHighRange(myChars);

        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < myChars.size(); j++) {
                if (myChars.elementAt(j).getSymbol().equals(word.charAt(i) + "")) {//search the char in myChar
                    lower = myChars.elementAt(j).getLow();
                    higher = myChars.elementAt(j).getHigh();
                    break;
                }
            }
            updateLowHigh(lower, higher);
        }
        compressValue = (lower + higher) / 2;
        return compressValue;
    }

    static String Decompress(double compressCode, int length) {
        double code = compressCode;
        double lower = 0;
        double higher = 1;
        String myword = "";

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < myChars.size(); j++) {
                if (myChars.elementAt(j).getLowerRange() < code && myChars.elementAt(j).getHighRange() > code) {
                    myword += myChars.elementAt(j).getSymbol();
                    lower = myChars.elementAt(j).getLowerRange();
                    higher = myChars.elementAt(j).getHighRange();
                    code = (code - lower) / (higher - lower);
                    break;
                }
            }
        }

        return myword;
    }

}
