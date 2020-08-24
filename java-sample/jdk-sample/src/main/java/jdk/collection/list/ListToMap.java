package jdk.collection.list;

//import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://www.concretepage.com/java/jdk-8/java-8-convert-list-to-map-using-collectors-tomap-example">
 * Java 8 Convert List to Map using Collectors.toMap() Example</a><br>
 * <a href="https://stackoverflow.com/questions/20363719/java-8-listv-into-mapk-v">Java 8 List into Map</a>
 */
public class ListToMap {

    public static void main(String[] args) {
        IdName idName1 = new IdName();
        idName1.setName("a");
        IdName idName2 = new IdName();
        idName2.setName("b");

        List<IdName> list = new ArrayList<>();
        list.add(idName1);
        list.add(idName1);
        list.add(idName2);

        ListToMap listToMap = new ListToMap();
        Map<String, IdName> map = listToMap.jdk8Lambda(list);
        System.out.println(map);
    }

    private Map<String, IdName> jdk7(List<IdName> IdNames) {
        Map<String, IdName> hashMap = new HashMap<>();
        for (IdName IdName : IdNames) {
            hashMap.put(IdName.getName(), IdName);
        }
        return hashMap;
    }

//    private Map<String, IdName> guavaLambda(List<IdName> IdNames) {
//        return Maps.uniqueIndex(IdNames, IdName::getName);
//    }

    private Map<String, IdName> jdk8Lambda(List<IdName> IdNames) {
        return IdNames.stream()
                .collect(Collectors.toMap(IdName::getName, Function.identity(), (x, y) -> y, LinkedHashMap::new));
    }


}
