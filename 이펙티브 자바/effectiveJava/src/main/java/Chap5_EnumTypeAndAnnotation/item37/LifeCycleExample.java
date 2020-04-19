package Chap5_EnumTypeAndAnnotation.item37;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LifeCycleExample {
    public static void main(String[] args) {
        Plant corn = new Plant("옥수수", LifeCycle.ANNUAL);
        Plant pea = new Plant("완두", LifeCycle.ANNUAL);
        Plant potato = new Plant("감자", LifeCycle.PERNNIAL);
        Plant alceaRosea = new Plant("접시꽃", LifeCycle.BIENNIAL);

        List<Plant> garden = Arrays.asList(corn, alceaRosea, pea);

//        usingOrdinalArray(garden);
        System.out.println("==========================");
        usingEnumMap(garden);
//        System.out.println("==========================");
//        streamV1(garden);
        System.out.println("==========================");
        streamV2(garden);
    }

    public static void usingOrdinalArray(List<Plant> garden) {
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant plant : garden) {
            plantsByLifeCycle[plant.lifeCycle.ordinal()].add(plant);
        }

        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s : %s%n",
                    LifeCycle.values()[i], plantsByLifeCycle[i]);
        }
    }

    public static void usingEnumMap(List<Plant> garden) {
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);

        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        //EnumMap은 toString을 재정의하였다.
        System.out.println("EnumMap을 직접 사용");
        System.out.println(plantsByLifeCycle);
    }

    public static void streamV1(List<Plant> garden) {
        Map plantsByLifeCycle = garden.stream().collect(Collectors.groupingBy(plant -> plant.lifeCycle));
        System.out.println(plantsByLifeCycle);
    }

    public static void streamV2(List<Plant> garden) {
        Map plantsByLifeCycle = garden.stream()
                .collect(Collectors.groupingBy(plant -> plant.lifeCycle,
                        () -> new EnumMap<>(LifeCycle.class), Collectors.toSet()));
        System.out.println("스트림 사용 Map");
        System.out.println(plantsByLifeCycle);
    }
}

//System.out.println(plantsByLifeCycle.get(LifeCycle.ANNUAL).getClass());;
//        System.out.println(plantsByLifeCycle.getClass());
