package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외를 발생시킨다")
    void throwsExceptionWhenNameExceedsFiveCharacters() {
        assertThatThrownBy(() -> new Car("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @Test
    @DisplayName("자동차 이름이 비어있으면 예외를 발생시킨다")
    void throwsExceptionWhenNameIsBlank() {
        assertThatThrownBy(() -> new Car(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어 있을 수 없습니다");
    }

    @Test
    @DisplayName("이동 조건이 참일 경우 자동차는 전진한다")
    void movesForwardWhenStrategyReturnsTrue() {
        Car car = new Car("pobi");

        // 항상 이동하는 전략
        car.move(new MovementStrategy() {
            @Override
            public boolean isMovable() {
                return true;
            }
        });

        assertThat(car.getDistance()).isEqualTo(1);
    }

    @Test
    @DisplayName("이동 조건이 거짓일 경우 자동차는 정지한다")
    void stopsWhenStrategyReturnsFalse() {
        Car car = new Car("pobi");

        // 절대 이동하지 않는 전략
        car.move(new MovementStrategy() {
            @Override
            public boolean isMovable() {
                return false;
            }
        });

        assertThat(car.getDistance()).isZero();
    }

    @Test
    @DisplayName("toString은 자동차 이름과 이동 거리를 '-'로 표시한다")
    void toStringDisplaysNameAndDistance() {
        Car car = new Car("pobi");

        // 한 칸 이동시키기
        car.move(new MovementStrategy() {
            @Override
            public boolean isMovable() {
                return true;
            }
        });

        assertThat(car.toString()).isEqualTo("pobi : -");
    }
}
