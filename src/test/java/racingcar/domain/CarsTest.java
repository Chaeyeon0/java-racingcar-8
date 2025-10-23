package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {

    @Test
    @DisplayName("쉼표로 구분된 자동차 이름 문자열로 Car 객체들을 생성한다")
    void createCarsFromCommaSeparatedNames() {
        Cars cars = new Cars("pobi, jun, woni");

        List<Car> carList = cars.getCars();
        assertThat(carList).hasSize(3);
        assertThat(carList)
                .extracting(Car::getName)
                .containsExactly("pobi", "jun", "woni");
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외를 발생시킨다")
    void throwsExceptionWhenAnyNameExceedsFiveCharacters() {
        assertThatThrownBy(() -> new Cars("abcdef, jun, woni"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @Test
    @DisplayName("모든 자동차를 한 번씩 이동시킬 수 있다")
    void moveAllCarsOnce() {
        Cars cars = new Cars("pobi, jun, woni");

        // 항상 이동하는 전략
        cars.moveAll(new MovementStrategy() {
            @Override
            public boolean isMovable() {
                return true;
            }
        });

        assertThat(cars.getCars())
                .extracting(Car::getDistance)
                .containsExactly(1, 1, 1);
    }

    @Test
    @DisplayName("가장 멀리 이동한 자동차가 단독 우승자가 된다")
    void findSingleWinner() {
        Cars cars = new Cars("pobi, jun, woni");

        // pobi 두 번 이동, jun 한 번 이동, woni 정지
        cars.getCars().get(0).move(() -> true);
        cars.getCars().get(0).move(() -> true);
        cars.getCars().get(1).move(() -> true);
        cars.getCars().get(2).move(() -> false);

        List<Car> winners = cars.findWinners();
        assertThat(winners)
                .extracting(Car::getName)
                .containsExactly("pobi");
    }

    @Test
    @DisplayName("가장 멀리 이동한 자동차가 여러 명이면 공동 우승자가 된다")
    void findMultipleWinners() {
        Cars cars = new Cars("pobi, jun, woni");

        // pobi와 jun을 동일하게 한 칸씩 이동
        cars.getCars().get(0).move(() -> true);
        cars.getCars().get(1).move(() -> true);

        List<Car> winners = cars.findWinners();
        assertThat(winners)
                .extracting(Car::getName)
                .containsExactlyInAnyOrder("pobi", "jun");
    }
}
