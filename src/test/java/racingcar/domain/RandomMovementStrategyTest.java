package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomMovementStrategyTest {

    @Test
    @DisplayName("랜덤 전략은 0 이상 9 이하의 숫자 중 4 이상일 때 전진한다")
    void shouldMoveReturnsTrueWhenRandomNumberIsFourOrGreater() {
        RandomMovementStrategy strategy = new RandomMovementStrategy();

        // 여러 번 실행하여 true가 나올 수 있는지 확인
        boolean canMove = false;
        for (int i = 0; i < 100; i++) {
            if (strategy.shouldMove()) {
                canMove = true;
                break;
            }
        }

        assertThat(canMove).isTrue();
    }

    @RepeatedTest(5)
    @DisplayName("랜덤 전략은 4 미만일 수도 있어 멈출 수 있다")
    void shouldMoveCanReturnFalseWhenRandomNumberIsLessThanFour() {
        RandomMovementStrategy strategy = new RandomMovementStrategy();

        boolean stopped = false;
        for (int i = 0; i < 100; i++) {
            if (!strategy.shouldMove()) {
                stopped = true;
                break;
            }
        }
        assertThat(stopped).isTrue();
    }
}
