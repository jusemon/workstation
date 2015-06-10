/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contolador;

import Controller.Validaciones.Validador;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Administrador
 */
public class ProbandoValidacionNIT {

    private static String[] validNIT, invalidNIT;

    /**
     * Load data to run the tests.
     */
    @BeforeClass
    public static void NITProviderText() {
        validNIT = new String[]{"123.456.789-1",
            "852.147.258-1", "123.111.111-2",
            "987.654.321-0", "123.123.123-2"};
        invalidNIT = new String[]{"123456789", "12331321", "132.123.123.1", "123-123-123-1", "645-564.654-4"};
    }

    /**
     * Test which validate an array of valid e.
     */
    @Test
    public void validNITTest() {

        for (String temp : validNIT) {

            boolean valid = Validador.validarNIT(temp);
            System.out.println("NIT is valid : " + temp + " , " + valid);

            // All of e-mails of this test must be valid.
            Assert.assertEquals(valid, true);
        }

    }

    /**
     * Test which validate an array of invalid Nit.
     */
    @Test
    public void invalidNITTest() {
        for (String temp : invalidNIT) {
            // Check if the NIT is valid using our method.
            boolean valid = Validador.validarNIT(temp);
            System.out.println("Nit is valid : " + temp + " , " + valid);
            // All of NIT of this test must be invalid.
            Assert.assertEquals(valid, false);
        }
    }

    public ProbandoValidacionNIT() {
    }

}
