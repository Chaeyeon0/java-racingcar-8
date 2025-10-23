package racingcar.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    //쉼표로 구분된 이름 문자열을 받아서 Car 리스트로 변환
    public Cars(String namesInput) {
        this.cars = Arrays.stream(namesInput.split(","))
                .map(String::trim)
                .map(Car::new)  // Car 생성자에서 이름 검증 수행
                .collect(Collectors.toList());
    }

    //모든 자동차를 한 번씩 이동
    public void moveAll(MovementStrategy strategy) {
        cars.forEach(car -> car.move(strategy));
    }

    // 자동차 리스트를 반환
    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    //가장 멀리 간 자동차(들) 반환
    public List<Car> findWinners() {
        int maxDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .collect(Collectors.toList());
    }

    public void printStatus() {
        cars.forEach(System.out::println);
        System.out.println();
    }
}
