package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.List;

@Disabled
public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenTryToBuyOccupiedPlace() {
        Account account1 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account1, 1, 1, date);
        Account account2 = new AccountCinema();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account2, 1, 1, date);
        });
    }

    @Test
    public void whenDoesNotBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isNotEqualTo(new Ticket3D());
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenDoesNotFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNotNull();
    }

    @Test()
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    @Test()
    public void whenInvalidAccount() {
        Account account = new AccountCinemaWorker();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, date);
        });
    }

    @Test()
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(1900, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }
}

