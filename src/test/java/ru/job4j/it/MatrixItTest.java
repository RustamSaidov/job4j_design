package ru.job4j.it;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class MatrixItTest {

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext(), is(false));
    }
    @Test
    public void whenMultiHashNext1() {
        int[][] in = {
                {1}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenMultiHashNext12() {
        int[][] in = {
                {1,2}, {1,2}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
    }



    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1,11, 111}, {2, 5}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(11));
        assertThat(it.next(), is(111));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(5));
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }



//    @Test(expected = NoSuchElementException.class)
//    public void whenEmptyThenNext() {
//        int[][] in = {
//                {}
//        };
//        MatrixIt it = new MatrixIt(in);
//        it.next();
//    }









//    @Test
//    public void when4El() {
//        int[][] in = {
//                {1}
//        };
//        MatrixIt it = new MatrixIt(in);
//        assertThat(it.next(), is(1));
//    }

//    @Test
//    public void when4El() {
//        int[][] in = {{1,2, 22}, {3,4}, {1,1}};
//        MatrixIt it = new MatrixIt(in);
//        assertThat(it.next(), is(1));
//        assertThat(it.next(), is(2));
//        assertThat(it.next(), is(22));
//        assertThat(it.next(), is(3));
//        assertThat(it.next(), is(4));
//        assertThat(it.next(), is(1));
//        assertThat(it.next(), is(1));
//    }

}