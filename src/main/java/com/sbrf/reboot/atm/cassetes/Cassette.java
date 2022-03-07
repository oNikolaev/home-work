package com.sbrf.reboot.atm.cassetes;

import java.util.ArrayList;

public class Cassette<Banknote> {
    final private ArrayList<Banknote> banknotes;

    public Cassette(ArrayList<Banknote> banknotes) {
        this.banknotes = banknotes;
    }

    public int getCountBanknotes() {
        return banknotes.size();
    }
}
