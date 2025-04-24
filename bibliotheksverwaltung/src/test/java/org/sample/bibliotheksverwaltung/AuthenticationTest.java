package org.sample.bibliotheksverwaltung;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import application.Authentication;

public class AuthenticationTest {

    // Minimale Implementierung der Authentication-Schnittstelle für Testzwecke
    private Authentication authImpl;
    
    // Testdaten
    private final String TEST_PASSWORD = "sicheresPasswort123";

    @Before
    public void setUp() {
        // Minimale Implementierung der Authentication-Schnittstelle
        authImpl = new Authentication() {
            @Override
            public boolean authenticate(String username, String password) {
                // Für den Test der Default-Methoden ist die Implementierung nicht relevant
                return false;
            }
        };
    }

    /**
     * Testet, ob die Salt-Generierung ein nicht-null-Ergebnis liefert
     */
    @Test
    public void testGenerateSaltNotNull() {
        byte[] salt = authImpl.generateSalt();
        assertNotNull("Das generierte Salt sollte nicht null sein", salt);
    }

    /**
     * Testet, ob die Salt-Generierung ein Salt mit der korrekten Länge erzeugt
     */
    @Test
    public void testGenerateSaltLength() {
        byte[] salt = authImpl.generateSalt();
        assertEquals("Das Salt sollte eine Länge von 16 Bytes haben", 16, salt.length);
    }

    /**
     * Testet, ob die Salt-Generierung bei mehrfachem Aufruf unterschiedliche Werte liefert
     */
    @Test
    public void testGenerateSaltUniqueness() {
        byte[] salt1 = authImpl.generateSalt();
        byte[] salt2 = authImpl.generateSalt();
        
        assertFalse("Zwei generierte Salts sollten unterschiedlich sein", 
                Arrays.equals(salt1, salt2));
    }

    /**
     * Testet, ob die Passwort-Hash-Funktion ein nicht-null-Ergebnis liefert
     */
    @Test
    public void testHashPasswordNotNull() {
        byte[] salt = authImpl.generateSalt();
        byte[] hash = authImpl.hashPassword(TEST_PASSWORD, salt);
        
        assertNotNull("Der Passwort-Hash sollte nicht null sein", hash);
    }

    /**
     * Testet, ob die Passwort-Hash-Funktion für dasselbe Passwort und Salt konsistente Ergebnisse liefert
     */
    @Test
    public void testHashPasswordConsistency() {
        byte[] salt = authImpl.generateSalt();
        byte[] hash1 = authImpl.hashPassword(TEST_PASSWORD, salt);
        byte[] hash2 = authImpl.hashPassword(TEST_PASSWORD, salt);
        
        assertArrayEquals("Die Hash-Funktion sollte für dasselbe Passwort und Salt identische Ergebnisse liefern", 
                       hash1, hash2);
    }

    /**
     * Testet, ob die Passwort-Hash-Funktion für unterschiedliche Passwörter unterschiedliche Ergebnisse liefert
     */
    @Test
    public void testHashPasswordDifferentPasswords() {
        byte[] salt = authImpl.generateSalt();
        byte[] hash1 = authImpl.hashPassword(TEST_PASSWORD, salt);
        byte[] hash2 = authImpl.hashPassword(TEST_PASSWORD + "unterschiedlich", salt);
        
        assertFalse("Die Hash-Funktion sollte für unterschiedliche Passwörter unterschiedliche Ergebnisse liefern", 
                 Arrays.equals(hash1, hash2));
    }

    /**
     * Testet, ob die Passwort-Hash-Funktion für dasselbe Passwort aber unterschiedliche Salts 
     * unterschiedliche Ergebnisse liefert
     */
    @Test
    public void testHashPasswordDifferentSalts() {
        byte[] salt1 = authImpl.generateSalt();
        byte[] salt2 = authImpl.generateSalt();
        byte[] hash1 = authImpl.hashPassword(TEST_PASSWORD, salt1);
        byte[] hash2 = authImpl.hashPassword(TEST_PASSWORD, salt2);
        
        assertFalse("Die Hash-Funktion sollte für dasselbe Passwort aber unterschiedliche Salts unterschiedliche Ergebnisse liefern", 
                 Arrays.equals(hash1, hash2));
    }
    
    /**
     * Testet, ob die Hash-Funktion die erwartete Ausgabelänge produziert (128 Bits = 16 Bytes)
     */
    @Test
    public void testHashPasswordOutputLength() {
        byte[] salt = authImpl.generateSalt();
        byte[] hash = authImpl.hashPassword(TEST_PASSWORD, salt);
        
        assertEquals("Der Hash sollte eine Länge von 16 Bytes (128 Bits) haben", 16, hash.length);
    }
    
    /**
     * Hilfsmethod für assertEquals mit int-Werten
     */
    private void assertEquals(String message, int expected, int actual) {
        org.junit.Assert.assertEquals(message, expected, actual);
    }
}