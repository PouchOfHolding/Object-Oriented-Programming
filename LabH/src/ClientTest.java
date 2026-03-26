import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import java.util.InputMismatchException;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    public void shouldComputeTimeAndSpeedForHorizontalMovementNoWind() {
        String input = "0.0 0.0\n10.0 0.0\n0.0 0.0\n5.0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        try {
            Client.main();
            String output = out.toString();
            assertEquals("2.00\n5.00 0.00\n", output);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    public void shouldComputeTimeAndSpeedWithWind() {
        String input = "0.0 0.0\n10.0 0.0\n2.0 0.0\n5.0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        try {
            Client.main();
            String output = out.toString();
            assertEquals("1.60\n5.00 0.00\n", output);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    public void shouldComputeTimeAndSpeedForDiagonalMovement() {
        String input = "0.0 0.0\n3.0 4.0\n0.0 0.0\n5.0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        try {
            Client.main();
            String output = out.toString();
            assertEquals("1.00\n3.00 4.00\n", output);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    public void shouldComputeTimeAndSpeedWithNegativeCoordinates() {
        String input = "-1.0 -1.0\n1.0 1.0\n0.0 0.0\n1.0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        try {
            Client.main();
            String output = out.toString();
            assertEquals("2.83\n0.71 0.71\n", output);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    public void shouldComputeTimeAndSpeedWithStrongWind() {
        String input = "0.0 0.0\n10.0 10.0\n5.0 5.0\n1.0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        try {
            Client.main();
            String output = out.toString();
            assertEquals("7.07\n0.71 0.71\n", output);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    public void shouldThrowExceptionForNonNumericInput() {
        String input = "a b\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);
        try {
            assertThrows(InputMismatchException.class, () -> Client.main());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void shouldThrowExceptionForInsufficientInput() {
        String input = "0.0 0.0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);
        try {
            assertThrows(NoSuchElementException.class, () -> Client.main());
        } finally {
            System.setIn(originalIn);
        }
    }

}
