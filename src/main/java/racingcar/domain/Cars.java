package racingcar.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cars {

    private final List<Car> cars;

    //쉼표로 구분된 이름 문자열을 받아서 Car 리스트로 변환
    public Cars(String namesInput) {

        if (namesInput == null || namesInput.isBlank()) {
            throw new IllegalArgumentException("자동차 이름 입력이 비어 있습니다.");
        }

        List<String> names = Arrays.stream(namesInput.split(","))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .toList();

        if (names.isEmpty()) {
            throw new IllegalArgumentException("유효한 자동차 이름이 없습니다.");
        }

        this.cars = names.stream()
                .map(Car::new)
                .toList();
    }

    //모든 자동차를 한 번씩 이동
    public void moveAll(MovementStrategy strategy) {
        cars.forEach(car -> car.attemptMove(strategy));
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
                .orElseThrow();

        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .toList();
    }
}