package exception;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NPESampleTest {

    @Test(expected = NullPointerException.class)
    public void NPEPosition() {
        List<String> innerList = new ArrayList<>();
        innerList.add(null);
        List<List<String>> outerList = new ArrayList<>();
        outerList.add(innerList);
        if (outerList
            .get(0)
            .get(0)
            .isEmpty()) {
            System.out.println("recognized NPE position in line 19.");
        }

        if (true
            && innerList.get(0).isEmpty()) {
            System.out.println("recognized NPE position in line 24.");
        }

        if (false
            || innerList.get(0).isEmpty()) {
            System.out.println("recognized NPE position in line 29.");
        }
    }

}