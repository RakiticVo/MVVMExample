package com.example.mvvmexample.Model;

public class Player {
    public String name;
    public PlayerValue value;

    public Player(String name, PlayerValue value) {
        this.name = name;
        this.value = value;
    }

    // Hàm xây dựng theo giá trị String
    public Player(String name, String value) {
        this.name = name;
        if (value.equalsIgnoreCase("x")) {
            this.value = PlayerValue.VALUE_X;
        } else if (value.equalsIgnoreCase("o")) {
            this.value = PlayerValue.VALUE_O;
        } else {
            this.value = PlayerValue.VALUE_EMPTY;
        }
    }
    // Hàm xây dựng theo giá trị int
    public Player(String name, int value) {
        this.name = name;
        if (value == 0) {
            this.value = PlayerValue.VALUE_X;
        } else if (value == 1) {
            this.value = PlayerValue.VALUE_O;
        } else {
            this.value = PlayerValue.VALUE_EMPTY;
        }
    }
    // Tông hợp các hằng số của giá trị PlayerValue
    public enum PlayerValue {
        VALUE_EMPTY("VALUE_EMPTY"),
        VALUE_X("VALUE_X"),
        VALUE_O("VALUE_O");

        private String value = "";

        PlayerValue(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            String s = super.toString();
            if (s.equals("VALUE_X")) {
                return "X";
            }
            return "O";
        }
    }
}
