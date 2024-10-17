package com.poppulo.lottery.dto;

import java.util.List;

public class TicketRequest {
    private int numberOfLines;
    private List<String> lines;

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}