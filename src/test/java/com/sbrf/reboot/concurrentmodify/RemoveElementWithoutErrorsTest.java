package com.sbrf.reboot.concurrentmodify;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {
    private List<Integer> list;
    final int indexToRemove = 1;

    @BeforeEach
    public void beforeEachTest() {
        list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};
    }

    @Test
    public void successConcurrentModificationException() {
        assertThrows(ConcurrentModificationException.class, () -> {
            for (Integer integer : list) {
                list.remove(indexToRemove);
            }
        });

    }

    @Test
    public void successRemoveElementNoException() {
        assertDoesNotThrow(() -> {
            list.remove(indexToRemove);
        });
    }

    @Test
    public void successRemoveElementNoExceptionUseIterator() {
        assertDoesNotThrow(() -> {
            int index = 0;
            for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); index++) {
                iterator.next();
                if (index == indexToRemove) {
                    iterator.remove();
                }
            }
        });
    }

}
