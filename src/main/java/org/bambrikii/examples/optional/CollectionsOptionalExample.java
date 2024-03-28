package org.bambrikii.examples.optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionsOptionalExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        getString(list);
        getString(null);
    }

    private static void getString(List<String> list) {
        System.out.println("> " + Optional.ofNullable(list).orElseGet(Collections::emptyList).stream().collect(Collectors.joining()));
    }
}
