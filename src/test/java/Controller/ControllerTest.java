package Controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private Controller ctrl;

    @Before
    public void setUp(){
        ctrl = new Controller();
    }

    @Test
    public void runTest(){
        //correct run
        String correctPesel = "97061000005 52090557562";
        InputStream is = new ByteArrayInputStream(correctPesel.getBytes());
        assertTrue(ctrl.run(is));
        //incorrect run
        String incorrectPesel = "97061000001 52090557563 52090557A62"; //all incorrect pesels, so it will return false
        InputStream is2 = new ByteArrayInputStream(incorrectPesel.getBytes());
        assertFalse(ctrl.run(is2));
    }

}
