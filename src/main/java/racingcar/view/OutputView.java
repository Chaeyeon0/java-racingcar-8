package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {

    public static void printRoundResult(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.toString()));
        System.out.println();
    }

    public static void printWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
        System.out.println("최종 우승자 : " + winnerNames);
    }
}
