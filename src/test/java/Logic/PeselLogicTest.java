package Logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PeselLogicTest {

    private PeselLogic pl;

    @Before
    public void setUp(){
        pl = new PeselLogic();
    }

    @Test
    public void checkPeselCorrectnessTest(){
        //correct pesel
        assertTrue(pl.checkPeselCorrectness("97061000005"));
        assertTrue(pl.checkPeselCorrectness("52090557562"));
        //incorrect pesel
        assertFalse(pl.checkPeselCorrectness("97061000001"));
        assertFalse(pl.checkPeselCorrectness("52090557563"));
    }

    @Test
    public void checkPeselNumberNull(){
        assertFalse(pl.checkPeselCorrectness(null));
    }

    @Test
    public void checkPeselNumberLength(){
        //if the length of the PESEL is > or < than 11, it is not valid
        assertFalse(pl.checkPeselCorrectness("970610000051"));
        assertFalse(pl.checkPeselCorrectness("9706100000"));
        //correct length
        assertTrue(pl.checkPeselCorrectness("97061000005"));
    }

    @Test
    public void checkPeselNumberWithLetter(){
        //pesel WITH letter(s)
        assertFalse(pl.checkPeselCorrectness("52090557A62"));
        assertFalse(pl.checkPeselCorrectness("9706P000005"));
        //pesel WITH NO letter(s)
        assertTrue(pl.checkPeselCorrectness("97061000005"));
    }

}