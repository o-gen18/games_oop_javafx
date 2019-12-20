package ru.job4j;
import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void checkFindByMethod() {
        BishopBlack bishop = new BishopBlack(Cell.F8);
        Logic figure = new Logic();
        figure.add(bishop);
        assertThat(figure.move(bishop.position(), Cell.A3), is(true));
    }
    @Test
    public void whenFindByDoesNotFound() {
        BishopBlack bishop = new BishopBlack(Cell.G2);
        Logic figure = new Logic();
        figure.add(bishop);
        boolean result = figure.move(Cell.D2, Cell.A5);
        assertThat(result, is(false));
    }
    @Test
    public void whenAnotherFigureStandsOnTheWay() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(out));
        BishopBlack bishop = new BishopBlack(Cell.G2);
        BishopBlack bishop2 = new BishopBlack(Cell.B7);
        Logic figure = new Logic();
        figure.add(bishop);
        figure.add(bishop2);
        figure.move(bishop.position(), bishop2.position());
        assertThat(out.toString(), is("Cannot go on that square!" + System.lineSeparator()));
        System.setOut(stdout);
    }
    @Test
    public void whenMoveSuccess() {
        BishopBlack bishop = new BishopBlack(Cell.B1);
        Logic figure = new Logic();
        figure.add(bishop);
        assertThat(figure.move(bishop.position(), Cell.H7), is(true));
    }
}
