package com.example.arithmetic.linear;

public class YueSeFuHuan {
    private Entry header = new Entry(1, null);

    public YueSeFuHuan(int n) {
        initLink(n);
    }

    private void initLink(int n) {
        Entry lastEntry = header;
        for (int i = 2; i <= n; i++) {
            Entry entry = new Entry(i, null);
            lastEntry.next = entry;
            lastEntry = entry;
        }
        lastEntry.next = header;
    }

    public void iterator() {
        Entry entry = header;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            stringBuilder.append(String.valueOf(entry.number) + ",");
            entry = entry.next;
        } while (entry.next.number != header.number);

        stringBuilder.append(String.valueOf(entry.number));
        System.out.println(stringBuilder.toString());
    }

    public Entry get(int index) {
        Entry entry = header;
        while (entry.number != index) {
            entry = entry.next;
        }
        return entry;
    }

    public void start(int startIndex, int m) {
        StringBuilder stringBuilder = new StringBuilder();
        Entry entry = get(startIndex);
        Entry lastEntry = entry;
        int count = 1;
        while (entry != entry.next) {
            if (count == m) {
                stringBuilder.append(String.valueOf(entry.number));
                lastEntry.next = entry.next;
                entry = entry.next;
                count = 1;
            } else {
                lastEntry = entry;
                entry = entry.next;
                count++;
            }
        }
        stringBuilder.append(String.valueOf(entry.number));
        System.out.println(stringBuilder.toString());


    }


    private class Entry {
        int number;
        Entry next;

        Entry(int number, Entry next) {
            this.next = next;
            this.number = number;
        }
    }


}
