package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.KnightBlack;
import ru.job4j.chess.firuges.white.KnightWhite;

import java.util.Arrays;
import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index = this.findBy(source);
            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = this.wayIsFree(this.figures[index], steps);
                    this.figures[index] = this.figures[index].copy(dest);
                }
            }
        } catch (Exception e) {
            System.out.println("Cannot go on that square!");
        }
        return rst;
    }



    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    private boolean wayIsFree(Figure figure, Cell[] steps) {
        if ( !(figure instanceof KnightBlack) && !(figure instanceof KnightWhite) ) {
            for (int i = 0; i < steps.length; i++) {
                for (int k = 0; k != this.figures.length; k++) {
                    if (this.figures[k].position().equals(steps[i])) {
                        throw new IllegalStateException(String.format("The way is busy on the square %s", this.figures[k].position()));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }
}
