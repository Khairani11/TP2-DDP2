package edu.learning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BurhanFessTest {
    @Test
    public void testIsKabisatTrue() {
        assertTrue(BurhanFess.isKabisat(2024));
        assertTrue(BurhanFess.isKabisat(2000));
    }

    @Test
    public void testIsKabisatFalse() {
        assertFalse(BurhanFess.isKabisat(2023));
        assertFalse(BurhanFess.isKabisat(2100));
    }

    @Test
    public void testNamaBulanSemua() {
        String[] bulan = {
                "Januari",
                "Februari",
                "Maret",
                "April",
                "Mei",
                "Juni",
                "Juli",
                "Agustus",
                "September",
                "Oktober",
                "November",
                "Desember"
        };
        for (int i = 1; i <= 12; i++) {
            assertEquals(bulan[i - 1], BurhanFess.namaBulan(i));
        }
    }

    @Test
    public void testGetMaxDaysInMonthAllValids() {
        assertEquals(31, BurhanFess.getMaxDaysInMonth(1, 2024)); // Januari
        assertEquals(29, BurhanFess.getMaxDaysInMonth(2, 2024)); // Februari kabisat
        assertEquals(28, BurhanFess.getMaxDaysInMonth(2, 2023)); // Februari non-kabisat
        assertEquals(31, BurhanFess.getMaxDaysInMonth(3, 2024)); // Maret
        assertEquals(30, BurhanFess.getMaxDaysInMonth(4, 2024)); // April
        assertEquals(31, BurhanFess.getMaxDaysInMonth(5, 2024)); // Mei
        assertEquals(30, BurhanFess.getMaxDaysInMonth(6, 2024)); // Juni
        assertEquals(31, BurhanFess.getMaxDaysInMonth(7, 2024)); // Juli
        assertEquals(31, BurhanFess.getMaxDaysInMonth(8, 2024)); // Agustus
        assertEquals(30, BurhanFess.getMaxDaysInMonth(9, 2024)); // September
        assertEquals(31, BurhanFess.getMaxDaysInMonth(10, 2024)); // Oktober
        assertEquals(30, BurhanFess.getMaxDaysInMonth(11, 2024)); // November
        assertEquals(31, BurhanFess.getMaxDaysInMonth(12, 2024)); // Desember
    }

    @Test
    public void testGetMaxDaysInMonthInvalid() {
        assertEquals(0, BurhanFess.getMaxDaysInMonth(0, 2024)); // Invalid month
        assertEquals(0, BurhanFess.getMaxDaysInMonth(13, 2024)); // Invalid month
        assertEquals(0, BurhanFess.getMaxDaysInMonth(-5, 2024)); // Invalid month
    }

    @Test
    public void testCalculateDelayedDateTimeToNextYear() {
        LocalDateTime now = LocalDateTime.of(2025, 12, 31, 23, 59, 59);
        LocalDateTime result = BurhanFess.calculateDelayedDateTime(now, 2); // cross to 2026
        assertEquals(2026, result.getYear());
        assertEquals(1, result.getMonthValue());
        assertEquals(1, result.getDayOfMonth());
        assertEquals(0, result.getHour());
        assertEquals(0, result.getMinute());
        assertEquals(1, result.getSecond());
    }

    @Test
    public void testInterpretasiVibeIfElseAllRanges() {
        assertTrue(BurhanFess.interpretasiVibe(0, 0).contains("pengagum diam-diam"));
        assertTrue(BurhanFess.interpretasiVibe(6, 0).contains("semi-aktif"));
        assertTrue(BurhanFess.interpretasiVibe(11, 0).contains("suka bikin penasaran"));
        assertTrue(BurhanFess.interpretasiVibe(16, 0).contains("suka bikin drama"));
        assertTrue(BurhanFess.interpretasiVibe(21, 0).contains("suka bikin orang mikir"));
        assertTrue(BurhanFess.interpretasiVibe(30, 0).contains("rahasia"));
    }

    @Test
    public void testInterpretasiVibeSwitchAllCases() {
        assertTrue(BurhanFess.interpretasiVibe(0, 1).contains("pengagum diam-diam"));
        assertTrue(BurhanFess.interpretasiVibe(5, 1).contains("semi-aktif"));
        assertTrue(BurhanFess.interpretasiVibe(10, 1).contains("suka bikin penasaran"));
        assertTrue(BurhanFess.interpretasiVibe(15, 1).contains("suka bikin drama"));
        assertTrue(BurhanFess.interpretasiVibe(20, 1).contains("suka bikin orang mikir"));
        assertTrue(BurhanFess.interpretasiVibe(30, 1).contains("rahasia"));
    }

    @Test
    public void testGetValidInputValidLangsung() {
        Scanner scanner = new Scanner("1\n");
        int[] result = BurhanFess.getValidInput(scanner, "Test: ", 0, 1);
        assertEquals(1, result[0]);
        assertEquals(0, result[1]);
    }

    @Test
    public void testGetValidInputWithErrors() {
        Scanner scanner = new Scanner("hello\n3\n1\n");
        int[] result = BurhanFess.getValidInput(scanner, "Test: ", 0, 2);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
    }

    @Test
    public void testHitungKodeVibeAllYes() {
        Scanner scanner = new Scanner("1\nyA\n1\nya\n1\n");
        int result = BurhanFess.hitungKodeVibe(scanner);
        assertEquals(31, result);
    }

    @Test
    public void testTampilkanWaktuSekarang() {
        LocalDateTime waktu = LocalDateTime.of(2025, 6, 28, 14, 5, 9);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        BurhanFess.tampilkanWaktuSekarang(waktu);

        System.setOut(originalOut);

        String expected = String.format("Fess dikirimkan sekarang pada 28 Juni 2025, pukul 14:05:09\n");
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testSimulasiAntrianJumlah1() {
        String input = String.join("\n",
                "1", // jumlah fess
                "10" // delay
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.simulasiAntrianTerjadwal(scanner);
    }

    @Test
    public void testSimulasiAntrianJumlah5() {
        String input = String.join("\n",
                "5", // jumlah fess
                "5", "10", "15", "20", "25" // 5 delay
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.simulasiAntrianTerjadwal(scanner);
    }

    @Test
    public void testSimulasiAntrianDenganPerbaikanJumlahTidakValid() {
        String input = String.join("\n",
                "0", //  invalid
                "7", // invalid
                "3", // valid
                "10", "20", "30");
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.simulasiAntrianTerjadwal(scanner);
    }

    @Test
    public void testSimulasiAntrianJumlah2() {
        String input = String.join("\n",
                "2", // jumlah fess
                "60", "15" // delay
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.simulasiAntrianTerjadwal(scanner);
    }

    @Test
    public void testHitungKemunculanKataTanpaKemunculan() {
        String[] kata = { "halo", "dunia", "indah" };
        int result = BurhanFess.hitungKemunculanKata(kata, 0, "burhan");
        assertEquals(0, result);
    }

    @Test
    public void testHitungKemunculanKataSatuKemunculan() {
        String[] kata = { "Burhan", "fess", "indonesia" };
        int result = BurhanFess.hitungKemunculanKata(kata, 0, "burhan");
        assertEquals(1, result);
    }

    @Test
    public void testHitungKemunculanKataBanyakKemunculanCaseInsensitive() {
        String[] kata = { "burhan", "Burhanfess", "test", "BURHAN", "hello" };
        int result = BurhanFess.hitungKemunculanKata(kata, 0, "burhan");
        assertEquals(3, result); // "burhan", "Burhanfess", "BURHAN"
    }

    @Test
    public void testHitungKemunculanKataEmptyArray() {
        String[] kata = {};
        int result = BurhanFess.hitungKemunculanKata(kata, 0, "burhan");
        assertEquals(0, result);
    }

    @Test
    public void testHitungKemunculanKataIndexDiTengah() {
        String[] kata = { "a", "burhan", "b", "burhan", "c" };
        int result = BurhanFess.hitungKemunculanKata(kata, 2, "burhan"); // mulai dari index ke-2
        assertEquals(1, result); // hanya index 3 yang cocok
    }

    @Test
    public void testAnalisisIntensitasFess_Netral() {
        String input = "\nAku suka makan nasi goreng\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        BurhanFess.analisisIntensitasFess(scanner);

        String result = output.toString();
        assertTrue(result.contains("Jumlah kata"));
        assertTrue(result.contains("Intensitas: Netral"));
    }

    @Test
    public void testAnalisisIntensitasFess_Sedang() {
        String input = "\nburhan itu baik burhan selalu hadir\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        BurhanFess.analisisIntensitasFess(scanner);

        String result = output.toString();
        assertTrue(result.contains("Jumlah 'burhan' : 2"));
        assertTrue(result.contains("Intensitas: Sedang"));
    }

    @Test
    public void testAnalisisIntensitasFess_Tinggi() {
        String input = "\nburhan burhan burhan burhan\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        BurhanFess.analisisIntensitasFess(scanner);

        String result = output.toString();
        assertTrue(result.contains("Jumlah 'burhan' : 4"));
        assertTrue(result.contains("Intensitas: Tinggi"));
    }

    @Test
    public void testRunProgramLangsung() {
        String input = String.join("\n",
                "1", // Q1
                "ya", // Q2
                "1", // Q3
                "ya", // Q4
                "1", // Q5
                "0", // interpretasi: if-else
                "0", // mode kirim: sekarang
                "tidak" // tidak lanjut
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.runProgram(scanner);
    }

    @Test
    public void testRunProgramDelay() {
        String input = String.join("\n",
                "0", "tidak", "0", "tidak", "0", // Q1-5: no contribution
                "1", // interpretasi: switch
                "1", // mode kirim: delay
                "5", // 5 detik delay
                "tidak" // selesai
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.runProgram(scanner);
    }

    @Test
    public void testRunProgramAntrian() {
        String input = String.join("\n",
                "1", "ya", "1", "ya", "1", // Q1-5
                "0", // interpretasi: if-else
                "2", // mode kirim: antrian
                "3", // jumlah fess
                "60", "15", "5", // delay fess 1-3
                "tidak" // tidak lanjut
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.runProgram(scanner);
    }

    @Test
    public void testRunProgramDenganTampilWaktuSekarang() {
        String input = String.join("\n",
                "1", // Q1
                "ya", // Q2
                "1", // Q3
                "ya", // Q4
                "1", // Q5
                "0", // Mode interpretasi: If-Else
                "0", // Mode kirim: sekarang → tampilkanWaktuSekarang akan dipanggil
                "tidak" // Tidak lanjut
        );
        Scanner scanner = new Scanner(new StringReader(input));
        BurhanFess.runProgram(scanner);
    }

    @Test
    public void testRunProgramAnalisisIntensitas() {
        String input = String.join("\n",
                "1", "ya", "1", "ya", "1",
                "0", // if-else
                "3", // analisis intensitas
                "", // consume newline
                "burhan burhan test",
                "tidak");
        BurhanFess.runProgram(new Scanner(new StringReader(input)));
    }
}
