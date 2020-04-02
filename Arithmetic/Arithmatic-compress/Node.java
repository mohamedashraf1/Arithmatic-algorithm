/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 *
 * @author Elliot
 */
public class Node {
    double low;
    double high;
    double lowerRange;
    double highRange;
    String symbol;
    double freq;

    public Node() {
    }

    public Node(double low, double high, double lowerRange, double highRange, String symbol, double freq) {
        this.low = low;
        this.high = high;
        this.lowerRange = lowerRange;
        this.highRange = highRange;
        this.symbol = symbol;
        this.freq = freq;
    }

    public double getFreq() {
        return freq;
    }

    public double getHigh() {
        return high;
    }

    public double getHighRange() {
        return highRange;
    }

    public double getLow() {
        return low;
    }

    public double getLowerRange() {
        return lowerRange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setHighRange(double highRange) {
        this.highRange = highRange;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setLowerRange(double lowerRange) {
        this.lowerRange = lowerRange;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

   
    
    
}
