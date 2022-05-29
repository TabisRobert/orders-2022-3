package pl.edu.wszib.orders.api;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Either<L, R> {
    public static <L, R> Either<L, R> left(final L value) {
        return new Left<>(value);
    }


    public static <L, R> Either<L, R> right(final R value) {
        return new Right<>(value);
    }

    private Either() {
    }

    public abstract <T> T map(final Function<? super L, ? extends T> lFunc,
                              final Function<? super R, ? extends T> rFunc);

    public <T> Either<T, R> mapLeft(final Function<? super L, ? extends T> lFunc) {
        return this.map(t -> left(lFunc.apply(t)), t -> (Either<T, R>) this);
    }

    public <T> Either<L, T> mapRight(final Function<? super R, ? extends T> lFunc) {
        return this.map(t -> (Either<L, T>) this, t -> right(lFunc.apply(t)));
    }

    public void apply(final Consumer<? super L> lFunc,
                      final Consumer<? super R> rFunc) {
        map(consume(lFunc), consume(rFunc));
    }

    private <T> Function<T, Void> consume(final Consumer<T> c) {
        return t -> {
            c.accept(t);
            return null;
        };
    }

    public boolean isRight() {
        return this instanceof Either.Right<L, R>;
    }

    public R get() {
        if (isRight()) {
            final Either.Right<L, R> right = (Either.Right<L, R>) this;
            return right.value;
        }
        throw new IllegalStateException("Either.left = " + this);
    }

    private static class Left<L, R> extends Either<L, R> {
        private final L value;

        public Left(final L value) {
            this.value = value;
        }

        @Override
        public <T> T map(final Function<? super L, ? extends T> lFunc,
                         final Function<? super R, ? extends T> rFunc) {
            return lFunc.apply(value);
        }
    }

    private static class Right<L, R> extends Either<L, R> {
        private final R value;

        public Right(final R value) {
            this.value = value;
        }

        @Override
        public <T> T map(final Function<? super L, ? extends T> lFunc,
                         final Function<? super R, ? extends T> rFunc) {
            return rFunc.apply(value);
        }
    }
}

