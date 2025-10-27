package racingcar.domain;

@FunctionalInterface
public interface MovementStrategy {
    boolean shouldMove();
}
