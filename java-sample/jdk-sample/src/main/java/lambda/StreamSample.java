package lambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSample {

    public static void main(String[] args) {
        StreamSample sample = new StreamSample();
        List<String> l1 = Arrays.asList("A", "B", "C");
        List<CharSequence> l2 = Arrays.asList("d", "e", "f");
        List<CharSequence> list1 = sample.merge1(l1, l2);
        System.out.println(list1);
        List<CharSequence> list2 = sample.merge2(l1, l2);
        System.out.println(list2);
    }

    <T, R extends T, S extends T> List<T> merge1(List<R> l1, List<S> l2) {
        return Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList());
    }

    <T, R extends T, S extends T> List<T> merge2(List<R> l1, List<S> l2) {
        return Stream.of(l1, l2).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
