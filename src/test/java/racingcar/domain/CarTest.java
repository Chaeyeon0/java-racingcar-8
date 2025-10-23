package racingcar.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    void 이름이_5자를_초과하면_예외가_발생한다() {
        assertThatThrownBy(() -> new Car("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @Test
    void 이름이_비어있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Car(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어 있을 수 없습니다");
    }

    @Test
    void 이동조건이_참이면_전진한다() {
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
    void 이동조건이_거짓이면_정지한다() {
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
    void toString은_이름과_이동거리를_표시한다() {
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
