package com.github.mauricioaniche.ck.metric;

import java.util.Stack;

public class Node {
    private int current = 0;
    private int max = 0;

    public Node(int current, int max) {
        this.current = current;
        this.max = max;
    }

    public void plusOne() {
        current++;
        max = Math.max(current, max);
    }

    public void popBlock(Stack<Boolean> stack) {
        Boolean pop = stack.pop();
        if(pop)
            current--;
    }

    public void addMax() {
        this.max++;
    }

    public void subtractMax() {
        this.max--;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
