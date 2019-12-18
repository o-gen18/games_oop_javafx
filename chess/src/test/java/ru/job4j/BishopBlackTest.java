package ru.job4j;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {
    @Test
    public void checkPositionMethod() {
         BishopBlack bishop = new BishopBlack(Cell.C8);
         assertThat(bishop.position(), is(Cell.C8));
    }
    @Test
    public void checkCopyMethod() {
        BishopBlack bishop = new BishopBlack(Cell.F8);
        assertThat(bishop.copy(Cell.G7).position(), is(Cell.G7));
    }
    @Test
    public void checkWayMethod() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
       Cell[] check = bishop.way(bishop.position(), Cell.G5);
        Cell[] result = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(check, is(result));
    }
    @Test
    public void checkWayMethod2() {
        BishopBlack bishop = new BishopBlack(Cell.D4);
        Cell[] check = bishop.way(bishop.position(), Cell.A7);
        Cell[] result = {Cell.C5, Cell.B6, Cell.A7};
        assertThat(check, is(result));
    }
    @Test(expected = IllegalStateException.class)
    public void checkIsDiagonalMethodWhenNotDiagonalMove() {
        BishopBlack bishop = new BishopBlack(Cell.C8);
        bishop.way(bishop.position(), Cell.F4);
    }
}
